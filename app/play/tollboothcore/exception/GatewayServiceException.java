package play.tollboothcore.exception;

public class GatewayServiceException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public GatewayServiceException() {
		super();
	}

	public GatewayServiceException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public GatewayServiceException(String message) {
		super(message);
	}

	public GatewayServiceException(Throwable throwable) {
		super(throwable);
	}

}
