package github.muhametshindenis.bitshop.modules.favorites.controller;

import github.muhametshindenis.bitshop.common.dto.ApiResponse;
import github.muhametshindenis.bitshop.modules.favorites.dto.CreateFavoriteDto;
import github.muhametshindenis.bitshop.modules.favorites.service.FavoriteService;
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
public class FavoriteController {
    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CreateFavoriteDto createFavoriteDto, @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.favoriteService.create(createFavoriteDto, userDetails));
    }

    @GetMapping
    public ResponseEntity<?> findAll(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(this.favoriteService.findAll(userDetails));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long productId, @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(this.favoriteService.findById(productId, userDetails));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAll(@AuthenticationPrincipal UserDetails userDetails) {
        this.favoriteService.deleteAll(userDetails);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(
                "Все продукта из избранного удалены!"
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long productId, @AuthenticationPrincipal UserDetails userDetails) {
        this.favoriteService.deleteById(productId, userDetails);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(
                "Продукт c ID: " + productId + " удален из избранного!"
        ));
    }
}
