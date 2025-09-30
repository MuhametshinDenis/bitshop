package github.muhametshindenis.bitshop.modules.users.service;

import github.muhametshindenis.bitshop.modules.users.dto.CreateUserDeliveryAddressDto;
import github.muhametshindenis.bitshop.modules.users.dto.UpdateUserDeliveryAddressDto;
import github.muhametshindenis.bitshop.modules.users.dto.UserDeliveryAddressResponseDto;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 29.09.2025 September 2025
 */
public interface UserDeliveryAddressService {
    UserDeliveryAddressResponseDto create(UserDetails userDetails, CreateUserDeliveryAddressDto createUserDeliveryAddressDto);

    List<UserDeliveryAddressResponseDto> findAll(UserDetails userDetails);

    UserDeliveryAddressResponseDto findById(UserDetails userDetails, Long addressId);

    UserDeliveryAddressResponseDto update(UserDetails userDetails, UpdateUserDeliveryAddressDto updateUserDeliveryAddressDto, Long deliveryAddressId);

    void delete(UserDetails userDetails, Long addressId);
}
