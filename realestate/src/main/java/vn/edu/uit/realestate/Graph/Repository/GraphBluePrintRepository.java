package vn.edu.uit.realestate.Graph.Repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import vn.edu.uit.realestate.Graph.Model.GraphBluePrint;

@Repository
public interface GraphBluePrintRepository extends Neo4jRepository<GraphBluePrint, Long>{
	
}
