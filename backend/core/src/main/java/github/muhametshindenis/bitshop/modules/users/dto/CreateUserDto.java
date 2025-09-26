package github.muhametshindenis.bitshop.modules.users.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 12.09.2025 September 2025
 */
@Data
@Builder
public class CreateUserDto {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
