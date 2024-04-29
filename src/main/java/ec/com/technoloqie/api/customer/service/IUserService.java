package ec.com.technoloqie.api.customer.service;

import java.util.Collection;

import ec.com.technoloqie.api.customer.commons.exception.AuthWSException;
import ec.com.technoloqie.api.customer.dto.SignUpDto;
import ec.com.technoloqie.api.customer.dto.UserDto;

public interface IUserService {
	
    Collection<UserDto> getListUsers() throws AuthWSException;
	UserDto createUser(SignUpDto user) throws AuthWSException;
	//CustomerDto getUserId(Integer code);
	//CustomerDto updateUser(CustomerDto customer, int id);
	//void deleteUser(Integer code);
}
