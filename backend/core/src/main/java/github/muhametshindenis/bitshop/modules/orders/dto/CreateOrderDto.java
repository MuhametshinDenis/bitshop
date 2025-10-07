package github.muhametshindenis.bitshop.modules.orders.dto;

import github.muhametshindenis.bitshop.modules.orders.entity.Currency;
import github.muhametshindenis.bitshop.modules.orders.entity.Status;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 29.09.2025 September 2025
 */
public record CreateOrderDto(
        @NotNull(message = "Поле deliveryAddressId является обязательным!")
        Long deliveryAddressId,

        @NotNull(message = "Поле currency является обязательным!")
        Currency currency,

        @NotNull(message = "Поле status является обязательным!")
        Status status,

        @NotEmpty(message = "Поле orderItems является обязательным!")
        @Valid
        List<CreateOrderItemDto> orderItems
) {
}
