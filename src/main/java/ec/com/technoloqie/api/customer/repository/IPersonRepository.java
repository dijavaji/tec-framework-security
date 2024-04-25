package ec.com.technoloqie.api.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.com.technoloqie.api.customer.entity.Person;

public interface IPersonRepository extends JpaRepository<Person, Integer>{

}
