package vn.edu.uit.realestate.Graph.Model;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity(label = "Details")
public class GraphDetails {
	@Id
	private long id;
	private float length;
	private float width;
	private float square;
	private String direction;
	private String floors;
	private String legalDocuments;
	private int bathrooms;
	private int bedrooms;
	private String utilities;
	private String others;
	@Relationship(type = "HAVE", direction = Relationship.INCOMING)
	private GraphTrade trade;

	public GraphDetails() {
		super();
	}

	public GraphDetails(Long id, Long length, Long width, Long square, String direction, String floors,
			String legalDocuments, int bathrooms, int bedrooms, String utilities, String others) {
		super();
		this.id = id;
		this.length = length;
		this.width = width;
		this.square = square;
		this.direction = direction;
		this.floors = floors;
		this.legalDocuments = legalDocuments;
		this.bathrooms = bathrooms;
		this.bedrooms = bedrooms;
		this.utilities = utilities;
		this.others = others;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLength(Long length) {
		if (length != null)
			this.length = length;
	}

	public void setWidth(Long width) {
		if (width != null)
			this.width = width;
	}

	public float getLength() {
		return length;
	}

	public void setLength(float length) {
		this.length = length;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getSquare() {
		return square;
	}

	public void setSquare(float square) {
		this.square = square;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setSquare(Long square) {
		if (square != null)
			this.square = square;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getFloors() {
		return floors;
	}

	public void setFloors(String floors) {
		this.floors = floors;
	}

	public String getLegalDocuments() {
		return legalDocuments;
	}

	public void setLegalDocuments(String legalDocuments) {
		this.legalDocuments = legalDocuments;
	}

	public int getBathrooms() {
		return bathrooms;
	}

	public void setBathrooms(int bathrooms) {
		this.bathrooms = bathrooms;
	}

	public int getBedrooms() {
		return bedrooms;
	}

	public void setBedrooms(int bedrooms) {
		this.bedrooms = bedrooms;
	}

	public String getUtilities() {
		return utilities;
	}

	public void setUtilities(String utilities) {
		this.utilities = utilities;
	}

	public String getOthers() {
		return others;
	}

	public void setOthers(String others) {
		this.others = others;
	}

//	public GraphTrade getTrade() {
//		return trade;
//	}

	public void setTrade(GraphTrade trade) {
		this.trade = trade;
	}
}
