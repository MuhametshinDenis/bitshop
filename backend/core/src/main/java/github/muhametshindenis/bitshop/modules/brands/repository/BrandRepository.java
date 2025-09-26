package github.muhametshindenis.bitshop.modules.brands.repository;

import github.muhametshindenis.bitshop.modules.brands.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 19.09.2025 September 2025
 */
public interface BrandRepository extends JpaRepository<Brand, Long> {
    Optional<Brand> findByName(String name);
}
