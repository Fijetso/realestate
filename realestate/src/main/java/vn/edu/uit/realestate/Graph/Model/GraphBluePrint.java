package vn.edu.uit.realestate.Graph.Model;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity(label = "BluePrint")
public class GraphBluePrint {
	@Id
	private long id;
	private String imageLink;
	private String description;
	@Relationship(type = "ILLUSTRATE", direction = Relationship.OUTGOING)
	private GraphTrade trade;
	
	public GraphBluePrint() {
		super();
	}

	public GraphBluePrint(long id, String imageLink, String description) {
		super();
		this.id = id;
		this.imageLink = imageLink;
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

//	public GraphTrade getTrade() {
//		return trade;
//	}

	public void setTrade(GraphTrade trade) {
		this.trade = trade;
	}
}