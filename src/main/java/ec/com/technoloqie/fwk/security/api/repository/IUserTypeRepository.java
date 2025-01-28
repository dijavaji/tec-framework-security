package ec.com.technoloqie.fwk.security.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.com.technoloqie.fwk.security.api.entity.UserType;

public interface IUserTypeRepository extends JpaRepository<UserType, Integer> {
	
	Optional<UserType> findByName(String name);
}
