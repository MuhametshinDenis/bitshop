package github.muhametshindenis.bitshop.modules.users.repository;

import github.muhametshindenis.bitshop.modules.users.entity.UserDeliveryAddress;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 29.09.2025 September 2025
 */
public interface UserDeliveryAddressRepository extends JpaRepository<UserDeliveryAddress, Long> {
    @Query("SELECT uda FROM UserDeliveryAddress uda WHERE uda.user.id = :userId AND uda.deliveryAddress = :deliveryAddress")
    Optional<UserDeliveryAddress> findByUserIdAndDeliveryAddress(@Param("userId") Long userId, @Param("deliveryAddress") String deliveryAddress);

    @Query("SELECT uda FROM UserDeliveryAddress uda WHERE uda.user.id = :userId AND uda.id = :deliveryAddressId")
    Optional<UserDeliveryAddress> findByUserIdAndDeliveryAddressId(@Param("userId") Long userId, @Param("deliveryAddressId") Long deliveryAddressId);

    @Query("SELECT uda FROM UserDeliveryAddress uda WHERE uda.user.id = :userId AND uda.isPrimary = :isPrimary")
    Optional<UserDeliveryAddress> findByUserIdAndIsPrimary(@Param("userId") Long userId, @Param("isPrimary") Boolean isPrimary);

    List<UserDeliveryAddress> findByUserId(Long userId);
}
