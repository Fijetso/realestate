package vn.edu.uit.realestate.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.uit.realestate.Model.Request;

public interface RequestRepository extends JpaRepository<Request, Long>{
}
