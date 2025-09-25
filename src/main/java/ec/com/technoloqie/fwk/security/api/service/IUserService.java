package ec.com.technoloqie.fwk.security.api.service;

import java.util.Collection;

import ec.com.technoloqie.fwk.security.api.commons.exception.AuthWSException;
import ec.com.technoloqie.fwk.security.api.dto.AuthCredential;
import ec.com.technoloqie.fwk.security.api.dto.JWTAuthResponse;
import ec.com.technoloqie.fwk.security.api.dto.SignUpDto;
import ec.com.technoloqie.fwk.security.api.dto.UserDto;

public interface IUserService {
	
    Collection<UserDto> getListUsers() throws AuthWSException;
	UserDto createUser(SignUpDto user) throws AuthWSException;
	//CustomerDto getUserId(Integer code);
	//CustomerDto updateUser(CustomerDto customer, int id);
	//void deleteUser(Integer code);
	JWTAuthResponse login(AuthCredential loginDto) throws AuthWSException;
	UserDto getUserByUsernameOrEmail(String username, String email) throws AuthWSException;
}
