package vn.edu.uit.realestate.Relational.Model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties("trade")
//@JsonFilter("CoordinateFilter")
public class Coordinate {
	@Id
	@GeneratedValue
	private Long id;
	private float longitude;
	private float latitude;
	@OneToOne(mappedBy = "coordinate", fetch = FetchType.LAZY)
	private Trade trade;

	public Coordinate() {
		super();
	}

	public Coordinate(float longitude, float latitude) {
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

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float f) {
		this.longitude = f;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public Trade getTrade() {
		return trade;
	}

	public void setTrade(Trade trade) {
		this.trade = trade;
	}
}
