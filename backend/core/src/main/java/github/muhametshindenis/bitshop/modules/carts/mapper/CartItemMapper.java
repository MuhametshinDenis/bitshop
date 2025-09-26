package github.muhametshindenis.bitshop.modules.carts.mapper;

import github.muhametshindenis.bitshop.modules.carts.dto.CartItemResponseDto;
import github.muhametshindenis.bitshop.modules.carts.entity.CartItem;

import java.math.BigDecimal;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 24.09.2025 September 2025
 */
public class CartItemMapper {
    public static CartItemResponseDto toResponse(CartItem cartItem) {
        return new CartItemResponseDto(
                cartItem.getProduct().getId(),
                cartItem.getProduct().getName(),
                cartItem.getQuantity(),
                cartItem.getProduct().getPrice(),
                calculateTotalPrice(cartItem)
        );
    }

    private static BigDecimal calculateTotalPrice(CartItem cartItems) {
        return cartItems.getProduct().getPrice().multiply(new BigDecimal(cartItems.getQuantity()));
    }
}
