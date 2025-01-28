package ec.com.technoloqie.fwk.security.api.mapper;

import ec.com.technoloqie.fwk.security.api.dto.SignUpDto;
import ec.com.technoloqie.fwk.security.api.dto.UserDto;
import ec.com.technoloqie.fwk.security.api.entity.User;

public class UserMapper {
	
	public static User mapToUser(UserDto userDto) {
		return new User(
				);
	}
	
	public static UserDto mapToUserDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setUsername(user.getUsername());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPass());
		userDto.setPerson(PersonMapper.mapToPersonDto(user.getPerson()));
		userDto.setCreatedBy(user.getCreatedBy());
		return userDto;
	}

	public static User mapToUser(SignUpDto signUpDto) {
		User user = new User();
		user.setUsername(signUpDto.getUsername());
		user.setEmail(signUpDto.getEmail());
		user.setPass(signUpDto.getPassword());
		user.setCreatedBy(signUpDto.getCreatedBy());
		return user;
	}

}
