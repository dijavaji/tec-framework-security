package ec.com.technoloqie.framework.security.ejb.commons.log;

import org.apache.log4j.Logger;

/**
 * Clase para el registro de los mensajes de una determinada transaccion en la ejecucion del programa
 * @author technoloqie
 *
 */
public final class SecurityLog {
	
	static final Logger logger = Logger.getLogger(SecurityLog.class);
	private static SecurityLog instance = new SecurityLog();
	private static String valueClazz;
	
	public static SecurityLog getLog() {
		if (instance == null) {
		    instance = new SecurityLog();
		}
		return instance;
	}
	 
	@SuppressWarnings("rawtypes")
	public static SecurityLog getLog(Class clazz) {
		if (instance == null) {
		    instance = new SecurityLog();
		}
		valueClazz = clazz.getName();
		return instance;
    } 
	
	public void info(String parameter){
		logger.info(parameter);
	}
	
	public void debug(String parameter){
		logger.debug("This is debug : " + parameter);
		}
		
	public void warn(String parameter){
		logger.warn("This is warn : " + parameter);
		}
	
	public void error(String parameter){
		logger.error(parameter);
	}
	
	public void fatal(String parameter){
		logger.fatal("This is fatal : " + parameter);
	}

	public void error(String string, Exception e) {
		valueClazz = valueClazz != null ? "[" + valueClazz + "] ": ""; 
		error(valueClazz + string + e.getMessage());
	}

}
