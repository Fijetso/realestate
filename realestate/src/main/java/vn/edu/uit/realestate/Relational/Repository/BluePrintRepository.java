package vn.edu.uit.realestate.Relational.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.edu.uit.realestate.Relational.Model.BluePrint;
import vn.edu.uit.realestate.Relational.Model.Trade;

public interface BluePrintRepository extends JpaRepository<BluePrint, Long>{

	List<BluePrint> findByTrade(Trade trade);
}
