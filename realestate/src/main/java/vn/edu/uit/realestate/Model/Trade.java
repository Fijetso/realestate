package vn.edu.uit.realestate.Model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFilter;

@Entity
//@JsonIgnoreProperties("bookings")
@JsonFilter("TradeFilter")
public class Trade {
	@Id
	@GeneratedValue
	private Long id;
	private String description;
	private Long cost;
	
	@ManyToOne
	@JoinColumn(name="userId", referencedColumnName = "id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="realEstateKindId", referencedColumnName = "id")
	private RealEstateKind realEstateKind;
	
	@ManyToOne
	@JoinColumn(name="tradeKindId", referencedColumnName = "id")
	private TradeKind tradeKind;
	
	@OneToOne
    @JoinColumn(name = "addressId", referencedColumnName = "id")
    private Address address;
	
	@OneToOne
    @JoinColumn(name = "detailsId", referencedColumnName = "id")
    private Details details;
	
	@OneToMany(mappedBy="trade")
	private List<Image> realImages;
	
	@OneToMany(mappedBy="trade")
	private List<Image> bluePrints;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="bookingId", referencedColumnName = "id")
	private  List<Booking> bookings;
	
	public Trade() {
		super();
	}

	public Trade(Long id, String description, Long cost, User user, RealEstateKind realEstateKind, TradeKind tradeKind,
			Address address, Details details, List<Image> realImages, List<Image> bluePrints, List<Booking> bookings) {
		super();
		this.id = id;
		this.description = description;
		this.cost = cost;
		this.user = user;
		this.realEstateKind = realEstateKind;
		this.tradeKind = tradeKind;
		this.address = address;
		this.details = details;
		this.realImages = realImages;
		this.bluePrints = bluePrints;
		this.bookings = bookings;
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

	public List<Image> getRealImages() {
		return realImages;
	}

	public void setRealImages(List<Image> realImages) {
		this.realImages = realImages;
	}

	public List<Image> getBluePrints() {
		return bluePrints;
	}

	public void setBluePrints(List<Image> bluePrints) {
		this.bluePrints = bluePrints;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}
}
