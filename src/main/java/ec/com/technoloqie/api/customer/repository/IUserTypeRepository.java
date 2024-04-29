package ec.com.technoloqie.api.customer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.com.technoloqie.api.customer.entity.UserType;

public interface IUserTypeRepository extends JpaRepository<UserType, Integer> {
	
	Optional<UserType> findByName(String name);
}
