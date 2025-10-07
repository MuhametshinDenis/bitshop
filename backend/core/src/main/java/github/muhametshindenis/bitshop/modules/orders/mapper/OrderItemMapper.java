package github.muhametshindenis.bitshop.modules.orders.mapper;

import github.muhametshindenis.bitshop.modules.orders.dto.OrderItemResponseDto;
import github.muhametshindenis.bitshop.modules.orders.entity.OrderItem;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 03 October 2025
 */
public class OrderItemMapper {
    public static OrderItemResponseDto toResponse(OrderItem orderItem) {
        return new OrderItemResponseDto(
                orderItem.getId(),
                orderItem.getName(),
                orderItem.getProductId(),
                orderItem.getPrice(),
                orderItem.getQuantity(),
                orderItem.getTotalPrice()
        );
    }
}
