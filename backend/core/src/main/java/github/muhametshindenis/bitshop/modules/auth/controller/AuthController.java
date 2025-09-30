package github.muhametshindenis.bitshop.modules.auth.controller;

import github.muhametshindenis.bitshop.modules.auth.dto.LoginDto;
import github.muhametshindenis.bitshop.modules.auth.dto.RegisterDto;
import github.muhametshindenis.bitshop.modules.auth.service.impl.AuthServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 27.08.2025 August 2025
 */
@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Auth", description = "API для управение ")
public class AuthController {
    private final AuthServiceImpl authServiceImpl;

    public AuthController(AuthServiceImpl authServiceImpl) {
        this.authServiceImpl = authServiceImpl;
    }

    @Operation(summary = "Авторизация пользователя", description = "Позволяет авторизоваться с использованием email и пароля.", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "Данные для авторизации", content = @Content(schema = @Schema(implementation = LoginDto.class))), responses = {@ApiResponse(responseCode = "200", description = "Авторизация прошла успешно"), @ApiResponse(responseCode = "401", description = "Неверный email или пароль")})
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto, HttpServletRequest request) {
        this.authServiceImpl.login(loginDto, request);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Регистрация нового пользователя", description = "Позволяет создать новый аккаунт с email и паролем.", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "Данные для регистрации", content = @Content(schema = @Schema(implementation = RegisterDto.class))), responses = {@ApiResponse(responseCode = "200", description = "Регистрация прошла успешно"), @ApiResponse(responseCode = "400", description = "Ошибка валидации или пользователь уже существует")})
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterDto registerDto, HttpServletRequest request) {
        this.authServiceImpl.register(registerDto, request);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Получение информации о текущем пользователе", description = "Возвращает данные профиля текущего аутентифицированного пользователя.", responses = {@ApiResponse(responseCode = "200", description = "Информация успешно получена"), @ApiResponse(responseCode = "401", description = "Пользователь не авторизован")})
    @GetMapping("/me")
    public ResponseEntity<?> getUserInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(this.authServiceImpl.getUserInfo());
    }
}
