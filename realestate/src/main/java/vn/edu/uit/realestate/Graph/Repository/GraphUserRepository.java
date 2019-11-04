package vn.edu.uit.realestate.Graph.Repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import vn.edu.uit.realestate.Graph.Model.GraphUser;

public interface GraphUserRepository extends Neo4jRepository<GraphUser, Long>{

}
