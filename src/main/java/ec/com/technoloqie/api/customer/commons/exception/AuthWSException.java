package ec.com.technoloqie.api.customer.commons.exception;

public class AuthWSException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public AuthWSException() {
        super();
    }
    
	public AuthWSException (String msg, Throwable nested) {
        super(msg, nested);
    }
    
	public AuthWSException (String message) {
        super(message);
    }
    
	public AuthWSException(Throwable nested) {
        super(nested);
	}
	
}	