package vn.edu.uit.realestate.Relational.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import vn.edu.uit.realestate.Relational.Model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	public Optional<Role> findByName(String name);
}
