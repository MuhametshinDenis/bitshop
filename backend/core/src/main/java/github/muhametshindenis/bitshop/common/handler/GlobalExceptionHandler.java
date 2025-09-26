package github.muhametshindenis.bitshop.common.handler;

import github.muhametshindenis.bitshop.common.base.BaseHttpException;
import github.muhametshindenis.bitshop.common.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 18 August 2025
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
		StringBuilder message = new StringBuilder("Validation failed: ");
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String errorMessage = error.getDefaultMessage();
			message.append(errorMessage);
		});
		
		ErrorResponse response = new ErrorResponse(
				message.toString(),
				HttpStatus.BAD_REQUEST.value(),
				Instant.now()
		);
		return ResponseEntity.badRequest().body(response);
	}
	
	@ExceptionHandler(BaseHttpException.class)
	public ResponseEntity<ErrorResponse> handleBaseHttpException(BaseHttpException e) {
		ErrorResponse response = new ErrorResponse(
				e.getMessage(),
				e.getHttpStatus().value(),
				Instant.now()
		);
		return ResponseEntity.status(e.getHttpStatus()).body(response);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGenericException(Exception e) {
		ErrorResponse response = new ErrorResponse(
				"Internal server error: " + e.getClass().getSimpleName(),
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				Instant.now()
		);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}
}
