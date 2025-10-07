package github.muhametshindenis.bitshop.modules.orders.dto;

import jakarta.validation.constraints.NotNull;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 01.10.2025 October 2025
 */
public record CreateOrderItemDto(
        @NotNull(message = "Поле productId является обязательным!")
        Long productId,
        @NotNull(message = "Поле quantity является обязательным!")
        Long quantity
) {
}
