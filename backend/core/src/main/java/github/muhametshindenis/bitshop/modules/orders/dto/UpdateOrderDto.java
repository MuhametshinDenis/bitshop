package github.muhametshindenis.bitshop.modules.orders.dto;

import github.muhametshindenis.bitshop.modules.orders.entity.Status;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 01.10.2025 October 2025
 */
public record UpdateOrderDto(Status status, Long deliveryAddress) {
}
