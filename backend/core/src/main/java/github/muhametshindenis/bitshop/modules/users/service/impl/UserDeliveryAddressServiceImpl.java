package github.muhametshindenis.bitshop.modules.users.service.impl;

import github.muhametshindenis.bitshop.common.exception.ConflictException;
import github.muhametshindenis.bitshop.common.exception.NotFoundException;
import github.muhametshindenis.bitshop.common.exception.UnauthorizedException;
import github.muhametshindenis.bitshop.modules.users.dto.CreateUserDeliveryAddressDto;
import github.muhametshindenis.bitshop.modules.users.dto.UpdateUserDeliveryAddressDto;
import github.muhametshindenis.bitshop.modules.users.dto.UserDeliveryAddressResponseDto;
import github.muhametshindenis.bitshop.modules.users.entity.User;
import github.muhametshindenis.bitshop.modules.users.entity.UserDeliveryAddress;
import github.muhametshindenis.bitshop.modules.users.mapper.UserDeliveryAddressMapper;
import github.muhametshindenis.bitshop.modules.users.repository.UserDeliveryAddressRepository;
import github.muhametshindenis.bitshop.modules.users.service.UserDeliveryAddressService;
import github.muhametshindenis.bitshop.modules.users.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 29.09.2025 September 2025
 */
@Service
public class UserDeliveryAddressServiceImpl implements UserDeliveryAddressService {
    private final UserDeliveryAddressRepository userDeliveryAddressRepository;
    private final UserService userService;

    public UserDeliveryAddressServiceImpl(UserDeliveryAddressRepository userDeliveryAddressRepository, UserService userService) {
        this.userDeliveryAddressRepository = userDeliveryAddressRepository;
        this.userService = userService;
    }

    @Override
    public UserDeliveryAddressResponseDto create(UserDetails userDetails, CreateUserDeliveryAddressDto createUserDeliveryAddressDto) {
        User user = this.getUserFromUserDetails(userDetails);

        if (this.userDeliveryAddressRepository.findByUserIdAndDeliveryAddress(user.getId(), createUserDeliveryAddressDto.address()).isPresent()) {
            throw new ConflictException("У пользователя уже существует такой адрес!");
        }

        UserDeliveryAddress currentDeliveryAddress = UserDeliveryAddressMapper.toEntity(createUserDeliveryAddressDto, user);
        boolean isPrimary = createUserDeliveryAddressDto.isPrimary() != null
                ? createUserDeliveryAddressDto.isPrimary()
                : true;


        if (isPrimary) {
            userDeliveryAddressRepository.findByUserIdAndIsPrimary(user.getId(), true).ifPresent(address -> {
                address.setIsPrimary(false);
                this.userDeliveryAddressRepository.save(address);
            });

            currentDeliveryAddress.setIsPrimary(true);
        }

        return UserDeliveryAddressMapper.toResponse(this.userDeliveryAddressRepository.save(currentDeliveryAddress));
    }

    @Override
    public List<UserDeliveryAddressResponseDto> findAll(UserDetails userDetails) {
        User user = this.getUserFromUserDetails(userDetails);
        List<UserDeliveryAddress> userDeliveryAddresses = this.userDeliveryAddressRepository.findByUserId(user.getId());
        return userDeliveryAddresses.stream()
                .map(UserDeliveryAddressMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserDeliveryAddressResponseDto findById(UserDetails userDetails, Long addressId) {
        User user = this.getUserFromUserDetails(userDetails);
        UserDeliveryAddress userDeliveryAddress = this.userDeliveryAddressRepository.findByUserIdAndDeliveryAddressId(user.getId(), addressId)
                .orElseThrow(() -> new NotFoundException("Адрес доставки с ID: " + addressId + " не найден!"));
        return UserDeliveryAddressMapper.toResponse(userDeliveryAddress);
    }

    @Override
    public UserDeliveryAddressResponseDto update(UserDetails userDetails, UpdateUserDeliveryAddressDto updateUserDeliveryAddressDto, Long deliveryAddressId) {
        User user = this.getUserFromUserDetails(userDetails);
        UserDeliveryAddress userDeliveryAddress = this.userDeliveryAddressRepository.findByUserIdAndDeliveryAddressId(user.getId(), deliveryAddressId)
                .orElseThrow(() -> new NotFoundException("Адрес доставки с ID: " + deliveryAddressId + " не найден!"));

        UserDeliveryAddressMapper.updateFromDto(updateUserDeliveryAddressDto, userDeliveryAddress);
        this.deletePrimaryAddress(user.getId());

        return UserDeliveryAddressMapper.toResponse(this.userDeliveryAddressRepository.save(userDeliveryAddress));
    }

    @Override
    public void delete(UserDetails userDetails, Long addressId) {
        User user = this.getUserFromUserDetails(userDetails);
        UserDeliveryAddress userDeliveryAddress = this.userDeliveryAddressRepository.findByUserIdAndDeliveryAddressId(user.getId(), addressId)
                .orElseThrow(() -> new NotFoundException("Адрес доставки с ID: " + addressId + " не найден!"));
        this.userDeliveryAddressRepository.delete(userDeliveryAddress);
    }

    private void deletePrimaryAddress(Long userId) {
        userDeliveryAddressRepository.findByUserIdAndIsPrimary(userId, true).ifPresent(address -> {
            address.setIsPrimary(false);
            this.userDeliveryAddressRepository.save(address);
        });
    }

    private User getUserFromUserDetails(UserDetails userDetails) {
        String userEmail = userDetails.getUsername();

        if (userEmail == null || userEmail.isEmpty()) {
            throw new UnauthorizedException("Пользователь не авторизирован!");
        }

        return this.userService.findUserByEmail(userEmail).orElseThrow(() -> new NotFoundException("Пользователь с email: " + userEmail + " не найден"));
    }
}
