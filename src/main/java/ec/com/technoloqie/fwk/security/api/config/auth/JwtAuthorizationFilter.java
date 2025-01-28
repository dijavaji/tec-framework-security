package ec.com.technoloqie.fwk.security.api.config.auth;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import ec.com.technoloqie.fwk.security.api.commons.util.JwtTokenUtils;
import ec.com.technoloqie.fwk.security.api.service.impl.CustomUserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter{
	
	//private JwtTokenUtils jwtTokenProvider;
	@Autowired
    private CustomUserDetailsServiceImpl userDetailsService;
	@Autowired
	private JwtTokenUtils jwtTokenUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

    	//String bearerToken = request.getHeader("Authorization");
    	// get JWT token from http request
        String token = getTokenFromRequest(request);
		
        //if(bearerToken != null && bearerToken.startsWith("Bearer ")) {
        if(StringUtils.hasText(token) && this.jwtTokenUtils.validateToken(token)) {
			//String token = bearerToken.replace("Bearer ", "");
        	 // get username from token
            String username = this.jwtTokenUtils.getUsername(token);
            log.info("get username from token {}",username);
         // load the user associated with token
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

        	UsernamePasswordAuthenticationToken usernamePat = this.jwtTokenUtils.getAuthentication(userDetails);
			SecurityContextHolder.getContext().setAuthentication(usernamePat); 
		}
		filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request){

        String bearerToken = request.getHeader("Authorization");

        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            String token = bearerToken.substring(7, bearerToken.length());
            return token;
        }

        return null;
    }
}
