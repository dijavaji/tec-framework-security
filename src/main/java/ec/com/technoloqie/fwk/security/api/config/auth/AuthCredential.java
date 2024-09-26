package ec.com.technoloqie.fwk.security.api.config.auth;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class AuthCredential {
	
	private String email;
	private String password;
}
