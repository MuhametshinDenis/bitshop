package github.muhametshindenis.bitshop.modules.users.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 29.09.2025 September 2025
 */
public record UserDeliveryAddressResponseDto(
        String deliveryAddress,
        Boolean isPrimary
) {
}
