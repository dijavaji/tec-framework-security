package ec.com.technoloqie.api.customer.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ec.com.technoloqie.api.customer.commons.exception.AuthWSException;
import ec.com.technoloqie.api.customer.dto.LoginDto;
import ec.com.technoloqie.api.customer.dto.SignUpDto;
import ec.com.technoloqie.api.customer.dto.UserDto;
import ec.com.technoloqie.api.customer.service.IUserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = {"http://127.0.0.1:3000"})
@RestController
@RequestMapping("${ec.com.technoloqie.fwk.security.api.prefix}/auth")
public class AuthRestController {

	@Autowired
	private AuthenticationManager authenticationManager;

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
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpDto signUpDto, BindingResult result){
		log.info("ingreso  registerUser {}", signUpDto.getUsername());
		UserDto userNew = null;
		Map <String, Object> response = new HashMap<>();
        try {
        	userNew = this.userService.createUser(signUpDto);
		}catch(AuthWSException e) {
			log.error("Error al momento de crear usuario.");
			response.put("message", "Error al momento de crear usuario");
			response.put("error", e.getMessage() +" : " + e);
			response.put("success", Boolean.FALSE);
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
			
		}catch(Exception e) {
			log.error("Error al momento de crear usuario.");
			response.put("message", "Error al momento de crear usuario");
			response.put("error", e.getMessage() +" : " + e);
			response.put("success", Boolean.FALSE);
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("message", "Usuario registrado correctamente");
		response.put("data", userNew);
		response.put("success", Boolean.TRUE);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
        //return new ResponseEntity<>(this.userService.createUser(signUpDto), HttpStatus.CREATED);
    }
	
}
