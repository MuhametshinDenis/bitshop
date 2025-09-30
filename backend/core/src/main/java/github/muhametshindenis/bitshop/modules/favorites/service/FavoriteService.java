package github.muhametshindenis.bitshop.modules.favorites.service;

import github.muhametshindenis.bitshop.common.exception.ConflictException;
import github.muhametshindenis.bitshop.common.exception.NotFoundException;
import github.muhametshindenis.bitshop.common.exception.UnauthorizedException;
import github.muhametshindenis.bitshop.modules.favorites.dto.CreateFavoriteDto;
import github.muhametshindenis.bitshop.modules.favorites.dto.FavoriteResponseDto;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;


/**
 * Интерфейс {@link FavoriteService} предоставляет контракт для работы с избранными продуктами пользователя.
 * <p>
 * Контракт включает методы для:
 * <ul>
 *     <li>добавления продукта в избранное;</li>
 *     <li>получения всех избранных продуктов текущего пользователя;</li>
 *     <li>получения конкретного избранного продукта по ID продукта;</li>
 *     <li>удаления конкретного продукта из избранного;</li>
 *     <li>удаления всех продуктов из избранного текущего пользователя.</li>
 * </ul>
 * <p>
 * Все методы требуют {@link UserDetails} текущего пользователя для обеспечения безопасности и фильтрации данных.
 * Методы выбрасывают исключения при нарушении бизнес-логики:
 * <ul>
 *     <li>{@link ConflictException} — при попытке добавить продукт, который уже в избранном;</li>
 *     <li>{@link NotFoundException} — при попытке получить или удалить несуществующий элемент;</li>
 *     <li>{@link UnauthorizedException} — при отсутствии или некорректной аутентификации пользователя.</li>
 * </ul>
 *
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 19.09.2025 September 2025
 */
public interface FavoriteService {
    /**
     * Добавляет продукт в избранное для текущего пользователя.
     *
     * @param createFavoriteDto DTO с данными о продукте для добавления в избранное
     * @param userDetails текущий аутентифицированный пользователь
     * @return {@link FavoriteResponseDto} с информацией о добавленном избранном
     * @throws ConflictException если продукт уже находится в избранном
     */
    FavoriteResponseDto create(CreateFavoriteDto createFavoriteDto, UserDetails userDetails);

    /**
     * Возвращает все избранные продукты текущего пользователя.
     *
     * @param userDetails текущий аутентифицированный пользователь
     * @return список {@link FavoriteResponseDto} всех избранных продуктов
     */
    List<FavoriteResponseDto> findAll(UserDetails userDetails);

    /**
     * Получает конкретный избранный продукт по ID продукта для текущего пользователя.
     *
     * @param productId ID продукта
     * @param userDetails текущий аутентифицированный пользователь
     * @return {@link FavoriteResponseDto} найденного продукта
     * @throws NotFoundException если продукт не найден в избранном
     */
    FavoriteResponseDto findById(Long productId, UserDetails userDetails);

    /**
     * Удаляет продукт из избранного текущего пользователя по ID продукта.
     *
     * @param productId ID продукта
     * @param userDetails текущий аутентифицированный пользователь
     * @throws NotFoundException если продукт не найден в избранном
     */
    void deleteById(Long productId, UserDetails userDetails);


    /**
     * Удаляет все продукты из избранного текущего пользователя.
     *
     * @param userDetails текущий аутентифицированный пользователь
     */
    void deleteAll(UserDetails userDetails);
}
