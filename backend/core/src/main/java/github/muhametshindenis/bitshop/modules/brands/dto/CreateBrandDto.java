package github.muhametshindenis.bitshop.modules.brands.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 19.09.2025 September 2025
 */
public record CreateBrandDto(
        @NotBlank(message = "Поле name является обязательным!")
        String name
) {
}
