package vn.edu.uit.realestate.Relational.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.edu.uit.realestate.Relational.Model.Category;
import vn.edu.uit.realestate.Relational.Model.News;

public interface NewsRepository extends JpaRepository<News, Long>{
	public List<News> findByCategory(Category category);
}
