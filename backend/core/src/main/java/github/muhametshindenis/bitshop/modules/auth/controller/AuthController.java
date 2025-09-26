package github.muhametshindenis.bitshop.modules.auth.controller;

import github.muhametshindenis.bitshop.modules.auth.dto.LoginDto;
import github.muhametshindenis.bitshop.modules.auth.dto.RegisterDto;
import github.muhametshindenis.bitshop.modules.auth.service.impl.AuthServiceImpl;
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
public class AuthController {
    private final AuthServiceImpl authServiceImpl;

    public AuthController(AuthServiceImpl authServiceImpl) {
        this.authServiceImpl = authServiceImpl;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto, HttpServletRequest request) {
        this.authServiceImpl.login(loginDto, request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterDto registerDto, HttpServletRequest request) {
        this.authServiceImpl.register(registerDto, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<?> getUserInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(this.authServiceImpl.getUserInfo());
    }
}
