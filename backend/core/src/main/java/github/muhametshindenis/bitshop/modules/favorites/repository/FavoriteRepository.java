package github.muhametshindenis.bitshop.modules.favorites.repository;

import github.muhametshindenis.bitshop.modules.favorites.entity.Favorite;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 19.09.2025 September 2025
 */
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    boolean existsByUserIdAndProductId(Long userId, Long productId);

    List<Favorite> findAllByUserId(Long userId);

    Optional<Favorite> findByUserIdAndProductId(Long userId, Long productId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Favorite f WHERE f.user.id = :userId")
    void deleteByUserId(@Param("userId") Long userId);
}
