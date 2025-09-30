package github.muhametshindenis.bitshop.modules.users.entity;

import github.muhametshindenis.bitshop.common.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

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
@Table(name = "user_addresses")
public class UserDeliveryAddress extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "delivery_address", nullable = false)
    private String deliveryAddress;

    @Column(name = "is_primary")
    private Boolean isPrimary = false;
}
