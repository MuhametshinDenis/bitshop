package github.muhametshindenis.bitshop.modules.brands.service;

import github.muhametshindenis.bitshop.common.exception.BadRequestException;
import github.muhametshindenis.bitshop.common.exception.ConflictException;
import github.muhametshindenis.bitshop.common.exception.NotFoundException;
import github.muhametshindenis.bitshop.modules.brands.dto.BrandResponseDto;
import github.muhametshindenis.bitshop.modules.brands.dto.CreateBrandDto;
import github.muhametshindenis.bitshop.modules.brands.dto.UpdateBrandDto;
import github.muhametshindenis.bitshop.modules.brands.entity.Brand;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Интерфейс {@link BrandService} предоставляет контракт для управления брендами.
 * <p>
 * Контракт включает методы для:
 * <ul>
 *     <li>создания нового бренда с обязательным изображением;</li>
 *     <li>получения списка всех брендов;</li>
 *     <li>получения информации о бренде по его ID;</li>
 *     <li>обновления существующего бренда (данные и/или изображение);</li>
 *     <li>удаления бренда по ID вместе с его изображением;</li>
 *     <li>поиска сущности бренда для внутренних сервисных операций.</li>
 * </ul>
 * <p>
 * Методы выбрасывают исключения при нарушении бизнес-логики:
 * <ul>
 *     <li>{@link BadRequestException} —
 *     если отсутствуют необходимые данные для создания или обновления;</li>
 *     <li>{@link ConflictException} —
 *     если бренд с таким названием уже существует;</li>
 *     <li>{@link NotFoundException} —
 *     если бренд не найден по указанному идентификатору.</li>
 * </ul>
 * <p>
 * Методы, работающие с изображениями, могут выбрасывать {@link IOException}.
 *
 * Автор: Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 19.09.2025 September 2025
 */
public interface BrandService {

    /**
     * Создаёт новый бренд.
     *
     * @param createBrandDto DTO с данными для создания бренда
     * @param image обязательное изображение бренда
     * @return {@link BrandResponseDto} с информацией о созданном бренде
     * @throws IOException если произошла ошибка при сохранении изображения
     */
    BrandResponseDto create(CreateBrandDto createBrandDto, MultipartFile image) throws IOException;

    /**
     * Возвращает список всех брендов.
     *
     * @return список {@link BrandResponseDto}
     */
    List<BrandResponseDto> findALl();

    /**
     * Находит бренд по идентификатору.
     *
     * @param brandId ID бренда
     * @return {@link BrandResponseDto} с информацией о найденном бренде
     * @throws github.muhametshindenis.bitshop.common.exception.NotFoundException если бренд не найден
     */
    BrandResponseDto findById(Long brandId);

    /**
     * Обновляет данные существующего бренда.
     *
     * @param updateBrandDto DTO с обновлёнными данными бренда (может быть null, если обновляется только изображение)
     * @param image новое изображение бренда (может быть null)
     * @param brandId ID бренда, который нужно обновить
     * @return {@link BrandResponseDto} с обновлённой информацией
     * @throws IOException если произошла ошибка при сохранении изображения
     * @throws github.muhametshindenis.bitshop.common.exception.NotFoundException если бренд не найден
     * @throws github.muhametshindenis.bitshop.common.exception.BadRequestException если не передано ни одно поле для обновления
     */
    BrandResponseDto update(UpdateBrandDto updateBrandDto, MultipartFile image, Long brandId) throws IOException;

    /**
     * Удаляет бренд по его идентификатору.
     * Вместе с брендом удаляется и его изображение из хранилища.
     *
     * @param brandId ID бренда
     * @throws github.muhametshindenis.bitshop.common.exception.NotFoundException если бренд не найден
     */
    void deleteById(Long brandId);

    /**
     * Находит сущность {@link Brand} по идентификатору.
     * Используется для внутренних сервисных операций.
     *
     * @param brandId ID бренда
     * @return сущность {@link Brand}
     * @throws github.muhametshindenis.bitshop.common.exception.NotFoundException если бренд не найден
     */
    Brand findBrandById(Long brandId);
}
