package github.muhametshindenis.bitshop.modules.carts.controller;

import github.muhametshindenis.bitshop.common.dto.ApiResponse;
import github.muhametshindenis.bitshop.modules.carts.dto.CreateCartItemDto;
import github.muhametshindenis.bitshop.modules.carts.dto.UpdateCartItemDto;
import github.muhametshindenis.bitshop.modules.carts.service.CartItemService;
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
@RestController
@RequestMapping("api/v1/carts/items")
public class CartItemController {
    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PostMapping
    public ResponseEntity<?> create(@AuthenticationPrincipal UserDetails userDetails, @Valid @RequestBody CreateCartItemDto createCartItemDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.cartItemService.create(userDetails, createCartItemDto));
    }

    @GetMapping
    public ResponseEntity<?> findAll(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(this.cartItemService.findAll(userDetails));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") Long productId) {
        return ResponseEntity.status(HttpStatus.OK).body(this.cartItemService.findByProductId(userDetails, productId));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") Long productId, @Valid @RequestBody UpdateCartItemDto updateCartItemDto) {
        return ResponseEntity.status(HttpStatus.OK).body(this.cartItemService.update(userDetails, updateCartItemDto, productId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") Long productId) {
        this.cartItemService.delete(userDetails, productId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(
                "Продукт с ID: " + productId + " успешно удален!"
        ));
    }
}
