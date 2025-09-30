package github.muhametshindenis.bitshop.modules.carts.entity;

import github.muhametshindenis.bitshop.common.base.BaseEntity;
import github.muhametshindenis.bitshop.modules.users.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
@Table(name = "carts")
public class Cart extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Currency currency;

    @Override
    protected void initDefaults() {
        if (currency == null) {
            currency = Currency.RUB;
        }
    }
}
