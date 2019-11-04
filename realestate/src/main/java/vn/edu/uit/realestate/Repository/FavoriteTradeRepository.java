package vn.edu.uit.realestate.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.uit.realestate.Model.FavoriteTrade;
import vn.edu.uit.realestate.Model.User;

public interface FavoriteTradeRepository extends JpaRepository<FavoriteTrade, Long>{
	@Transactional
	@Modifying
	@Query(value = "UPDATE Trade SET Trade.view_count=Trade.view_count + 1 WHERE Trade.id=:id", nativeQuery = true)
	List<FavoriteTrade> increaseViewCountById( @Param("id") Long id);
	List<FavoriteTrade> findByUser(User user);
}
