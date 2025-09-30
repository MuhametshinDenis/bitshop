package github.muhametshindenis.bitshop.modules.orders.entity;

import github.muhametshindenis.bitshop.common.base.BaseEntity;
import github.muhametshindenis.bitshop.modules.users.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.engine.internal.Cascade;

import java.util.List;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 29.09.2025 September 2025
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Currency currency;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false, name = "delivery_address")
    private String deliveryAddress;

    @Override
    protected void initDefaults() {
        if (currency == null) {
            currency = Currency.RUB;
        }
    }
}
