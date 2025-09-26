package github.muhametshindenis.bitshop.modules.carts.dto;

import jakarta.validation.constraints.NotNull;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 22.09.2025 September 2025
 */
public record UpdateCartItemDto(
    @NotNull(message = "Поле quantity является обязательным!")
    Integer quantity
) {
}
