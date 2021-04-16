package ec.com.technoloqie.framework.security.web.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;

import ec.com.technoloqie.framework.security.ejb.commons.model.User;
import ec.com.technoloqie.framework.security.ejb.persistence.business.UserManagerLocal;

/**
 * 
 * @author root
 *
 */
@Named
@RequestScoped
public class LoginController extends BaseController{
	
	private static final String INVALID_USERNAME_OR_PASSWORD = "Invalid Username or password";
	
	@EJB
	private UserManagerLocal userManager;
	
	public String login() {	//https://github.com/Apress/pro-jsf-html5
		User currentAppUser = (User) evaluateEL("#{user}", User.class);
		try {
		User appUser = userManager.getUser(currentAppUser.getUserName(), currentAppUser.getPass());
		if (appUser == null) {
			getContext().addMessage(null, new FacesMessage(INVALID_USERNAME_OR_PASSWORD));
			return null;
		}
		//Set Necessary user information
		currentAppUser.setUserName(appUser.getUserName());
		currentAppUser.setIsEnable(appUser.getIsEnable());
		currentAppUser.setUserProfileCol(appUser.getUserProfileCol());
		} catch (Exception ex) {
			Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
			getContext().addMessage(null, new FacesMessage(SYSTEM_ERROR));
			return null;
		}
		return "/protected/weather";
	}

}
