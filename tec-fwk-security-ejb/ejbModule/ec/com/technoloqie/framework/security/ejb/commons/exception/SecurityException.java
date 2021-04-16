package ec.com.technoloqie.framework.security.ejb.commons.exception;


/**
 * Clase para el tratamiento de los errores que pueden ocurrir en la ejecucion del codigo
 * @author root
 *
 */
@SuppressWarnings("serial")
public class SecurityException extends RuntimeException{
	public SecurityException() {
        super();
    }
    
	public SecurityException(String msg, Throwable nested) {
        super(msg, nested);
    }
    
	public SecurityException(String message) {
        super(message);
    }
    
	public SecurityException(Throwable nested) {
        super(nested);
    }
}
