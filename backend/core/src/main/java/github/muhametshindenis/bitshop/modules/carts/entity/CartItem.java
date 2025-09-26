package github.muhametshindenis.bitshop.modules.carts.entity;

import github.muhametshindenis.bitshop.common.base.BaseEntity;
import github.muhametshindenis.bitshop.modules.products.entity.Product;
import jakarta.persistence.*;
import lombok.*;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 19.09.2025 September 2025
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "cart_items")
public class CartItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @OneToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
