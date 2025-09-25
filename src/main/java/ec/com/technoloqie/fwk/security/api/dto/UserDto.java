package ec.com.technoloqie.fwk.security.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

	private String username;
	private String email;
	private String password;
	private String createdBy;
	private PersonDto person;

}
