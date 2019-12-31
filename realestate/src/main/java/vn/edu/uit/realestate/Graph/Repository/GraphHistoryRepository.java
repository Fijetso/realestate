package vn.edu.uit.realestate.Graph.Repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.edu.uit.realestate.Graph.Model.GraphHistory;

@Repository
public interface GraphHistoryRepository extends Neo4jRepository<GraphHistory, Long>{
//	@Query("MATCH (h:History) MATCH (h)--(u:User) WHERE  u.userId={userId} AND RETURN *")
//	public String findTheMostSearching(@Param("userId") String userId, @Param("key") String key, @Param("value") String value);
}
