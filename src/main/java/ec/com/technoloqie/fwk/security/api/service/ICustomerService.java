package ec.com.technoloqie.fwk.security.api.service;

import java.util.Collection;

import ec.com.technoloqie.fwk.security.api.dto.CustomerDto;

public interface ICustomerService {
	
	Collection<CustomerDto> getListCustomers();
	CustomerDto createCustomer(CustomerDto customer);
	CustomerDto getCustomerId(Integer code);
	CustomerDto updateCustomer(CustomerDto customer, int id);
	void deleteCustomer(Integer code);

}
