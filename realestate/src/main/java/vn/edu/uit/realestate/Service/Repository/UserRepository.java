package vn.edu.uit.realestate.Service.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.uit.realestate.Model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findUserByUsername(String name);
}
