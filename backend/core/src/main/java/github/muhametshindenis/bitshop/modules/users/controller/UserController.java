package github.muhametshindenis.bitshop.modules.users.controller;

import github.muhametshindenis.bitshop.modules.users.dto.UpdateUserDto;
import github.muhametshindenis.bitshop.modules.users.dto.UserResponseDto;
import github.muhametshindenis.bitshop.modules.users.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Users", description = "API для управления пользователями")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PatchMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
            summary = "Обновление данных пользователя",
            description = "Позволяет обновить имя, фамилию, адрес доставки и аватар пользователя. " +
                    "Аватар передается как multipart файл.",
            requestBody = @RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                            schema = @Schema(implementation = UpdateUserDto.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Пользователь успешно обновлен",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Некорректный запрос (например, пустой аватар)",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Пользователь не найден",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Внутренняя ошибка сервера",
                            content = @Content
                    )
            }
    )
    public ResponseEntity<?> update(@ModelAttribute UpdateUserDto updateUserDto,
                                    @AuthenticationPrincipal UserDetails userDetails) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.update(updateUserDto, userDetails));
    }
}
