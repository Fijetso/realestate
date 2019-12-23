package vn.edu.uit.realestate.Graph.Model;

import java.util.List;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import vn.edu.uit.realestate.Relational.Model.Enum.TradeStatus;

@NodeEntity(label = "Trade")
public class GraphTrade {
	@Id
	private long id;
	private String description;
	private long cost;
	//// problem
	private String tradeStatus = TradeStatus.WAITING.toString();

	@Relationship(type = "HAVE", direction = Relationship.OUTGOING)
	private GraphAddress address;

	@Relationship(type = "HAVE", direction = Relationship.OUTGOING)
	private GraphDetails details;

	@Relationship(type = "LOCATE", direction = Relationship.OUTGOING)
	private GraphCoordinate coordinate;

	@Relationship(type = "ILLUSTRATE", direction = Relationship.INCOMING)
	private List<GraphRealImage> realImages;

	@Relationship(type = "ILLUSTRATE", direction = Relationship.INCOMING)
	private List<GraphBluePrint> bluePrints;

	@Relationship(type="CONTAIN", direction = Relationship.INCOMING)
	private GraphTradeKind tradeKind;
	@Relationship(type = "CONTAIN", direction = Relationship.INCOMING)
	private GraphRealEstateKind realEstateKind;
	@Relationship(type = "POST", direction = Relationship.INCOMING)
	private GraphUser user;
	
	public GraphTrade() {
		super();
	}

	public GraphTrade(long id, String description, long cost, String tradeStatus, GraphAddress address,
			GraphDetails details, GraphCoordinate coordinate, List<GraphRealImage> realImages,
			List<GraphBluePrint> bluePrints, GraphTradeKind tradeKind, GraphRealEstateKind realEstateKind,
			GraphUser user) {
		super();
		this.id = id;
		this.description = description;
		this.cost = cost;
		this.tradeStatus = tradeStatus;
		this.address = address;
		this.details = details;
		this.coordinate = coordinate;
		this.realImages = realImages;
		this.bluePrints = bluePrints;
		this.tradeKind = tradeKind;
		this.realEstateKind = realEstateKind;
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getCost() {
		return cost;
	}

	public void setCost(long cost) {
		this.cost = cost;
	}

	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public GraphAddress getAddress() {
		return address;
	}

	public void setAddress(GraphAddress address) {
		this.address = address;
	}

	public GraphDetails getDetails() {
		return details;
	}

	public void setDetails(GraphDetails details) {
		this.details = details;
	}

	public GraphCoordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(GraphCoordinate coordinate) {
		this.coordinate = coordinate;
	}

	public void setRealImages(List<GraphRealImage> realImages) {
		this.realImages = realImages;
	}

	public void setBluePrints(List<GraphBluePrint> bluePrints) {
		this.bluePrints = bluePrints;
	}

	public GraphTradeKind getTradeKind() {
		return tradeKind;
	}

	public void setTradeKind(GraphTradeKind tradeKind) {
		this.tradeKind = tradeKind;
	}

	public GraphRealEstateKind getRealEstateKind() {
		return realEstateKind;
	}

	public void setRealEstateKind(GraphRealEstateKind realEstateKind) {
		this.realEstateKind = realEstateKind;
	}

	public GraphUser getUser() {
		return user;
	}

	public void setUser(GraphUser user) {
		this.user = user;
	}

	public List<GraphRealImage> getRealImages() {
		return realImages;
	}

	public List<GraphBluePrint> getBluePrints() {
		return bluePrints;
	}
}
