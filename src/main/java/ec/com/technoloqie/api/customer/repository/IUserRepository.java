package ec.com.technoloqie.api.customer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.com.technoloqie.api.customer.entity.User;

public interface IUserRepository extends JpaRepository<User, Integer> {
	
    Optional<User> findByEmail(String email);
    Optional<User> findByUsernameOrEmail(String username, String email);
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    
}
