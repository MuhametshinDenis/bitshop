package github.muhametshindenis.bitshop.modules.reviews.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 07 October 2025
 */
public record CreateReviewDto(
        @NotBlank(message = "Поле comment является обязательным!")
        String comment,

        @NotNull(message = "Поле rating является обязательным")
        @Min(value = 1, message = "Поле rating должно быть больше и равно 1")
        @Max(value = 5, message = "Поле rating должно быть меньше или равно 5")
        Double rating
) {
}
