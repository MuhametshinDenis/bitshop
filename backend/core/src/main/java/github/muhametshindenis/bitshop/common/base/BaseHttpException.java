package github.muhametshindenis.bitshop.common.base;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 18 August 2025
 */
@Getter
public class BaseHttpException extends RuntimeException {
	private final HttpStatus httpStatus;
	
	public BaseHttpException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}
}
