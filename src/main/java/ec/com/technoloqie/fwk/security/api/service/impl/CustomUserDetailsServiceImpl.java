package ec.com.technoloqie.fwk.security.api.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import ec.com.technoloqie.fwk.security.api.commons.exception.AuthWSException;
import ec.com.technoloqie.fwk.security.api.entity.User;
import ec.com.technoloqie.fwk.security.api.entity.UserDetailsImpl;
import ec.com.technoloqie.fwk.security.api.repository.IUserRepository;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService{
	
	private IUserRepository userRepository;

	public CustomUserDetailsServiceImpl(IUserRepository userRepository) {
	        this.userRepository = userRepository;
	    }

	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws AuthWSException {
		User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new AuthWSException("Usuario no registrado: "+ usernameOrEmail));
		//Set<GrantedAuthority> authorities = user.getRoles().stream().map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
		//Set<GrantedAuthority> authorities  = new HashSet<GrantedAuthority>();
		//authorities.add(new SimpleGrantedAuthority(user.getUserType().getName()));
		
	   //return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPass(), authorities);
		return new UserDetailsImpl(user);
	}

}
