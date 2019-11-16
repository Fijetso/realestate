package vn.edu.uit.realestate.Graph.Model;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity(label = "RealImage")
public class GraphRealImage {
	@Id
	private Long id;
	private String imageLink;
	private String description;
	
	public GraphRealImage() {
		super();
	}

	public GraphRealImage(Long id, String imageLink, String description) {
		super();
		this.id = id;
		this.imageLink = imageLink;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
}