package vn.edu.uit.realestate.Relational.Model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties("trade")
@JsonFilter("CoordinateFilter")
public class Coordinate {
	@Id
	@GeneratedValue
	private Long id;
	private Long longitude;
	private Long latitude;
	@OneToOne(mappedBy = "coordinate", fetch = FetchType.LAZY)
	private Trade trade;

	public Coordinate() {
		super();
	}

	public Coordinate(Long longitude, Long latitude) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLongitude() {
		return longitude;
	}

	public void setLongitude(Long longitude) {
		this.longitude = longitude;
	}

	public Long getLatitude() {
		return latitude;
	}

	public void setLatitude(Long latitude) {
		this.latitude = latitude;
	}

	public Trade getTrade() {
		return trade;
	}

	public void setTrade(Trade trade) {
		this.trade = trade;
	}
}
