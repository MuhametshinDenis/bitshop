package github.muhametshindenis.bitshop.modules.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 27.08.2025 August 2025
 */
public record LoginDto(
        @Email
        @NotBlank
        String email,

        @NotBlank
        String password
) {
}
