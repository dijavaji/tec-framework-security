package ec.com.technoloqie.api.customer.mapper;

import ec.com.technoloqie.api.customer.dto.CustomerDto;
import ec.com.technoloqie.api.customer.entity.Customer;

public class CustomerMapper {
	
	public static Customer mapToCustomer(CustomerDto customerDto) {
		return new Customer(
				customerDto.getId(),
				customerDto.getPass(),
				customerDto.getCreatedBy(),
				customerDto.getCreatedDate(),
				customerDto.getModifiedBy(),
				customerDto.getModifiedDate(),
				customerDto.getStatus()
				);
	}
	
	public static CustomerDto mapToCustomerDto(Customer customer) {
		return new CustomerDto(
				customer.getId(),
				customer.getPass(),
				customer.getCreatedBy(),
				customer.getCreatedDate(),
				customer.getModifiedBy(),
				customer.getModifiedDate(),
				customer.getStatus(),
				null
				);
	}
}
