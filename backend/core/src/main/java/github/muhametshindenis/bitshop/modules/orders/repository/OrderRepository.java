package github.muhametshindenis.bitshop.modules.orders.repository;

import github.muhametshindenis.bitshop.modules.orders.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 29.09.2025 September 2025
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}
