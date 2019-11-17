package vn.edu.uit.realestate.Relational.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.uit.realestate.Relational.Model.RealImage;
import vn.edu.uit.realestate.Relational.Model.Trade;

public interface RealImageRepository extends JpaRepository<RealImage, Long>{
//	@Transactional
//	@Modifying
//	@Query(value = "SELECT * FROM real_image WHERE trade_id=:tradeid", nativeQuery = true)
	List<RealImage> findByTrade(Trade trade);
}
