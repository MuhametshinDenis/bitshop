package github.muhametshindenis.bitshop.modules.carts.controller;

import github.muhametshindenis.bitshop.common.dto.ResponseMessage;
import github.muhametshindenis.bitshop.modules.carts.dto.CartResponseDto;
import github.muhametshindenis.bitshop.modules.carts.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 19.09.2025 September 2025
 */
@Tag(name = "Cart", description = "API для управления корзиной пользователя")
@RestController
@RequestMapping("/api/v1/carts")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @Operation(summary = "Получить корзину пользователя", description = "Возвращает текущее состояние корзины для аутентифицированного пользователя.", responses = {@ApiResponse(responseCode = "200", description = "Корзина успешно получена", content = @Content(schema = @Schema(implementation = CartResponseDto.class))), @ApiResponse(responseCode = "401", description = "Пользователь не авторизован", content = @Content)})
    @GetMapping
    public ResponseEntity<?> getCart(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(cartService.findCart(userDetails));
    }

    @Operation(summary = "Очистить корзину пользователя", description = "Удаляет все товары из корзины аутентифицированного пользователя.", responses = {@ApiResponse(responseCode = "200", description = "Корзина успешно очищена", content = @Content(schema = @Schema(implementation = ResponseMessage.class))), @ApiResponse(responseCode = "401", description = "Пользователь не авторизован", content = @Content)})
    @DeleteMapping
    public ResponseEntity<?> deleteCart(@AuthenticationPrincipal UserDetails userDetails) {
        this.cartService.delete(userDetails);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Корзина успешно очищена!"));
    }
}
