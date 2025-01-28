package ec.com.technoloqie.fwk.security.api.service.impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.com.technoloqie.fwk.security.api.commons.exception.AuthWSException;
import ec.com.technoloqie.fwk.security.api.dto.CustomerDto;
import ec.com.technoloqie.fwk.security.api.entity.Customer;
import ec.com.technoloqie.fwk.security.api.mapper.CustomerMapper;
import ec.com.technoloqie.fwk.security.api.repository.ICustormerRepository;
import ec.com.technoloqie.fwk.security.api.service.ICustomerService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerServiceImpl implements ICustomerService{
	
	@Autowired
	private ICustormerRepository customerRepo;
	
	@Override
	public Collection<CustomerDto> getListCustomers() {
		List<Customer> customers = this.customerRepo.findAll();
		return customers.stream().map((customer)-> CustomerMapper.mapToCustomerDto(customer)).collect(Collectors.toList());
	}

	@Override
	public CustomerDto createCustomer(CustomerDto customerDto) {
		Customer customer = CustomerMapper.mapToCustomer(customerDto);
		log.info("inserta cliente {}", customer);
		return CustomerMapper.mapToCustomerDto(customerRepo.save(customer));
	}

	@Override
	public CustomerDto getCustomerId(Integer code) {
		Customer customer = this.customerRepo.findById(code).orElseThrow(()-> new AuthWSException("Error el cliente no existe"));
		return CustomerMapper.mapToCustomerDto(customer);
	}

	@Override
	public CustomerDto updateCustomer(CustomerDto customer, int id) {
		Customer existCustomer = this.customerRepo.findById(id).orElseThrow(()-> new AuthWSException("Error el cliente no existe")); //tenemos que comprobar si con la identificación dada existe en la db o no
		existCustomer.setPass(customer.getPass());
		existCustomer.setModifiedBy(customer.getModifiedBy());
		existCustomer.setModifiedDate(new Date());
		existCustomer.setStatus(customer.getStatus());
		this.customerRepo.save(existCustomer);
		return CustomerMapper.mapToCustomerDto(existCustomer);
	}

	@Override
	public void deleteCustomer(Integer code) {
		getCustomerId(code);
		customerRepo.deleteById(code);
	}
}
