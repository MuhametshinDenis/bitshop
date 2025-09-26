package github.muhametshindenis.bitshop.modules.categories.entity;

import github.muhametshindenis.bitshop.common.base.BaseEntity;
import github.muhametshindenis.bitshop.modules.products.entity.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 18.09.2025 September 2025
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "categories")
public class Category extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "category")
    private Set<Product> products;

    @PreRemove
    private void preRemove() {
        for (Product product : products) {
            product.setCategory(null);
        }
    }
}
