package ec.com.technoloqie.fwk.security.api.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class AuthCredential {
	
	private String email;	//se puede utilizar UsernameOrEmail
	private String password;
}
