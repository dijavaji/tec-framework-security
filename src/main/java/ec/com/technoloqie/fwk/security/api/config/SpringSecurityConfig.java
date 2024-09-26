package ec.com.technoloqie.fwk.security.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import ec.com.technoloqie.fwk.security.api.config.auth.JWTAuthenticationFilter;
import ec.com.technoloqie.fwk.security.api.config.auth.JwtAuthorizationFilter;

@Configuration
//@EnableMethodSecurity
public class SpringSecurityConfig {
	
	private final UserDetailsService userDetailsService;
	private final JwtAuthorizationFilter jwtAuthorizationFilter;
	
	public SpringSecurityConfig(UserDetailsService userDetailsService,JwtAuthorizationFilter jwtAuthorizationFilter){
    	super();
        this.userDetailsService = userDetailsService;
        this.jwtAuthorizationFilter= jwtAuthorizationFilter;
    }
	
	@Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http,  AuthenticationManager authManager) throws Exception {
    	
		JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter();
		jwtAuthenticationFilter.setAuthenticationManager(authManager);
		
		jwtAuthenticationFilter.setFilterProcessesUrl("/login");
    	

        http.csrf().disable()
        .authorizeRequests().anyRequest().authenticated()
		.and()
		//.httpBasic().and() //comentado por que se tiene personalizado
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.addFilter(jwtAuthenticationFilter)	//agrego filtros personales
		.addFilterBefore(this.jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
	

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    
}
