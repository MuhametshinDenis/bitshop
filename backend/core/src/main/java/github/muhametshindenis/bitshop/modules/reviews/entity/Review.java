package github.muhametshindenis.bitshop.modules.reviews.entity;

import github.muhametshindenis.bitshop.common.base.BaseEntity;
import github.muhametshindenis.bitshop.modules.products.entity.Product;
import github.muhametshindenis.bitshop.modules.users.entity.User;
import jakarta.persistence.*;
import lombok.*;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 07 October 2025
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "review")
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(nullable = false, name = "product_id")
    private Product product;

    @ManyToOne()
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    @Column(length = 500)
    private String comment;

    @Column(nullable = false)
    private Float rating;
}
