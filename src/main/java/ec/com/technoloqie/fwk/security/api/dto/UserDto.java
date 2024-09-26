package ec.com.technoloqie.fwk.security.api.dto;

import lombok.Data;

@Data
public class UserDto {

	private String username;
	private String email;
	private String password;
	private String createdBy;
	private PersonDto person;

}
