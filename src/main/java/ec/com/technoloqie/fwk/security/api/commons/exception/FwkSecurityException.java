package ec.com.technoloqie.fwk.security.api.commons.exception;

public class FwkSecurityException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public FwkSecurityException() {
        super();
    }
    
	public FwkSecurityException (String msg, Throwable nested) {
        super(msg, nested);
    }
    
	public FwkSecurityException (String message) {
        super(message);
    }
    
	public FwkSecurityException(Throwable nested) {
        super(nested);
	}
	
}
