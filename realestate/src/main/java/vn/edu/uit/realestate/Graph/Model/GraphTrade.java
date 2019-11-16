package vn.edu.uit.realestate.Graph.Model;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import vn.edu.uit.realestate.Relational.Model.Enum.TradeStatus;

@NodeEntity(label = "Trade")
public class GraphTrade {
	@Id
	private Long id;
	private String description;
	private Long cost;
	//// problem
	private TradeStatus tradeStatus = TradeStatus.WAITING;

	@Relationship(type = "HAVE", direction = Relationship.OUTGOING)
	private GraphAddress address;

	@Relationship(type = "HAVE", direction = Relationship.OUTGOING)
	private GraphDetails details;

	@Relationship(type = "LOCATE", direction = Relationship.OUTGOING)
	private GraphCoordinate coordinate;

	@Relationship(type = "ILLUSTRATE", direction = Relationship.INCOMING)
	private List<GraphRealImage> realImages = new ArrayList<GraphRealImage>();

	@Relationship(type = "ILLUSTRATE", direction = Relationship.INCOMING)
	private List<GraphBluePrint> bluePrints = new ArrayList<GraphBluePrint>();

	public GraphTrade() {
		super();
	}

	public GraphTrade(Long id, String description, Long cost, TradeStatus tradeStatus, GraphAddress address,
			GraphDetails details, GraphCoordinate coordinate, List<GraphRealImage> realImages,
			List<GraphBluePrint> bluePrints) {
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
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getCost() {
		return cost;
	}

	public void setCost(Long cost) {
		this.cost = cost;
	}

	public TradeStatus getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(TradeStatus tradeStatus) {
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

	public List<GraphRealImage> getRealImages() {
		return realImages;
	}

	public void setRealImages(List<GraphRealImage> realImages) {
		this.realImages = realImages;
	}

	public List<GraphBluePrint> getBluePrints() {
		return bluePrints;
	}

	public void setBluePrints(List<GraphBluePrint> bluePrints) {
		this.bluePrints = bluePrints;
	}
}
