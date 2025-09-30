package github.muhametshindenis.bitshop.modules.carts.service;

import github.muhametshindenis.bitshop.common.exception.NotFoundException;
import github.muhametshindenis.bitshop.common.exception.UnauthorizedException;
import github.muhametshindenis.bitshop.modules.carts.dto.CartResponseDto;
import github.muhametshindenis.bitshop.modules.carts.entity.Cart;
import github.muhametshindenis.bitshop.modules.users.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Интерфейс {@link CartService} предоставляет контракт для управления корзиной пользователя.
 * <p>
 * Контракт включает методы для:
 * <ul>
 *     <li>поиска и инициализации корзины по {@link User};</li>
 *     <li>получения информации о корзине текущего пользователя;</li>
 *     <li>удаления корзины текущего пользователя.</li>
 * </ul>
 * <p>
 * Методы выбрасывают исключения при нарушении бизнес-логики:
 * <ul>
 *     <li>{@link UnauthorizedException} — если пользователь не аутентифицирован;</li>
 *     <li>{@link NotFoundException} — если пользователь не найден.</li>
 * </ul>
 *
 * Автор: Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 19.09.2025 September 2025
 */
public interface CartService {

    /**
     * Ищет корзину пользователя. Если корзина не существует — создаёт новую.
     *
     * @param user сущность {@link User}, для которой ищется корзина
     * @return {@link Cart} найденная или вновь созданная корзина
     */
    Cart findCartByUser(User user);

    /**
     * Возвращает информацию о корзине текущего пользователя.
     *
     * @param userDetails текущий аутентифицированный пользователь
     * @return {@link CartResponseDto} с данными корзины (общее количество, сумма, валюта и т. д.)
     * @throws UnauthorizedException если пользователь не аутентифицирован
     * @throws NotFoundException если пользователь не найден
     */
    CartResponseDto findCart(UserDetails userDetails);

    /**
     * Удаляет корзину текущего пользователя, если она существует.
     *
     * @param userDetails текущий аутентифицированный пользователь
     * @throws UnauthorizedException если пользователь не аутентифицирован
     * @throws NotFoundException если пользователь не найден
     */
    void delete(UserDetails userDetails);
}