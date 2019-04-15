package vn.edu.uit.realestate.Model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Trade {
	@Id
	@GeneratedValue
	private Long tradeId;
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	@ManyToOne
	@JoinColumn(name="realEstateKindId", referencedColumnName = "id")
	private RealEstateKind realEstateKind;
	@ManyToOne
	@JoinColumn(name="tradeKindId", referencedColumnName = "id")
	private TradeKind tradeKind; 
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressId", referencedColumnName = "id")
    private Address address;
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "detailsId", referencedColumnName = "id")
    private Details details;
	private Long cost;
//	private Set<String> images;
	private String description;
	@ManyToOne
	@JoinColumn(name="bookingId", referencedColumnName = "id")
	private Booking booking;
	public Trade() {
		super();
	}
	
	public Trade(Long tradeId, User user, RealEstateKind realEstateKind, TradeKind tradeKind, Address address,
			Details details, Long cost, String description, Booking booking) {
		super();
		this.tradeId = tradeId;
		this.user = user;
		this.realEstateKind = realEstateKind;
		this.tradeKind = tradeKind;
		this.address = address;
		this.details = details;
		this.cost = cost;
		this.description = description;
		this.booking = booking;
	}

	public Long getTradeId() {
		return tradeId;
	}

	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public RealEstateKind getRealEstateKind() {
		return realEstateKind;
	}

	public void setRealEstateKind(RealEstateKind realEstateKind) {
		this.realEstateKind = realEstateKind;
	}

	public TradeKind getTradeKind() {
		return tradeKind;
	}

	public void setTradeKind(TradeKind tradeKind) {
		this.tradeKind = tradeKind;
	}

	public Long getCost() {
		return cost;
	}

	public void setCost(Long cost) {
		this.cost = cost;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Details getDetails() {
		return details;
	}

	public void setDetails(Details details) {
		this.details = details;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	
}
