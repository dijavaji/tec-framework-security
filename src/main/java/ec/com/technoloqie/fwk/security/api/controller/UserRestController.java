package ec.com.technoloqie.fwk.security.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ec.com.technoloqie.fwk.security.api.dto.UserDto;
import ec.com.technoloqie.fwk.security.api.service.IUserService;

/**
* Controlador principal que expone el servicio a trav&eacute;s de HTTP/Rest para
* las operaciones del recurso de usuarios<br/>
* @author dvasquez
* @version 0.1
*/
//@CrossOrigin(origins = {"http://127.0.0.1:4200"})
@CrossOrigin(origins = {"${ec.com.technoloqie.chatbot.app.url}"})
@RestController
@RequestMapping("${ec.com.technoloqie.fwk.security.api.prefix}/users")
public class UserRestController {
	
	@Value("${spring.application.name}")
	private String appName;
	private IUserService userService;
	
	public UserRestController(IUserService userService) {
		this.userService = userService;
	}

	@GetMapping("/messages")
    public String getMessage() {
        return String.format("Now this finally works out. Welcome %s",appName);
    }
	
	@GetMapping
	public ResponseEntity<?> getUserByUsernameOrEmail(@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "email", required = false) String email) {
		Map<String, Object> response = new HashMap<>();
		
		try {
			UserDto userDto = this.userService.getUserByUsernameOrEmail(username, email);
			response.put("message", "Consulta usuario correctamente");
			response.put("data", userDto);
			response.put("success", Boolean.TRUE);
			return ResponseEntity.ok(response);
		}catch(Exception e) {
			//log.error("Error consulta de usuario.",e );
			response.put("message", "Error consulta de usuario.");
			response.put("error", e.getMessage() +" : " + e);
			response.put("success", Boolean.FALSE);
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);		
		}
	}
	
	/*@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> addCustomer(@Valid @RequestBody CustomerDto accountDto, BindingResult result) {
		return new ResponseEntity<>(this.customerService.createCustomer(accountDto), HttpStatus.CREATED); 
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getCustomerById(@PathVariable Integer id) {
		CustomerDto customerDto = this.customerService.getCustomerId(id);
		return ResponseEntity.ok(customerDto); 
	}
	
	@GetMapping
	public ResponseEntity<List<?>> getAllCustomers() {
		List<CustomerDto> customers = (List<CustomerDto>) this.customerService.getListCustomers();
		return ResponseEntity.ok(customers); 
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCustomer(@Valid @PathVariable Integer id) {
		this.customerService.deleteCustomer(id);
		return ResponseEntity.ok("Cliente eliminado correctamente"); 
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateCustomer(@RequestBody CustomerDto customerDto, @PathVariable Integer id) {
		CustomerDto customer = this.customerService.updateCustomer(customerDto, id);
		return ResponseEntity.ok(customer);
	}*/

}
