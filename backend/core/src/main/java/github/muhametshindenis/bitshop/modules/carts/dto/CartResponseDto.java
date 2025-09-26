package github.muhametshindenis.bitshop.modules.carts.dto;

import github.muhametshindenis.bitshop.modules.carts.entity.Currency;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 22.09.2025 September 2025
 */
public record CartResponseDto(
        Long id,
        Integer totalQuantity,
        BigDecimal totalPrice,
        Currency currency,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
