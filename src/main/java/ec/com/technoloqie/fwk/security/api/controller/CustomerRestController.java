package ec.com.technoloqie.fwk.security.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ec.com.technoloqie.fwk.security.api.dto.CustomerDto;
import ec.com.technoloqie.fwk.security.api.service.ICustomerService;
import jakarta.validation.Valid;

//@CrossOrigin(origins = {"http://127.0.0.1:4200"})
@RestController
@RequestMapping("${ec.com.technoloqie.fwk.security.api.prefix}/customers")
public class CustomerRestController {
	
	 @Value("${spring.application.name}")
	 private String appName;
	
	@Autowired
	private ICustomerService customerService;
	
	@GetMapping("/messages")
    public String getMessage() {
        return String.format("Now this finally works out. Welcome %s",appName);
    }
	
	@PostMapping
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
	}

}
