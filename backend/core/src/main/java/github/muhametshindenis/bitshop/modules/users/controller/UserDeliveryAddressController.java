package github.muhametshindenis.bitshop.modules.users.controller;

import github.muhametshindenis.bitshop.common.dto.ResponseMessage;
import github.muhametshindenis.bitshop.modules.users.dto.CreateUserDeliveryAddressDto;
import github.muhametshindenis.bitshop.modules.users.dto.UpdateUserDeliveryAddressDto;
import github.muhametshindenis.bitshop.modules.users.entity.UserDeliveryAddress;
import github.muhametshindenis.bitshop.modules.users.service.UserDeliveryAddressService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 29.09.2025 September 2025
 */
@RestController
@RequestMapping("/api/v1/users/delivery-addresses")
public class UserDeliveryAddressController {
    private final UserDeliveryAddressService userDeliveryAddressService;

    public UserDeliveryAddressController(UserDeliveryAddressService userDeliveryAddressService) {
        this.userDeliveryAddressService = userDeliveryAddressService;
    }

    @PostMapping
    public ResponseEntity<?> create(@AuthenticationPrincipal UserDetails userDetails,
                                    @Valid @RequestBody CreateUserDeliveryAddressDto createUserDeliveryAddressDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userDeliveryAddressService.create(userDetails, createUserDeliveryAddressDto));
    }

    @GetMapping
    public ResponseEntity<?> findAll(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(this.userDeliveryAddressService.findAll(userDetails));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@AuthenticationPrincipal UserDetails userDetails,
                                      @PathVariable("id") Long orderId) {
        return ResponseEntity.status(HttpStatus.OK).body(this.userDeliveryAddressService.findById(userDetails, orderId));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@AuthenticationPrincipal UserDetails userDetails,
                                    @RequestBody UpdateUserDeliveryAddressDto updateUserDeliveryAddressDto,
                                    @PathVariable("id") Long orderId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.userDeliveryAddressService.update(userDetails, updateUserDeliveryAddressDto, orderId)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@AuthenticationPrincipal UserDetails userDetails,
                                    @PathVariable("id") Long orderId) {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(
                "Адрес с ID: " + orderId + " успешно удален!"
        ));
    }
}
