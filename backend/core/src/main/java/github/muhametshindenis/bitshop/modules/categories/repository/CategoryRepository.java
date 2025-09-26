package github.muhametshindenis.bitshop.modules.categories.repository;

import github.muhametshindenis.bitshop.modules.categories.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 18.09.2025 September 2025
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
}
