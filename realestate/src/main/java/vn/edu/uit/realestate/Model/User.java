package vn.edu.uit.realestate.Model;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

@Entity
@JsonIgnoreProperties({"trades","password"})
public class User {
	@Id
	@GeneratedValue
	private String id;
	@Size(min=2, message="Name should have at least 2 characters")
	private String name;
	@Email(message="Please provide a valid email address")
	private String email;
	private String imageUrl;
	@Column(nullable = false)
    private Boolean emailVerified = false;
	@Pattern(regexp = "([0-9]{10}$)", message="Please provide a valid phone number")
	private String phone;
	@Enumerated(EnumType.STRING)
    private AuthSupport support;
	private String password;
	@Past(message="BirthDate must be in the past")
	private Date birthdate;
	private boolean gender;
	@ManyToOne
	@JoinColumn(name="userKindId", referencedColumnName="id")
	private UserKind userKind;
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY)
	private List<Trade> trades;

	public User() {
		super();
}

	public User(String id, @Size(min = 2, message = "Name should have at least 2 characters") String name,
			@Email(message = "Please provide a valid email address") String email, String imageUrl,
			Boolean emailVerified,
			@Pattern(regexp = "([0-9]{10}$)", message = "Please provide a valid phone number") String phone,
			AuthSupport support, String password, @Past(message = "BirthDate must be in the past") Date birthdate,
			boolean gender, UserKind userKind, List<Trade> trades) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.imageUrl = imageUrl;
		this.emailVerified = emailVerified;
		this.phone = phone;
		this.support = support;
		this.password = password;
		this.birthdate = birthdate;
		this.gender = gender;
		this.userKind = userKind;
		this.trades = trades;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Boolean getEmailVerified() {
		return emailVerified;
	}

	public void setEmailVerified(Boolean emailVerified) {
		this.emailVerified = emailVerified;
	}

	public AuthSupport getSupport() {
		return support;
	}

	public void setSupport(AuthSupport support) {
		this.support = support;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserKind getUserKind() {
		return userKind;
	}

	public void setUserKind(UserKind userKind) {
		this.userKind = userKind;
	}

	public List<Trade> getTrades() {
		return trades;
	}

	public void setTrades(List<Trade> trades) {
		this.trades = trades;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}
}
