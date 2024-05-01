package ec.com.technoloqie.fwk.security.api.service.impl;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.com.technoloqie.fwk.security.api.commons.exception.AuthWSException;
import ec.com.technoloqie.fwk.security.api.dto.SignUpDto;
import ec.com.technoloqie.fwk.security.api.dto.UserDto;
import ec.com.technoloqie.fwk.security.api.entity.Person;
import ec.com.technoloqie.fwk.security.api.entity.User;
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
	
	@Autowired
	private IUserRepository userRepository;
	@Autowired
	private IPersonRepository personRepo;
	
	@Autowired
	private IUserTypeRepository userTypeRepo;
	
	@Autowired
    private PasswordEncoder passwordEncoder;

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
	

}
