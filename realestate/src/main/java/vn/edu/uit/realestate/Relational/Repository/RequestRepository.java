package vn.edu.uit.realestate.Relational.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.edu.uit.realestate.Relational.Model.Request;

public interface RequestRepository extends JpaRepository<Request, Long>{
}
