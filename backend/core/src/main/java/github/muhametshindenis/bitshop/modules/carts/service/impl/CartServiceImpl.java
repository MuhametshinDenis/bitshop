package github.muhametshindenis.bitshop.modules.carts.service.impl;

import github.muhametshindenis.bitshop.common.exception.NotFoundException;
import github.muhametshindenis.bitshop.common.exception.UnauthorizedException;
import github.muhametshindenis.bitshop.modules.carts.dto.CartResponseDto;
import github.muhametshindenis.bitshop.modules.carts.entity.Cart;
import github.muhametshindenis.bitshop.modules.carts.entity.CartItem;
import github.muhametshindenis.bitshop.modules.carts.entity.Currency;
import github.muhametshindenis.bitshop.modules.carts.repository.CartRepository;
import github.muhametshindenis.bitshop.modules.carts.service.CartService;
import github.muhametshindenis.bitshop.modules.users.entity.User;
import github.muhametshindenis.bitshop.modules.users.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 19.09.2025 September 2025
 */
@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final UserService userService;

    public CartServiceImpl(CartRepository cartRepository, UserService userService) {
        this.cartRepository = cartRepository;
        this.userService = userService;
    }

    @Override
    public Cart findCartByUser(User user) {
        Cart cart = this.cartRepository.findByUserId(user.getId()).orElse(null);

        if (cart == null) {
            cart = Cart.builder()
                    .user(user)
                    .cartItems(List.of())
                    .build();
            cart = this.cartRepository.save(cart);
        }

        return cart;
    }


    @Override
    public CartResponseDto findCart(UserDetails userDetails) {
        User user = this.getUserFromUserDetails(userDetails);
        Cart cart = this.cartRepository.findByUserId(user.getId()).orElse(null);

        if (cart == null) {
            return new CartResponseDto(null, 0, BigDecimal.ZERO, Currency.RUB, null, null);
        }

        int totalQuantity = cart.getCartItems().stream().mapToInt(CartItem::getQuantity).sum();

        BigDecimal totalPrice = cart.getCartItems().stream().map(item -> {
            BigDecimal price = item.getProduct().getPrice() != null ? item.getProduct().getPrice() : BigDecimal.ZERO;
            return price.multiply(new BigDecimal(item.getQuantity()));
        }).reduce(BigDecimal.ZERO, BigDecimal::add);


        return new CartResponseDto(cart.getId(), totalQuantity, totalPrice, cart.getCurrency(), cart.getCreatedAt(), cart.getUpdatedAt());
    }

    @Override
    public void delete(UserDetails userDetails) {
        User user = this.getUserFromUserDetails(userDetails);

        this.cartRepository.findByUserId(user.getId()).ifPresent(cartRepository::delete);
    }

    private User getUserFromUserDetails(UserDetails userDetails) {
        String userEmail = userDetails.getUsername();

        if (userEmail == null || userEmail.isEmpty()) {
            throw new UnauthorizedException("Пользователь не авторизирован!");
        }

        return this.userService.findUserByEmail(userEmail).orElseThrow(() -> new NotFoundException("Пользователь с email: " + userEmail + " не найден"));
    }
}
