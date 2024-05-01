package ec.com.technoloqie.fwk.security.api.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ec.com.technoloqie.fwk.security.api.entity.User;
import ec.com.technoloqie.fwk.security.api.repository.IUserRepository;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService{
	
	private IUserRepository userRepository;

	public CustomUserDetailsServiceImpl(IUserRepository userRepository) {
	        this.userRepository = userRepository;
	    }

	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email: "+ usernameOrEmail));
		//Set<GrantedAuthority> authorities = user.getRoles().stream().map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
		Set<GrantedAuthority> authorities  = new HashSet<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(user.getUserType().getName()));
		
	   return new org.springframework.security.core.userdetails.User(user.getEmail(),
	           user.getPass(),
	           authorities);
	}

}
