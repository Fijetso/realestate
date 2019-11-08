package vn.edu.uit.realestate.Graph.Model;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity(label = "Movie")
public class GraphMovie {
	
	@Id @GeneratedValue
	private Long id;
	private String name;
	public GraphMovie() {
		super();
	}
	public GraphMovie(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
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
