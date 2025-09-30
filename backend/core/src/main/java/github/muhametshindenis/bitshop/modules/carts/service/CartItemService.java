package github.muhametshindenis.bitshop.modules.carts.service;

import github.muhametshindenis.bitshop.common.exception.BadRequestException;
import github.muhametshindenis.bitshop.common.exception.ConflictException;
import github.muhametshindenis.bitshop.common.exception.NotFoundException;
import github.muhametshindenis.bitshop.common.exception.UnauthorizedException;
import github.muhametshindenis.bitshop.modules.carts.dto.CartItemResponseDto;
import github.muhametshindenis.bitshop.modules.carts.dto.CreateCartItemDto;
import github.muhametshindenis.bitshop.modules.carts.dto.UpdateCartItemDto;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * Интерфейс {@link CartItemService} предоставляет контракт для управления товарами в корзине пользователя.
 * <p>
 * Контракт включает методы для:
 * <ul>
 *     <li>добавления товара в корзину;</li>
 *     <li>получения списка всех товаров в корзине текущего пользователя;</li>
 *     <li>получения конкретного товара в корзине по ID продукта;</li>
 *     <li>обновления количества товара в корзине;</li>
 *     <li>удаления товара из корзины.</li>
 * </ul>
 * <p>
 * Все методы требуют {@link UserDetails} текущего пользователя для обеспечения безопасности и фильтрации данных.
 * Методы выбрасывают исключения при нарушении бизнес-логики:
 * <ul>
 *     <li>{@link UnauthorizedException} — если пользователь не аутентифицирован;</li>
 *     <li>{@link NotFoundException} — если товар или пользователь не найдены;</li>
 *     <li>{@link ConflictException} — при попытке добавить товар, который уже есть в корзине, либо указать недопустимое количество;</li>
 *     <li>{@link BadRequestException} — если количество товара превышает доступное.</li>
 * </ul>
 *
 * Автор: Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 23.09.2025 September 2025
 */
public interface CartItemService {

    /**
     * Добавляет товар в корзину текущего пользователя.
     *
     * @param userDetails текущий аутентифицированный пользователь
     * @param createCartItemDto DTO с данными о товаре (ID продукта и количество)
     * @return {@link CartItemResponseDto} с информацией о добавленном товаре
     * @throws ConflictException если товар уже находится в корзине
     * @throws BadRequestException если количество товара превышает доступное
     * @throws UnauthorizedException если пользователь не аутентифицирован
     */
    CartItemResponseDto create(UserDetails userDetails, CreateCartItemDto createCartItemDto);

    /**
     * Возвращает список всех товаров в корзине текущего пользователя.
     *
     * @param userDetails текущий аутентифицированный пользователь
     * @return список {@link CartItemResponseDto}
     * @throws UnauthorizedException если пользователь не аутентифицирован
     */
    List<CartItemResponseDto> findAll(UserDetails userDetails);

    /**
     * Получает конкретный товар из корзины по ID продукта.
     *
     * @param userDetails текущий аутентифицированный пользователь
     * @param productId ID продукта
     * @return {@link CartItemResponseDto} найденного товара
     * @throws NotFoundException если товар не найден в корзине
     * @throws UnauthorizedException если пользователь не аутентифицирован
     */
    CartItemResponseDto findByProductId(UserDetails userDetails, Long productId);

    /**
     * Обновляет количество товара в корзине.
     *
     * @param userDetails текущий аутентифицированный пользователь
     * @param updateCartItemDto DTO с новым количеством товара
     * @param productId ID продукта
     * @return {@link CartItemResponseDto} с обновлённой информацией
     * @throws NotFoundException если товар не найден в корзине
     * @throws ConflictException если указано количество больше доступного
     * @throws UnauthorizedException если пользователь не аутентифицирован
     */
    CartItemResponseDto update(UserDetails userDetails, UpdateCartItemDto updateCartItemDto, Long productId);

    /**
     * Удаляет товар из корзины текущего пользователя по ID продукта.
     *
     * @param userDetails текущий аутентифицированный пользователь
     * @param productId ID продукта
     * @throws NotFoundException если товар не найден в корзине
     * @throws UnauthorizedException если пользователь не аутентифицирован
     */
    void delete(UserDetails userDetails, Long productId);
}