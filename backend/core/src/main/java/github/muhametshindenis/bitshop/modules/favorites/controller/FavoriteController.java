package github.muhametshindenis.bitshop.modules.favorites.controller;

import github.muhametshindenis.bitshop.common.dto.ResponseMessage;
import github.muhametshindenis.bitshop.modules.favorites.dto.CreateFavoriteDto;
import github.muhametshindenis.bitshop.modules.favorites.service.FavoriteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 19.09.2025 September 2025
 */
@RestController
@RequestMapping("/api/v1/favorites")
@Tag(name = "Favorites", description = "API для управления избранными")
public class FavoriteController {
    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @Operation(summary = "Добавить продукт в избранное", description = "Создает запись избранного для текущего пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Продукт успешно добавлен в избранное"),
            @ApiResponse(responseCode = "400", description = "Некорректные данные запроса")
    })
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CreateFavoriteDto createFavoriteDto, @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.favoriteService.create(createFavoriteDto, userDetails));
    }

    @Operation(summary = "Получить все избранные продукты текущего пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список избранных продуктов успешно получен")
    })
    @GetMapping
    public ResponseEntity<?> findAll(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(this.favoriteService.findAll(userDetails));
    }

    @Operation(summary = "Получить избранный продукт по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Продукт успешно найден"),
            @ApiResponse(responseCode = "404", description = "Продукт не найден в избранном")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long productId, @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(this.favoriteService.findById(productId, userDetails));
    }

    @Operation(summary = "Удалить все продукты из избранного текущего пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Все продукты успешно удалены")
    })
    @DeleteMapping
    public ResponseEntity<?> deleteAll(@AuthenticationPrincipal UserDetails userDetails) {
        this.favoriteService.deleteAll(userDetails);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(
                "Все продукта из избранного удалены!"
        ));
    }

    @Operation(summary = "Удалить продукт из избранного по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Продукт успешно удален"),
            @ApiResponse(responseCode = "404", description = "Продукт не найден в избранном")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long productId, @AuthenticationPrincipal UserDetails userDetails) {
        this.favoriteService.deleteById(productId, userDetails);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(
                "Продукт c ID: " + productId + " удален из избранного!"
        ));
    }
}
