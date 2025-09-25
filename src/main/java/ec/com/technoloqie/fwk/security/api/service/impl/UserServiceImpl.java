package ec.com.technoloqie.fwk.security.api.service.impl;

import java.util.Collection;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.com.technoloqie.fwk.security.api.commons.exception.AuthWSException;
import ec.com.technoloqie.fwk.security.api.commons.util.JwtTokenUtils;
import ec.com.technoloqie.fwk.security.api.dto.AuthCredential;
import ec.com.technoloqie.fwk.security.api.dto.JWTAuthResponse;
import ec.com.technoloqie.fwk.security.api.dto.SignUpDto;
import ec.com.technoloqie.fwk.security.api.dto.UserDto;
import ec.com.technoloqie.fwk.security.api.entity.Person;
import ec.com.technoloqie.fwk.security.api.entity.User;
import ec.com.technoloqie.fwk.security.api.entity.UserDetailsImpl;
import ec.com.technoloqie.fwk.security.api.entity.UserType;
import ec.com.technoloqie.fwk.security.api.mapper.PersonMapper;
import ec.com.technoloqie.fwk.security.api.mapper.UserMapper;
import ec.com.technoloqie.fwk.security.api.repository.IPersonRepository;
import ec.com.technoloqie.fwk.security.api.repository.IUserRepository;
import ec.com.technoloqie.fwk.security.api.repository.IUserTypeRepository;
import ec.com.technoloqie.fwk.security.api.service.IUserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements IUserService{
	
	private IUserRepository userRepository;
	private IPersonRepository personRepo;
	private IUserTypeRepository userTypeRepo;
    private PasswordEncoder passwordEncoder;
	//private AuthenticationManager authenticationManager;
	private JwtTokenUtils jwtTokenUtils;
    private CustomUserDetailsServiceImpl userDetailsService;
	
	public UserServiceImpl(IUserRepository userRepository, IPersonRepository personRepo, IUserTypeRepository userTypeRepo,
			PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtTokenUtils jwtTokenUtils,
			CustomUserDetailsServiceImpl userDetailsService) {
		this.userRepository = userRepository;
		this.personRepo = personRepo;
		this.userTypeRepo = userTypeRepo;
		this.passwordEncoder = passwordEncoder;
		//this.authenticationManager = authenticationManager;
		this.jwtTokenUtils = jwtTokenUtils;
		this.userDetailsService = userDetailsService;
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public Collection<UserDto> getListUsers() {
		return null;
	}

	@Override
	@Transactional
	public UserDto createUser(SignUpDto signUpDto) {
		// add check for username exists in a DB
        if(userRepository.existsByUsername(signUpDto.getUsername())){
           throw new AuthWSException("\"Username is already taken!\"");
        }

        // add check for email exists in DB
        if(userRepository.existsByEmail(signUpDto.getEmail())){
        	throw new AuthWSException("Email is already taken!"); 
        }
        //save user
		try {
			signUpDto.setPassword(passwordEncoder.encode(signUpDto.getPassword())); 
			Optional<UserType> userType = userTypeRepo.findByName("user");
			User user = UserMapper.mapToUser(signUpDto);
			user.setUserType(userType.get());
			log.info("inserta usuario {}", user.getUsername());
			User newUser = userRepository.save(user);
			Person person = PersonMapper.mapToPerson(signUpDto.getPerson());
			person.setCreatedBy(signUpDto.getCreatedBy());
			person.setUser(newUser);
			newUser.setPerson(personRepo.save(person));
			return UserMapper.mapToUserDto(newUser);
		}catch(Exception e) {
			log.error("Error al momento de crear usuario ", e);
			throw new AuthWSException("Error al momento de crear usuario",e);
		}
	}

	@Override
	public JWTAuthResponse login(AuthCredential loginDto) throws AuthWSException {
		
		//User user = this.userRepository.findByUsernameOrEmail(loginDto.getEmail(), loginDto.getEmail()).orElseThrow(() ->new AuthWSException("Usuario no registrado: "+ loginDto.getEmail()));
		UserDetailsImpl user = this.userDetailsService.loadUserByUsername(loginDto.getEmail());
		
		if(!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
			throw new AuthWSException("Usuario o clave no valido");
		}
		
		//Set<GrantedAuthority> authorities  = new HashSet<GrantedAuthority>();
		//authorities.add(new SimpleGrantedAuthority(user.getUserType().getName()));
		
		//Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword(), authorities));
		Authentication authentication = this.jwtTokenUtils.getAuthentication(user);
		SecurityContextHolder.getContext().setAuthentication(authentication);
        
		String token = this.jwtTokenUtils.createToken(user.getUsername(), loginDto.getEmail(), user.getUserType().getId()); //JwtTokenUtils.generateToken(authentication);
        //TODO insertar token en redis con vencimiento en segundos
        String tokenRefresh = jwtTokenUtils.signRefreshToken(user.getUsername());

        return new JWTAuthResponse(token, tokenRefresh);
	}


	@Override
	public UserDto getUserByUsernameOrEmail(String username, String email) throws AuthWSException {
		User user = userRepository.findByUsernameOrEmail(username, email)
                .orElseThrow(() -> new AuthWSException(" Usuario o mail incorrectos: "+ username + email));	//Usuario no registrado
		return UserMapper.mapToUserDto(user);
	}

	
}