package github.muhametshindenis.bitshop.modules.carts.dto;

import java.math.BigDecimal;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 22.09.2025 September 2025
 */
public record CartItemResponseDto(
        Long productId,
        String name,
        Integer quantity,
        BigDecimal price,
        BigDecimal totalPrice
) {
}
