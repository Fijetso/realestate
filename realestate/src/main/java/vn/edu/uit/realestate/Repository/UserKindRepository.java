package vn.edu.uit.realestate.DataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.edu.uit.realestate.Model.UserKind;

public interface UserKindRepository extends JpaRepository<UserKind, Long>{

}