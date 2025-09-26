package github.muhametshindenis.bitshop.modules.auth.service;

import github.muhametshindenis.bitshop.modules.auth.dto.LoginDto;
import github.muhametshindenis.bitshop.modules.auth.dto.RegisterDto;
import github.muhametshindenis.bitshop.modules.users.dto.UserResponseDto;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 12.09.2025 September 2025
 */
public interface AuthService {
    void login(LoginDto loginDto, HttpServletRequest request);
    void register(RegisterDto registerDto, HttpServletRequest request);
    UserResponseDto getUserInfo();
}
