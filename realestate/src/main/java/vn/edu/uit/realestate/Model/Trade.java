package vn.edu.uit.realestate.Model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Trade {
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="userId", referencedColumnName = "id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="realEstateKindId", referencedColumnName = "id")
	private RealEstateKind realEstateKind;
	
	@OneToOne
    @JoinColumn(name = "addressId", referencedColumnName = "id")
    private Address address;
	
	@OneToOne
    @JoinColumn(name = "detailsId", referencedColumnName = "id")
    private Details details;
	
	private Long cost;
	
	@OneToMany
	@JoinColumn(name="imageId", referencedColumnName = "id")
	private Set<Image> images;
	
	private String description;
	
	@OneToMany
	@JoinColumn(name="bookingId", referencedColumnName = "id")
	private  Set<Booking> booking;
	public Trade() {
		super();
	}

	public Trade(Long id, User user, RealEstateKind realEstateKind, Address address, Details details, Long cost,
			Set<Image> images, String description, Set<Booking> booking) {
		super();
		this.id = id;
		this.user = user;
		this.realEstateKind = realEstateKind;
		this.address = address;
		this.details = details;
		this.cost = cost;
		this.images = images;
		this.description = description;
		this.booking = booking;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	public Set<Image> getImages() {
		return images;
	}

	public void setImages(Set<Image> images) {
		this.images = images;
	}

	public Set<Booking> getBooking() {
		return booking;
	}

	public void setBooking(Set<Booking> booking) {
		this.booking = booking;
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
	
}
