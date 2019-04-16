package vn.edu.uit.realestate.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Address {
	@Id
	@GeneratedValue
	private Long id;
	private String detail;
	private String street;
	private String ward;
	private String district;
	private String cityOrProvince;
	@OneToOne(mappedBy = "address")
	private Trade trade;
	public Address() {
		super();
	}
	public Address(Long id, String detail, String street, String ward, String district, String cityOrProvince,
			Trade trade) {
		super();
		this.id = id;
		this.detail = detail;
		this.street = street;
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
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getCityOrProvince() {
		return cityOrProvince;
	}
	public void setCityOrProvince(String cityOrProvince) {
		this.cityOrProvince = cityOrProvince;
	}
	public Trade getTrade() {
		return trade;
	}
	public void setTrade(Trade trade) {
		this.trade = trade;
	}

}
