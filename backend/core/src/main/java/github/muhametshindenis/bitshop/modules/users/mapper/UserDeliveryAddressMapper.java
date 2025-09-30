package github.muhametshindenis.bitshop.modules.users.mapper;

import github.muhametshindenis.bitshop.modules.users.dto.CreateUserDeliveryAddressDto;
import github.muhametshindenis.bitshop.modules.users.dto.UpdateUserDeliveryAddressDto;
import github.muhametshindenis.bitshop.modules.users.dto.UserDeliveryAddressResponseDto;
import github.muhametshindenis.bitshop.modules.users.entity.User;
import github.muhametshindenis.bitshop.modules.users.entity.UserDeliveryAddress;

import java.util.Optional;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 29.09.2025 September 2025
 */
public class UserDeliveryAddressMapper {
    public static UserDeliveryAddress toEntity(CreateUserDeliveryAddressDto createUserDeliveryAddressDto, User user) {
        return UserDeliveryAddress.builder()
                .deliveryAddress(createUserDeliveryAddressDto.address())
                .isPrimary(createUserDeliveryAddressDto.isPrimary())
                .user(user)
                .build();
    }

    public static UserDeliveryAddressResponseDto toResponse(UserDeliveryAddress userDeliveryAddress) {
        return new UserDeliveryAddressResponseDto(
                userDeliveryAddress.getDeliveryAddress(),
                userDeliveryAddress.getIsPrimary()
        );
    }

    public static void updateFromDto(UpdateUserDeliveryAddressDto updateUserDeliveryAddressDto, UserDeliveryAddress userDeliveryAddress) {
        Optional.ofNullable(updateUserDeliveryAddressDto.address()).ifPresent(userDeliveryAddress::setDeliveryAddress);
        Optional.ofNullable(updateUserDeliveryAddressDto.isPrimary()).ifPresent(userDeliveryAddress::setIsPrimary);
    }
}
