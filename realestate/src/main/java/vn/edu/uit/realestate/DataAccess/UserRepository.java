package vn.edu.uit.realestate.DataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.uit.realestate.Model.User;

public interface UserRepository extends JpaRepository<User, Long>{
}
