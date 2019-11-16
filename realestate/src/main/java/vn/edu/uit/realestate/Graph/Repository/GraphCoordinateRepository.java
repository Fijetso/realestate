package vn.edu.uit.realestate.Graph.Repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import vn.edu.uit.realestate.Graph.Model.GraphCoordinate;

@Repository
public interface GraphCoordinateRepository extends Neo4jRepository<GraphCoordinate, Long>{
	
}
