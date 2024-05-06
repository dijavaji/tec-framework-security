package ec.com.technoloqie.fwk.security.api.config.auth;

import java.io.IOException;
import java.util.Collections;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import ec.com.technoloqie.fwk.security.api.commons.util.JwtTokenUtils;
import ec.com.technoloqie.fwk.security.api.dto.AuthCredential;
import ec.com.technoloqie.fwk.security.api.entity.UserDetailsImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	@SuppressWarnings("static-access")
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		AuthCredential authCredential = new AuthCredential();
		
		try {
			
			authCredential = new ObjectMapper().readValue(request.getReader(), AuthCredential.class);
		}catch(Exception e) {
			log.error("Error credenciales no enviadas.",e);
		}
		
		UsernamePasswordAuthenticationToken usernamePat = new UsernamePasswordAuthenticationToken(
				authCredential.getEmail(), authCredential.getPassword(), Collections.emptyList());
		
		return getAuthenticationManager().authenticate(usernamePat);
		
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
		String token = JwtTokenUtils.createToken(userDetails.getUsername(), userDetails.getEmail());
		log.info("generado successfulAuthentication {}",token);
		response.addHeader("Authorization", "Bearer " + token);
		
		response.getWriter().flush();
	}
}
