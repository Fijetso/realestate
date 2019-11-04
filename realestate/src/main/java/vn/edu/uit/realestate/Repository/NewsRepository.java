package vn.edu.uit.realestate.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.uit.realestate.Model.News;

public interface NewsRepository extends JpaRepository<News, Long>{
}
