package vn.edu.uit.realestate.Graph.Repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.edu.uit.realestate.Graph.Model.GraphAddress;
import vn.edu.uit.realestate.Graph.Model.GraphTrade;

@Repository
public interface GraphAddressRepository extends Neo4jRepository<GraphAddress, Long>{
	@Query("MATCH (w)-[W]-(t:Trade)-[h:HAVE]->(a:Address) WHERE  a.district={districtId} RETURN *")
	public List<GraphTrade> findTradeByDistrictId(@Param("districtId") long districtId);
}
