package vn.edu.uit.realestate.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.edu.uit.realestate.Model.Image;

public interface ImageRepository extends JpaRepository<Image, Long>{

}