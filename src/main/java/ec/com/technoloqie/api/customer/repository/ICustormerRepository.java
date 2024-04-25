package ec.com.technoloqie.api.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.com.technoloqie.api.customer.entity.Customer;

public interface ICustormerRepository extends JpaRepository<Customer, Integer>{

}
