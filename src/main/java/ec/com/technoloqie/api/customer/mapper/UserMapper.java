package ec.com.technoloqie.api.customer.mapper;

import ec.com.technoloqie.api.customer.dto.SignUpDto;
import ec.com.technoloqie.api.customer.dto.UserDto;
import ec.com.technoloqie.api.customer.entity.User;

public class UserMapper {
	
	public static User mapToUser(UserDto userDto) {
		return new User(
				);
	}
	
	public static UserDto mapToUserDto(User user) {
		return new UserDto();
	}

	public static User mapToUser(SignUpDto signUpDto) {
		return new User();
	}

}
