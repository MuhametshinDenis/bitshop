package github.muhametshindenis.bitshop.modules.payments.dto;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 28.09.2025 September 2025
 */
public record PaymentResponseDto(
    String status,
    String message,
    String sessionId,
    String sessionUrl
) {
}
