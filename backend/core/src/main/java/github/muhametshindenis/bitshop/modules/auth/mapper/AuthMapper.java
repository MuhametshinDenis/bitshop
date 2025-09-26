package github.muhametshindenis.bitshop.modules.auth.mapper;

import github.muhametshindenis.bitshop.modules.auth.dto.RegisterDto;
import github.muhametshindenis.bitshop.modules.users.dto.CreateUserDto;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 18.09.2025 September 2025
 */
public class AuthMapper {
    public static CreateUserDto toCreateUserDto(RegisterDto registerDto) {
        return CreateUserDto.builder()
                .email(registerDto.email())
                .password(registerDto.password())
                .firstName(registerDto.firstName())
                .lastName(registerDto.lastName())
                .build();
    }
}
