package vn.edu.uit.realestate.DataAccess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import vn.edu.uit.realestate.Model.RealImage;
import vn.edu.uit.realestate.Model.Trade;

public interface RealImageRepository extends JpaRepository<RealImage, Long>{
//	@Transactional
//	@Modifying
//	@Query(value = "SELECT * FROM real_image WHERE trade_id=:tradeid", nativeQuery = true)
	List<RealImage> findByTrade(@Param("tradeid") Trade trade);
}
