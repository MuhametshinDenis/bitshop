package github.muhametshindenis.bitshop.modules.orders.mapper;

import github.muhametshindenis.bitshop.modules.orders.dto.OrderResponseDto;
import github.muhametshindenis.bitshop.modules.orders.entity.Order;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 03 October 2025
 */
public class OrderMapper {
    public static OrderResponseDto toResponse(Order order) {
        return new OrderResponseDto(
            order.getId(),
                order.getDeliveryAddress(),
                order.getCurrency(),
                order.getStatus(),
                order.getOrderItems().stream().map(OrderItemMapper::toResponse).toList()
        );
    }
}
