package ec.com.technoloqie.fwk.security.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.com.technoloqie.fwk.security.api.entity.Person;

public interface IPersonRepository extends JpaRepository<Person, Integer>{

}
