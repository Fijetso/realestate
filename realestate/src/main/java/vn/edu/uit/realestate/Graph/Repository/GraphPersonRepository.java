package vn.edu.uit.realestate.Graph.Repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import vn.edu.uit.realestate.Graph.Model.GraphPerson;

@Repository
public interface GraphPersonRepository extends Neo4jRepository<GraphPerson, Long>{

	@Query("MATCH (p:Person)<-[r:LIKES]-(m:Movie) RETURN p")
//	@Query("MATCH (p:Person) RETURN p")
	public List<GraphPerson> getAllPeople();
}
