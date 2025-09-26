package github.muhametshindenis.bitshop.modules.carts.controller;

import github.muhametshindenis.bitshop.common.dto.ApiResponse;
import github.muhametshindenis.bitshop.modules.carts.service.CartService;
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
@RequestMapping("/api/v1/carts")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<?> getCart(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(cartService.findCart(userDetails));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteCart(@AuthenticationPrincipal UserDetails userDetails) {
        this.cartService.delete(userDetails);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(
                "Корзина успешно очищена!"
        ));
    }
}
