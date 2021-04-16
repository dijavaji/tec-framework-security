package ec.com.technoloqie.framework.security.web.controller;

import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class BaseController {
	protected FacesContext getContext() {
		return FacesContext.getCurrentInstance();
	}
	@SuppressWarnings("rawtypes")
	protected Map getRequestMap() {
		return getContext().getExternalContext().getRequestMap();
	}
	protected HttpSession getSession() {
		return (HttpSession) getContext().getExternalContext().getSession(false);
	}
	@SuppressWarnings("unchecked")
	protected Object evaluateEL(String elExpression, Class beanClazz) {
		return getContext().getApplication().evaluateExpressionGet(getContext(), elExpression,beanClazz);
	}
	
	public static final String SYSTEM_ERROR = "System error ...";
}
