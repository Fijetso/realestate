package vn.edu.uit.realestate.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.edu.uit.realestate.Model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
