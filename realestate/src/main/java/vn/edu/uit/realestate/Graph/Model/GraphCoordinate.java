package vn.edu.uit.realestate.Graph.Model;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity(label = "Coordinate")
public class GraphCoordinate {
	@Id
	private long id;
	private float longitude;
	private float latitude;
	@Relationship(type = "LOCATE", direction = Relationship.INCOMING)
	private GraphTrade trade;

	public GraphCoordinate() {
		super();
	}

	public GraphCoordinate(Long id, float f, float g) {
		super();
		this.id = id;
		this.longitude = f;
		this.latitude = g;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(Long longitude) {
		this.longitude = longitude;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(Long latitude) {
		this.latitude = latitude;
	}

//	public GraphTrade getTrade() {
//		return trade;
//	}

	public void setTrade(GraphTrade trade) {
		this.trade = trade;
	}
}
