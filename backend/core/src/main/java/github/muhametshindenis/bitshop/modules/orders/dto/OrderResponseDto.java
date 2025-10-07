package github.muhametshindenis.bitshop.modules.orders.dto;

import github.muhametshindenis.bitshop.modules.orders.entity.Currency;
import github.muhametshindenis.bitshop.modules.orders.entity.Status;

import java.util.List;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 29.09.2025 September 2025
 */
public record OrderResponseDto(
    Long id,
    String deliveryAddress,
    Currency currency,
    Status status,
    List<OrderItemResponseDto> orderItems
) {
}
