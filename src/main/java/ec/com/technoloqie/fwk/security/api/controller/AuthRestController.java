package ec.com.technoloqie.fwk.security.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ec.com.technoloqie.fwk.security.api.commons.exception.AuthWSException;
import ec.com.technoloqie.fwk.security.api.dto.AuthCredential;
import ec.com.technoloqie.fwk.security.api.dto.JWTAuthResponse;
import ec.com.technoloqie.fwk.security.api.dto.SignUpDto;
import ec.com.technoloqie.fwk.security.api.dto.UserDto;
import ec.com.technoloqie.fwk.security.api.service.IUserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@Slf4j
//@CrossOrigin(origins = {"http://127.0.0.1:3000"})
@CrossOrigin(origins = {"${ec.com.technoloqie.chatbot.app.url}"})
@RestController
@RequestMapping("${ec.com.technoloqie.fwk.security.api.prefix}/auth")
public class AuthRestController {

	//@Autowired
	//private AuthenticationManager authenticationManager;

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
    public ResponseEntity<?> authenticateUser(@RequestBody AuthCredential loginDto){
		log.info("message is: {}", loginDto.getEmail());
		Map <String, Object> response = new HashMap<>();
		try {
			JWTAuthResponse token = this.userService.login(loginDto);
			//response.put("data", token);
			//response.put("success", Boolean.TRUE);
			return new ResponseEntity<>(token, HttpStatus.CREATED);
		}catch(AuthWSException e) {
			log.error("Error authenticateUser {}", e);
			response.put("message", e.getMessage());
			response.put("error", e.getMessage() +" : " + e);
			response.put("success", Boolean.FALSE);
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.UNAUTHORIZED);
			
		}catch(Exception e) {
			log.error("Error al momento de ingresar usuario. {}",e);
			response.put("message", "Error al momento de ingresar usuario.");
			response.put("error", e.getMessage() +" : " + e);
			response.put("success", Boolean.FALSE);
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
        //return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
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
