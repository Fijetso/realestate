package vn.edu.uit.realestate.Graph.Repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.edu.uit.realestate.Graph.Model.GraphHistory;

@Repository
public interface GraphHistoryRepository  extends Neo4jRepository<GraphHistory, Long>{
	@Query("MATCH (h:History {userId:) 20 DES by id")
	public List<GraphHistory> getHistoryByUserId(@Param(value="userId")Long userId);
}
