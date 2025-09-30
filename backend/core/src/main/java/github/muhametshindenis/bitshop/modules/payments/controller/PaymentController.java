package github.muhametshindenis.bitshop.modules.payments.controller;

import github.muhametshindenis.bitshop.modules.payments.dto.InitiatePaymentDto;
import github.muhametshindenis.bitshop.modules.payments.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 28.09.2025 September 2025
 */
@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<?> initiate(@AuthenticationPrincipal UserDetails userDetails, @RequestBody InitiatePaymentDto initiatePaymentDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.paymentService.initiate(userDetails, initiatePaymentDto));
    }
}
