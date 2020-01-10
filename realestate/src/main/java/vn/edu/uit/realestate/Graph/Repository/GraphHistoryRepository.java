package vn.edu.uit.realestate.Graph.Repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.edu.uit.realestate.Graph.Model.GraphHistory;

@Repository
public interface GraphHistoryRepository  extends Neo4jRepository<GraphHistory, Long>{
	@Query("MATCH (n:History)-[:BROWSE]-(u:User {id:{userId}}) RETURN n ORDER BY ID(n) DESC LIMIT 10")
	public List<GraphHistory> getHistoryByUserId(@Param(value="userId")Long userId);
}
