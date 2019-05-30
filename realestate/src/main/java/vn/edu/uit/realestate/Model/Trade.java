package vn.edu.uit.realestate.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	private Long viewCount =  (long) 0;
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
	private List<RealImage> realImages = new ArrayList<RealImage>();
	
	@OneToMany(mappedBy="trade")
	private List<BluePrint> bluePrints = new ArrayList<BluePrint>();
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="bookingId", referencedColumnName = "id")
	private  List<Booking> bookings = new ArrayList<Booking>();
	
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            })
	@JoinTable(name = "FavoriteTrade",
            joinColumns = { @JoinColumn(name = "tradeId") },
            inverseJoinColumns = { @JoinColumn(name = "userId") })
	private List<User> favoriteUsers = new ArrayList<User>();
    
	public Trade() {
		super();
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

	public List<RealImage> getRealImages() {
		return realImages;
	}

	public void setRealImages(List<RealImage> realImages) {
		this.realImages = realImages;
	}

	public List<BluePrint> getBluePrints() {
		return bluePrints;
	}

	public void setBluePrints(List<BluePrint> bluePrints) {
		this.bluePrints = bluePrints;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public Long getViewCount() {
		return viewCount;
	}

	public void setViewCount(Long viewCount) {
		this.viewCount = viewCount;
	}

	public List<User> getFavoriteUsers() {
		return favoriteUsers;
	}

	public void setFavoriteUsers(List<User> favoriteUsers) {
		this.favoriteUsers = favoriteUsers;
	}
}
