package github.muhametshindenis.bitshop.modules.carts.repository;

import github.muhametshindenis.bitshop.modules.carts.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 19.09.2025 September 2025
 */
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserId(Long userId);
}
