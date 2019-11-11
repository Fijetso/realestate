package vn.edu.uit.realestate.Relational.Model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFilter;

@Entity
//@JsonIgnoreProperties("trade")
@JsonFilter("DetailsFilter")
public class Details {
	@Id
	@GeneratedValue
	private Long id;
	private Long length;
	private Long width;
	private Long square;
	private String direction;
	private String floors;
	private String legalDocuments;
	private int bathrooms;
	private int bedrooms;
	private String utilities;
	private String others;
	@OneToOne(mappedBy = "details", fetch = FetchType.LAZY)
	private Trade trade;
	public Details() {
		super();
	}
	public Details(Long length, Long width, Long square, String direction, String floors,
			String legalDocuments, int bathrooms, int bedrooms, String utilities, String others) {
		super();
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
		this.id = trade.getId();
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getLength() {
		return length;
	}
	public void setLength(Long length) {
		this.length = length;
	}
	public Long getWidth() {
		return width;
	}
	public void setWidth(Long width) {
		this.width = width;
	}
	public Long getSquare() {
		return square;
	}
	public void setSquare(Long square) {
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
	public Trade getTrade() {
		return trade;
	}
	public void setTrade(Trade trade) {
		this.trade = trade;
	} 
	}
