package github.muhametshindenis.bitshop.modules.orders.entity;

import github.muhametshindenis.bitshop.common.base.BaseEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 29.09.2025 September 2025
 */
@Entity
@Table(name = "order_items")
public class OrderItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(nullable = false, name = "product_id")
    private Long productId;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Long quantity;

    @Column(nullable = false)
    private BigDecimal totalPrice;

    @Override
    protected void initDefaults() {
        if (totalPrice == null) {
            totalPrice = price.multiply(BigDecimal.valueOf(quantity));
        }
    }
}
