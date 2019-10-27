package vn.edu.uit.realestate.DataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.uit.realestate.Model.Trade;

public interface TradeRepository extends JpaRepository<Trade, Long>{
	@Transactional
	@Modifying
	@Query(value = "UPDATE Trade SET Trade.view_count=Trade.view_count + 1 WHERE Trade.id=:id", nativeQuery = true)
	void increaseViewCountById( @Param("id") Long id);
	
//	@Transactional
//	@Query(value = "SELECT * from Trade WHERE Trade.cost >=:lowest_price AND Trade.cost <=:highest_price")
//	List<Trade> findByPrice(@Param("lowest_price") Long lowestPrice, @Param("highest_price") Long highestPrice);
}
