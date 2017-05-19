package play.tollboothcore.exception;

import org.springframework.validation.Errors;

public class GatewayServiceValidationException extends GatewayServiceException {
	private static final long serialVersionUID = 1L;

	private final Errors errors;
	
	public GatewayServiceValidationException(String message, Errors errors) {
		super(message);
		this.errors = errors;
	}

	public Errors getErrors() {
		return errors;
	}

	
}
