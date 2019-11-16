package vn.edu.uit.realestate.Graph.Repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import vn.edu.uit.realestate.Graph.Model.GraphTradeKind;

@Repository
public interface GraphTradeKindRepository extends Neo4jRepository<GraphTradeKind, Long>{
	
}
