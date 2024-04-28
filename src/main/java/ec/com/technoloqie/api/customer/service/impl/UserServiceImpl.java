package ec.com.technoloqie.api.customer.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.com.technoloqie.api.customer.commons.exception.AuthWSException;
import ec.com.technoloqie.api.customer.dto.SignUpDto;
import ec.com.technoloqie.api.customer.dto.UserDto;
import ec.com.technoloqie.api.customer.entity.User;
import ec.com.technoloqie.api.customer.mapper.UserMapper;
import ec.com.technoloqie.api.customer.repository.IUserRepository;
import ec.com.technoloqie.api.customer.service.IUserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements IUserService{
	
	@Autowired
	private IUserRepository userRepository;

	@Override
	public Boolean existsByUsername(String username) {
		return this.userRepository.existsByUsername(username);
	}

	@Override
	public Boolean existsByEmail(String email) {
		return this.userRepository.existsByEmail(email);
	}

	@Override
	public Collection<UserDto> getListUsers() {
		return null;
	}

	@Override
	public UserDto createUser(SignUpDto signUpDto) {
		try {
			User user = UserMapper.mapToUser(signUpDto);
			log.info("inserta usuario {}", user.getUsername());
			return UserMapper.mapToUserDto(userRepository.save(user));
		}catch(Exception e) {
			log.error("Error al momento de crear usuario {}", e);
			throw new AuthWSException("Error al momento de crear usuario",e);
		}
	}
	

}
