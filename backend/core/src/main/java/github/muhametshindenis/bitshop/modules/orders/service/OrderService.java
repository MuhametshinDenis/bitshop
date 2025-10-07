package github.muhametshindenis.bitshop.modules.orders.service;

import github.muhametshindenis.bitshop.modules.orders.dto.CreateOrderDto;
import github.muhametshindenis.bitshop.modules.orders.dto.OrderResponseDto;
import github.muhametshindenis.bitshop.modules.orders.dto.UpdateOrderDto;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 29.09.2025 September 2025
 */
public interface OrderService {
    OrderResponseDto create(UserDetails userDetails, CreateOrderDto createOrderDto);
    List<OrderResponseDto> findAll(UserDetails userDetails);
    OrderResponseDto findById(UserDetails userDetails, Long orderId);
    OrderResponseDto update(UserDetails userDetails, Long orderId, UpdateOrderDto updateOrderDto);
}
