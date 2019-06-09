package vn.edu.uit.realestate.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import vn.edu.uit.realestate.Model.AddressTree.District;

@Entity
public class Request {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String phoneNumber;
	private String email;
	private Long lowestPrice;
	private Long higestPrice;
	
	@ManyToOne
	@JoinColumn(name="districtId", referencedColumnName = "id")
	private District district;
	@ManyToOne
	@JoinColumn(name="tradeKindId", referencedColumnName = "id")
	private TradeKind tradeKind;
	@ManyToOne
	@JoinColumn(name="realEstateKindId", referencedColumnName = "id")
	private RealEstateKind realEstateKind;
	public Request() {
		super();
	}
	public Request(Long id, String name, String phoneNumber, String email, District district, Long lowestPrice,
			Long higestPrice, TradeKind tradeKind, RealEstateKind realEstateKind) {
		super();
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.district = district;
		this.lowestPrice = lowestPrice;
		this.higestPrice = higestPrice;
		this.tradeKind = tradeKind;
		this.realEstateKind = realEstateKind;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public District getDistrict() {
		return district;
	}
	public void setDistrict(District district) {
		this.district = district;
	}
	public Long getLowestPrice() {
		return lowestPrice;
	}
	public void setLowestPrice(Long lowestPrice) {
		this.lowestPrice = lowestPrice;
	}
	public Long getHigestPrice() {
		return higestPrice;
	}
	public void setHigestPrice(Long higestPrice) {
		this.higestPrice = higestPrice;
	}
	public TradeKind getTradeKind() {
		return tradeKind;
	}
	public void setTradeKind(TradeKind tradeKind) {
		this.tradeKind = tradeKind;
	}
	public RealEstateKind getRealEstateKind() {
		return realEstateKind;
	}
	public void setRealEstateKind(RealEstateKind realEstateKind) {
		this.realEstateKind = realEstateKind;
	}
}
