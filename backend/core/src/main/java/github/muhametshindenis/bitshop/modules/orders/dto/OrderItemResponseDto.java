package github.muhametshindenis.bitshop.modules.orders.dto;

import java.math.BigDecimal;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 01.10.2025 October 2025
 */
public record OrderItemResponseDto(
        Long id,
        String name,
        Long productId,
        BigDecimal price,
        Long quantity,
        BigDecimal totalPrice
) {
}
