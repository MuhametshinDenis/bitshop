package github.muhametshindenis.bitshop.modules.carts.controller;

import github.muhametshindenis.bitshop.common.dto.ResponseMessage;
import github.muhametshindenis.bitshop.modules.carts.dto.CreateCartItemDto;
import github.muhametshindenis.bitshop.modules.carts.dto.UpdateCartItemDto;
import github.muhametshindenis.bitshop.modules.carts.service.CartItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
 * @since 23.09.2025 September 2025
 */
@Tag(name = "Cart Items", description = "API для управления товарами в корзине")
@RestController
@RequestMapping("api/v1/carts/items")
public class CartItemController {
    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @Operation(
            summary = "Добавить товар в корзину",
            description = "Создаёт новый элемент корзины для аутентифицированного пользователя.",
            responses = {
                @ApiResponse(responseCode = "201", description = "Товар успешно добавлен", content = @Content(schema = @Schema(implementation = CreateCartItemDto.class))),
                    @ApiResponse(responseCode = "400", description = "Некорректные данные", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Пользователь не авторизован", content = @Content)
            }
    )
    @PostMapping
    public ResponseEntity<?> create(@AuthenticationPrincipal UserDetails userDetails, @Valid @RequestBody CreateCartItemDto createCartItemDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.cartItemService.create(userDetails, createCartItemDto));
    }

    @Operation( summary = "Получить все товары из корзины", description = "Возвращает список всех товаров в корзине аутентифицированного пользователя.", responses = { @ApiResponse(responseCode = "200", description = "Товары успешно получены"), @ApiResponse(responseCode = "401", description = "Пользователь не авторизован", content = @Content) } )
    @GetMapping
    public ResponseEntity<?> findAll(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(this.cartItemService.findAll(userDetails));
    }

    @Operation( summary = "Получить товар из корзины по ID", description = "Возвращает элемент корзины по идентификатору продукта.", responses = { @ApiResponse(responseCode = "200", description = "Товар найден"), @ApiResponse(responseCode = "404", description = "Товар не найден", content = @Content), @ApiResponse(responseCode = "401", description = "Пользователь не авторизован", content = @Content) } )
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") Long productId) {
        return ResponseEntity.status(HttpStatus.OK).body(this.cartItemService.findByProductId(userDetails, productId));
    }

    @Operation( summary = "Обновить товар в корзине", description = "Обновляет количество или данные товара в корзине по идентификатору продукта.", responses = { @ApiResponse(responseCode = "200", description = "Товар успешно обновлён", content = @Content(schema = @Schema(implementation = UpdateCartItemDto.class))), @ApiResponse(responseCode = "400", description = "Некорректные данные", content = @Content), @ApiResponse(responseCode = "404", description = "Товар не найден", content = @Content), @ApiResponse(responseCode = "401", description = "Пользователь не авторизован", content = @Content) } )
    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") Long productId, @Valid @RequestBody UpdateCartItemDto updateCartItemDto) {
        return ResponseEntity.status(HttpStatus.OK).body(this.cartItemService.update(userDetails, updateCartItemDto, productId));
    }

    @Operation( summary = "Удалить товар из корзины", description = "Удаляет товар из корзины аутентифицированного пользователя по ID продукта.", responses = { @ApiResponse(responseCode = "200", description = "Товар успешно удалён", content = @Content(schema = @Schema(implementation = ResponseMessage.class))), @ApiResponse(responseCode = "404", description = "Товар не найден", content = @Content), @ApiResponse(responseCode = "401", description = "Пользователь не авторизован", content = @Content) } )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") Long productId) {
        this.cartItemService.delete(userDetails, productId);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(
                "Продукт с ID: " + productId + " успешно удален!"
        ));
    }
}
