package github.muhametshindenis.bitshop.modules.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 27.08.2025 August 2025
 */
public record RegisterDto(
        @Email(message = "Обязательно email")
        @NotBlank(message = "Поле email не должно быть пустым!")
        String email,

        @NotBlank(message = "Поле password не должно быть пустым!")
        String password,

        @NotBlank(message = "Поле firstName не должно быть пустым!")
        String firstName,

        @NotBlank(message = "Поле lastName не должно быть пустым!")
        String lastName
) {
}
