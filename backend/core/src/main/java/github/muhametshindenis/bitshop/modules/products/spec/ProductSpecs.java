package github.muhametshindenis.bitshop.modules.products.spec;

import github.muhametshindenis.bitshop.modules.products.entity.Product;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 19.09.2025 September 2025
 */
public class ProductSpecs {
    public static Specification<Product> hasCategoryId(Long categoryId) {
        if (categoryId == null) {
            return (root, query, criteriaBuilder) -> null;
        }

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("category").get("id"), categoryId);
    }

    public static Specification<Product> byMinPrice(BigDecimal minPrice) {
        if (minPrice == null) {
            return (root, query, criteriaBuilder) -> null;
        }

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    public static Specification<Product> byMaxPrice(BigDecimal maxPrice) {
        if (maxPrice == null) {
            return (root, query, criteriaBuilder) -> null;
        }

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
    }
}
