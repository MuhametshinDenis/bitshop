package github.muhametshindenis.bitshop.modules.users.controller;

import github.muhametshindenis.bitshop.modules.users.service.UserDeliveryAddressService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 29.09.2025 September 2025
 */
@RestController
@RequestMapping("api/v1/users/addresses")
public class UserDeliveryAddressController {
    private final UserDeliveryAddressService userDeliveryAddressService;

    public UserDeliveryAddressController(UserDeliveryAddressService userDeliveryAddressService) {
        this.userDeliveryAddressService = userDeliveryAddressService;
    }
}
