package vn.edu.uit.realestate.Model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFilter;

@Entity
//@JsonIgnoreProperties("trade")
@JsonFilter("AddressFilter")
public class Address {
	@Id
	@GeneratedValue
	private Long id;
	@NotNull(message="You must enter the detail address")
	private String detail;
	@NotNull(message="You must enter the ward")
	private Long ward;
	@NotNull(message="You must enter the district")
	private Long district;
	@NotNull(message="You must enter the city or province")
	private Long cityOrProvince;
	@OneToOne(mappedBy = "address", fetch=FetchType.LAZY)
	private Trade trade;
	public Address() {
		super();
	}
	
	public Address(Long id, @NotNull(message = "You must enter the detail address") String detail,
			@NotNull(message = "You must enter the ward") Long ward,
			@NotNull(message = "You must enter the district") Long district,
			@NotNull(message = "You must enter the city or province") Long cityOrProvince, Trade trade) {
		super();
		this.id = id;
		this.detail = detail;
		this.ward = ward;
		this.district = district;
		this.cityOrProvince = cityOrProvince;
		this.trade = trade;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	public Long getWard() {
		return ward;
	}

	public void setWard(Long ward) {
		this.ward = ward;
	}

	public Long getDistrict() {
		return district;
	}

	public void setDistrict(Long district) {
		this.district = district;
	}

	public Long getCityOrProvince() {
		return cityOrProvince;
	}

	public void setCityOrProvince(Long cityOrProvince) {
		this.cityOrProvince = cityOrProvince;
	}

	public Trade getTrade() {
		return trade;
	}
	public void setTrade(Trade trade) {
		this.trade = trade;
	}

}
