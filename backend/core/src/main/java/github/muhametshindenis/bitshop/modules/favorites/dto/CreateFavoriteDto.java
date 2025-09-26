package github.muhametshindenis.bitshop.modules.favorites.dto;

import jakarta.validation.constraints.NotNull;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 19.09.2025 September 2025
 */
public record CreateFavoriteDto(
        @NotNull(message = "Поле productId является обязательным!")
        Long productId
) {
}
