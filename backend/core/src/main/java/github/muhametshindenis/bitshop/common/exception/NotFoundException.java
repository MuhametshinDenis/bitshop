package github.muhametshindenis.bitshop.common.exception;

import github.muhametshindenis.bitshop.common.base.BaseHttpException;
import org.springframework.http.HttpStatus;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 18 August 2025
 */
public class NotFoundException extends BaseHttpException {
	public NotFoundException(String message) {
		super(message, HttpStatus.NOT_FOUND);
	}
}
