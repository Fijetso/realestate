package vn.edu.uit.realestate.Relational.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.edu.uit.realestate.Relational.Model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	public Optional<User> findByName(String name);
	public Optional<User> findByEmail(String email);
}
