package ec.com.technoloqie.api.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ec.com.technoloqie.api.customer.dto.LoginDto;
import ec.com.technoloqie.api.customer.dto.SignUpDto;
import ec.com.technoloqie.api.customer.service.IUserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
public class AuthRestController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	private IUserService userService;

	@Value("${spring.application.name}")
	private String appName;

	@GetMapping("/messages")
	public String getMessage() {
		return String.format("Now this finally works out. Welcome %s", appName);
	}
	
	@PostMapping("/signin")
	@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto){
		log.info("message is: {}", loginDto.getUsernameOrEmail());
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
    }
	
	@PostMapping("/signup")
	@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){
		log.info("ingreso  registerUser {}", signUpDto.getUsername());
        // add check for username exists in a DB
        if(userService.existsByUsername(signUpDto.getUsername())){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        // add check for email exists in DB
        if(userService.existsByEmail(signUpDto.getEmail())){
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }
        //save user
        
        return new ResponseEntity<>(this.userService.createUser(signUpDto), HttpStatus.CREATED);

    }
	
}
