package vn.edu.uit.realestate.Relational.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import vn.edu.uit.realestate.Relational.Model.AddressTree.District;

@Entity
public class Request {
	@Id
	@GeneratedValue
	private Long id;
	private Long lowestPrice;
	private Long highestPrice;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="userId", referencedColumnName = "id")
	private User user;
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
	public Long getHighestPrice() {
		return highestPrice;
	}
	public void setHighestPrice(Long highestPrice) {
		this.highestPrice = highestPrice;
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
