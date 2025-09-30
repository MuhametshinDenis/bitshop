package github.muhametshindenis.bitshop.modules.payments.service;

import github.muhametshindenis.bitshop.modules.payments.dto.InitiatePaymentDto;
import github.muhametshindenis.bitshop.modules.payments.dto.PaymentResponseDto;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 28.09.2025 September 2025
 */

public interface PaymentService {

    PaymentResponseDto initiate(UserDetails userDetails,InitiatePaymentDto initiatePaymentDto);
}
