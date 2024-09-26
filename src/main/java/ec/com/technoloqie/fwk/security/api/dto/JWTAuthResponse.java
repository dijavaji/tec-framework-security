package ec.com.technoloqie.fwk.security.api.dto;

import ec.com.technoloqie.fwk.security.api.commons.AuthWSConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JWTAuthResponse {
	
	private String accessToken;
	private String refreshToken;
    private String tokenType = AuthWSConstants.JWT_TOKEN_TYPE;
    
    public JWTAuthResponse(String accessToken, String refreshToken) {
    	this.accessToken = accessToken;
    	this.refreshToken = refreshToken;
    }
    
}
