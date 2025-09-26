package github.muhametshindenis.bitshop.modules.carts.repository;

import github.muhametshindenis.bitshop.modules.carts.entity.CartItem;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 19.09.2025 September 2025
 */
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByProductId(Long productId);

    @Query("SELECT ci FROM CartItem ci WHERE ci.cart.user.id = :userId AND ci.product.id = :productId")
    Optional<CartItem> findByUserIdAndProductId(@Param("userId") Long userId, @Param("productId") Long productId);

    @Query("SELECT ci FROM CartItem ci WHERE ci.cart.user.id = :userId")
    List<CartItem> findAllByUserId(@Param("userId") Long userId);
}
