package ec.com.technoloqie.fwk.security.api.config;

import org.springframework.beans.factory.annotation.Value;
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
	
	@Value("${ec.com.technoloqie.fwk.security.api.prefix}")
	private String apiPrefix;
	
	public SpringSecurityConfig(UserDetailsService userDetailsService,JwtAuthorizationFilter jwtAuthorizationFilter){
    	super();
        this.userDetailsService = userDetailsService;
        this.jwtAuthorizationFilter= jwtAuthorizationFilter;
    }
	
	@Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http,  AuthenticationManager authManager) throws Exception {
    	
		JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter();
		jwtAuthenticationFilter.setAuthenticationManager(authManager);
		
		jwtAuthenticationFilter.setFilterProcessesUrl(apiPrefix + "/login");
    	

        http.csrf().disable()
        .authorizeHttpRequests()
        .requestMatchers("/api/v1/auth/**").permitAll()
        .anyRequest().authenticated()
		.and()
		//.httpBasic().and() //comentado por que se tiene personalizado
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.addFilter(jwtAuthenticationFilter)	//agrego filtros personales
		.addFilterBefore(this.jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
        /*.authorizeHttpRequests((authorize) ->{
            authorize.anyRequest().authenticated();
            authorize.requestMatchers("/v3/api-docs/**", "/swagger-ui/**","/swagger-ui.html").permitAll();
            authorize.requestMatchers("/api/v1/auth/**").permitAll();
            authorize.anyRequest().authenticated();
            }

        );*/
        
		
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
