package github.muhametshindenis.bitshop.modules.users.dto;

import java.time.LocalDateTime;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 27.08.2025 August 2025
 */
public record UserResponseDto(
        String email,
        String firstName,
        String lastName,
        String avatarUrl,
        String deliveryAddress
) {
}
