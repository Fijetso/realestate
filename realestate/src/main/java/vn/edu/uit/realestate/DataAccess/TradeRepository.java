package vn.edu.uit.realestate.DataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.edu.uit.realestate.Model.Trade;

public interface TradeRepository extends JpaRepository<Trade, Long>{

}