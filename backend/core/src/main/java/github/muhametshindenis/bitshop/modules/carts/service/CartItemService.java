package github.muhametshindenis.bitshop.modules.carts.service;

import github.muhametshindenis.bitshop.modules.carts.dto.CartItemResponseDto;
import github.muhametshindenis.bitshop.modules.carts.dto.CreateCartItemDto;
import github.muhametshindenis.bitshop.modules.carts.dto.UpdateCartItemDto;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 23.09.2025 September 2025
 */
public interface CartItemService {
    CartItemResponseDto create(UserDetails userDetails, CreateCartItemDto createCartItemDto);
    List<CartItemResponseDto> findAll(UserDetails userDetails);
    CartItemResponseDto findByProductId(UserDetails userDetails, Long productId);
    CartItemResponseDto update(UserDetails userDetails, UpdateCartItemDto updateCartItemDto, Long productId);
    void delete(UserDetails userDetails, Long productId);
}
