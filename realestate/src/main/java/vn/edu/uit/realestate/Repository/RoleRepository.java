package vn.edu.uit.realestate.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.uit.realestate.Model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	public Optional<Role> findByName(String name);
}
