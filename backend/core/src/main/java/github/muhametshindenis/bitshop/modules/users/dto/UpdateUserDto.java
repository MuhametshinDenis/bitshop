package github.muhametshindenis.bitshop.modules.users.dto;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 13.09.2025 September 2025
 */
public record UpdateUserDto(
        String firstName,
        String lastName,
        MultipartFile avatar,
        String deliveryAddress
) {
}
