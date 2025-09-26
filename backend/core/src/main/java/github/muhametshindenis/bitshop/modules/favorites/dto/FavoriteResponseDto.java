package github.muhametshindenis.bitshop.modules.favorites.dto;

import java.math.BigDecimal;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 19.09.2025 September 2025
 */
public record FavoriteResponseDto(
        Long id,
        Long productId,
        String name,
        String imageUrl,
        BigDecimal price
) {
}
