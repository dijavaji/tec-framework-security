package ec.com.technoloqie.api.customer.dto;

import lombok.Data;

@Data
public class LoginDto {

	private String usernameOrEmail;
	private String password;
}
