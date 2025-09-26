package github.muhametshindenis.bitshop.modules.carts.service;

import github.muhametshindenis.bitshop.modules.carts.dto.CartResponseDto;
import github.muhametshindenis.bitshop.modules.carts.entity.Cart;
import github.muhametshindenis.bitshop.modules.users.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 19.09.2025 September 2025
 */
public interface CartService {
    Cart findCartByUser(User user);

    CartResponseDto findCart(UserDetails userDetails);

    void delete(UserDetails userDetails);
}
