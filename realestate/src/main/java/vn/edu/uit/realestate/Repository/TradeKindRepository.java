package vn.edu.uit.realestate.DataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.edu.uit.realestate.Model.TradeKind;

public interface TradeKindRepository extends JpaRepository<TradeKind, Long>{

}