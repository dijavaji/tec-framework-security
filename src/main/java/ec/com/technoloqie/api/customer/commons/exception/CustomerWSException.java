package ec.com.technoloqie.api.customer.commons.exception;

public class CustomerWSException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public CustomerWSException() {
        super();
    }
    
	public CustomerWSException (String msg, Throwable nested) {
        super(msg, nested);
    }
    
	public CustomerWSException (String message) {
        super(message);
    }
    
	public CustomerWSException(Throwable nested) {
        super(nested);
	}
	
}	