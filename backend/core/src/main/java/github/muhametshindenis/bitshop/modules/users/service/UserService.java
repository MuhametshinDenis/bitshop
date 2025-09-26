package github.muhametshindenis.bitshop.modules.users.service;

import github.muhametshindenis.bitshop.modules.users.dto.CreateUserDto;
import github.muhametshindenis.bitshop.modules.users.dto.UpdateUserDto;
import github.muhametshindenis.bitshop.modules.users.dto.UserResponseDto;
import github.muhametshindenis.bitshop.modules.users.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;
import java.util.Optional;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 12.09.2025 September 2025
 */
public interface UserService {
    void create(CreateUserDto createUserDto);
    Optional<User> findUserByEmail(String email);
    Optional<UserResponseDto> findByEmail(String email);
    Optional<UserResponseDto> findById(Long id);
    void deleteById(Long id);
    boolean validateUserCredentials(String email, String password);
    UserResponseDto update(UpdateUserDto updateUserDto, UserDetails userDetails) throws IOException;
}
