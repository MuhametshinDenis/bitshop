package github.muhametshindenis.bitshop.modules.users.controller;

import github.muhametshindenis.bitshop.modules.users.dto.UpdateUserDto;
import github.muhametshindenis.bitshop.modules.users.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 13.09.2025 September 2025
 */
@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PatchMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> update(@ModelAttribute UpdateUserDto updateUserDto,
                                    @AuthenticationPrincipal UserDetails userDetails) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.update(updateUserDto, userDetails));
    }
}
