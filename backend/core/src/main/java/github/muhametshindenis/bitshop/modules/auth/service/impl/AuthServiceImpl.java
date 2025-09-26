package github.muhametshindenis.bitshop.modules.auth.service.impl;

import github.muhametshindenis.bitshop.common.exception.ConflictException;
import github.muhametshindenis.bitshop.common.exception.UnauthorizedException;
import github.muhametshindenis.bitshop.modules.auth.dto.LoginDto;
import github.muhametshindenis.bitshop.modules.auth.dto.RegisterDto;
import github.muhametshindenis.bitshop.modules.auth.mapper.AuthMapper;
import github.muhametshindenis.bitshop.modules.auth.service.AuthService;
import github.muhametshindenis.bitshop.modules.users.dto.CreateUserDto;
import github.muhametshindenis.bitshop.modules.users.dto.UserResponseDto;
import github.muhametshindenis.bitshop.modules.users.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 27.08.2025 August 2025
 */
@Service
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    public void login(LoginDto loginDto, HttpServletRequest request) {
        if (!this.userService.validateUserCredentials(loginDto.email(), loginDto.password())) {
            throw new UnauthorizedException("Пользователь с таким email или password не найден!");
        }

        Authentication auth = this.authenticate(loginDto.email(), loginDto.password());
        setAuthenticationInSession(auth, request);
    }

    public void register(RegisterDto registerDto, HttpServletRequest request) {
        if (this.userService.findByEmail(registerDto.email()).isPresent()) {
            throw new ConflictException("Пользователь с email: " + registerDto.email() + " уже существует!");
        }

        CreateUserDto createUserDto = AuthMapper.toCreateUserDto(registerDto);
        this.userService.create(createUserDto);

        Authentication auth = this.authenticate(registerDto.email(), registerDto.password());
        setAuthenticationInSession(auth, request);
    }

    public UserResponseDto getUserInfo() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        return this.userService.findByEmail(userEmail).orElseThrow(() ->
                new UnauthorizedException("Не зарегистрированный пользователь!"));
    }

    private Authentication authenticate(String email, String password) {
        var token = new UsernamePasswordAuthenticationToken(email, password);
        return this.authenticationManager.authenticate(token);
    }

    private void setAuthenticationInSession(Authentication authentication, HttpServletRequest request) {
        SecurityContextHolder.getContext().setAuthentication(authentication);

        request.getSession(true);

        new HttpSessionSecurityContextRepository()
                .saveContext(SecurityContextHolder.getContext(), request, null);
    }
}
