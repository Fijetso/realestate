package vn.edu.uit.realestate.Graph.Repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import vn.edu.uit.realestate.Graph.Model.GraphTrade;

@Repository
public interface GraphTradeRepository extends Neo4jRepository<GraphTrade, Long>{
	@Query("MATCH (n) DETACH DELETE n")
	public void deleteEntireNeo4j();
	@Query("MATCH (t:Trade) WHERE t.Id=?<id> SET t.tradeStatus = ?<tradeStatus>")
	public GraphTrade updateTradeStatus(long id, String tradeStatus);
}
