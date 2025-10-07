package github.muhametshindenis.bitshop.modules.users.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 29.09.2025 September 2025
 */
public record CreateUserDeliveryAddressDto(
        @NotBlank(message = "Поле address является обязательным!")
        String deliveryAddress,
        Boolean isPrimary
) {
    public CreateUserDeliveryAddressDto {
        if (isPrimary == null) {
            isPrimary = true;
        }
    }
}
