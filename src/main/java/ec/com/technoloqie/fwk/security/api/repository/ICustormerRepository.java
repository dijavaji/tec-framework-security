package ec.com.technoloqie.fwk.security.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.com.technoloqie.fwk.security.api.entity.Customer;

public interface ICustormerRepository extends JpaRepository<Customer, Integer>{

}
