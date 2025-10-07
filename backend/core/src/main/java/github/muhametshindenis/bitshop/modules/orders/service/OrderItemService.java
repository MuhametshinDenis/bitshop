package github.muhametshindenis.bitshop.modules.orders.service;

import github.muhametshindenis.bitshop.modules.orders.dto.CreateOrderItemDto;
import github.muhametshindenis.bitshop.modules.orders.dto.OrderItemResponseDto;
import github.muhametshindenis.bitshop.modules.orders.entity.Order;
import github.muhametshindenis.bitshop.modules.orders.entity.OrderItem;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 29.09.2025 September 2025
 */
public interface OrderItemService {
    OrderItemResponseDto create(UserDetails userDetails, CreateOrderItemDto createOrderItemDto);
    List<OrderItem> createAll(UserDetails userDetails, List<CreateOrderItemDto> createOrderItemDtos, Order order);
    List<OrderItemResponseDto> findAll(UserDetails userDetails);
}
