package vn.edu.uit.realestate.DataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.uit.realestate.Model.News;

public interface NewsRepository extends JpaRepository<News, Long>{
}