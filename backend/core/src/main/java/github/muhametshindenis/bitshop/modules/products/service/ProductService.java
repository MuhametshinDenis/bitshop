package github.muhametshindenis.bitshop.modules.products.service;

import github.muhametshindenis.bitshop.common.exception.BadRequestException;
import github.muhametshindenis.bitshop.common.exception.ConflictException;
import github.muhametshindenis.bitshop.common.exception.NotFoundException;
import github.muhametshindenis.bitshop.modules.brands.service.BrandService;
import github.muhametshindenis.bitshop.modules.categories.service.CategoryService;
import github.muhametshindenis.bitshop.modules.products.dto.CreateProductDto;
import github.muhametshindenis.bitshop.modules.products.dto.ProductFilterDto;
import github.muhametshindenis.bitshop.modules.products.dto.ProductResponseDto;
import github.muhametshindenis.bitshop.modules.products.dto.UpdateProductDto;
import github.muhametshindenis.bitshop.modules.products.entity.Product;
import github.muhametshindenis.bitshop.modules.storage.service.StorageService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Интерфейс {@link ProductService} описывает контракт для работы с продуктами.
 * <p>
 * Методы сервиса позволяют:
 * <ul>
 *     <li>создавать новый продукт с изображением;</li>
 *     <li>обновлять данные и изображение продукта;</li>
 *     <li>удалять продукт по ID;</li>
 *     <li>получать продукт по ID;</li>
 *     <li>получать список продуктов с фильтрацией и пагинацией;</li>
 *     <li>получать сущность продукта для внутреннего использования.</li>
 * </ul>
 * <p>
 * Сервис не занимается непосредственным сохранением изображений в S3 — это делегируется {@link StorageService}.
 * Валидация и проверка существования категорий и брендов выполняется через соответствующие сервисы {@link CategoryService} и {@link BrandService}.
 * <p>
 * Методы могут выбрасывать исключения:
 * <ul>
 *     <li>{@link BadRequestException}</li>
 *     <li>{@link ConflictException}</li>
 *     <li>{@link NotFoundException}</li>
 * </ul>
 *
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 15.09.2025 September 2025
 */
public interface ProductService {
    /**
     * Создаёт новый продукт и сохраняет изображение в S3.
     *
     * @param createProductDto DTO с данными продукта
     * @param image            изображение продукта
     * @return {@link ProductResponseDto} созданного продукта
     * @throws IOException если возникает ошибка при сохранении изображения
     */
    ProductResponseDto create(CreateProductDto createProductDto, MultipartFile image) throws IOException;

    /**
     * Получает продукт по его ID.
     *
     * @param productId ID продукта
     * @return {@link ProductResponseDto} продукта
     */
    ProductResponseDto findById(Long productId);

    /**
     * Получает список продуктов с фильтрацией по категории, цене и с поддержкой пагинации.
     *
     * @param productFilterDto DTO с фильтрами и параметрами страницы
     * @return список {@link ProductResponseDto} продуктов
     */
    List<ProductResponseDto> findByFilters(ProductFilterDto productFilterDto);

    /**
     * Обновляет данные продукта и при необходимости изображение.
     *
     * @param updateProductDto DTO с обновлёнными данными продукта
     * @param image            новое изображение продукта (может быть null)
     * @param productId        ID продукта для обновления
     * @return {@link ProductResponseDto} обновлённого продукта
     * @throws IOException если возникает ошибка при сохранении нового изображения
     */
    ProductResponseDto update(UpdateProductDto updateProductDto, MultipartFile image, Long productId) throws IOException;

    /**
     * Удаляет продукт и его изображение из хранилища.
     *
     * @param productId ID продукта для удаления
     */
    void deleteById(Long productId);

    /**
     * Получает сущность {@link Product} по ID для внутреннего использования.
     *
     * @param productId ID продукта
     * @return {@link Product} сущность продукта
     */
    Product findProductById(Long productId);
}
