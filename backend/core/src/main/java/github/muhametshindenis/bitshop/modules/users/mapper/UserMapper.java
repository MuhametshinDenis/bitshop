package github.muhametshindenis.bitshop.modules.users.mapper;

import github.muhametshindenis.bitshop.modules.users.dto.CreateUserDto;
import github.muhametshindenis.bitshop.modules.users.dto.UpdateUserDto;
import github.muhametshindenis.bitshop.modules.users.dto.UserResponseDto;
import github.muhametshindenis.bitshop.modules.users.entity.User;
import github.muhametshindenis.bitshop.modules.users.entity.UserDeliveryAddress;

import java.util.Optional;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 18.09.2025 September 2025
 */
public class UserMapper {
    public static User toEntity(CreateUserDto user) {
        return User.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }

    public static UserResponseDto toResponse(User user) {
        String primaryAddress = user.getUserDeliveryAddresses().stream()
                .filter(UserDeliveryAddress::getIsPrimary)
                .map(UserDeliveryAddress::getDeliveryAddress)
                .findFirst()
                .orElse(null);

        return new UserResponseDto(
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getAvatarUrl(),
                primaryAddress
        );
    }

    /**
     * Обновляет поля сущности User на основе данных из UpdateUserDto.
     *
     * Этот метод только маппит значения из DTO в сущность.
     * Логика бизнес-процессов (например, обновление аватара через StorageService)
     * обрабатывается в сервисе, а не здесь.
     *
     * @implNote Специально используется явное обновление через Optional.ofNullable(),
     * чтобы сохранить читаемость и избежать сложной динамической логики.
     */
    public static void updateFromDto(UpdateUserDto updateUserDto, User user) {
        Optional.ofNullable(updateUserDto.firstName()).ifPresent(user::setFirstName);
        Optional.ofNullable(updateUserDto.lastName()).ifPresent(user::setLastName);
    }
}
