package vn.edu.uit.realestate.Graph.Model;

import java.util.List;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity(label = "Person")
public class GraphPerson {
	@Id @GeneratedValue
	private Long id;
	private String name;
	@Relationship(type = "RATED", direction = Relationship.INCOMING)
	List<GraphMovie> graphMovies;
	
	public GraphPerson(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public GraphPerson() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
