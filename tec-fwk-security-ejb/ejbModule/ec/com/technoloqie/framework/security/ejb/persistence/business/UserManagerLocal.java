package ec.com.technoloqie.framework.security.ejb.persistence.business;

import javax.ejb.LocalBean;

import ec.com.technoloqie.framework.security.ejb.commons.model.User;

@LocalBean
public interface UserManagerLocal {
	User getUser(String userName, String password) throws SecurityException;
	
}
