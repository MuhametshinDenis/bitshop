package github.muhametshindenis.bitshop.common.dto;

import java.time.Instant;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 18 August 2025
 */
public record ErrorResponse(
		String message,
		Integer status,
		Instant timestamp
) {
}
