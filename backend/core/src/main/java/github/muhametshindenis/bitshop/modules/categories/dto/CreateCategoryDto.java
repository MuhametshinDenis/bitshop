package github.muhametshindenis.bitshop.modules.categories.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 18.09.2025 September 2025
 */
public record CreateCategoryDto(
        @NotBlank(message = "Поле name является обязательным!")
        String name
) {
}
