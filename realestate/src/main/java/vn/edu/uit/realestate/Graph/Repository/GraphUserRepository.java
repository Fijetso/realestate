package vn.edu.uit.realestate.Graph.Repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import vn.edu.uit.realestate.Graph.Model.GraphUser;

@Repository
public interface GraphUserRepository extends Neo4jRepository<GraphUser, Long>{
	
	@Query("MATCH (u:User) RETURN u")
	public List<GraphUser> getAllUser();
	@Query("CREATE (BillyC:User {name:'Billy Crystal'}) RETURN BillyC")
	public GraphUser saveNewUser();
}
