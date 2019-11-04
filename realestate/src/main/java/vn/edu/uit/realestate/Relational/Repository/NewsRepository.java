package vn.edu.uit.realestate.Relational.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.edu.uit.realestate.Relational.Model.News;

public interface NewsRepository extends JpaRepository<News, Long>{
}
