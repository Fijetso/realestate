package vn.edu.uit.realestate.Model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.Email;

@Entity
@JsonIgnoreProperties("trades")
public class User {
	@Id
	@GeneratedValue
	private Long id;
	@Size(min=2, message="Name should have at least 2 characters")
	private String name;
	@Email(message="Please provide a valid email address")
	private String email;
	private String phone;
	private String password;
	@Past(message="BirthDate must be in the past")
	private Date birthdate;
	private boolean gender;
	@ManyToOne
//	@JoinColumn(name="userKindId", referencedColumnName="id")
	private UserKind userKind;
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY)
	private Set<Trade> trades;

	public User() {
		super();
	}	

	public User(Long id, @Size(min = 2, message = "Name should have at least 2 characters") String name,
			@Email(message = "It seems Email cannot recognized") String email, String phone, String password,
			@Past(message = "BirthDate must be in the past") Date birthdate, boolean gender, UserKind userKind,
			Set<Trade> trades) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.birthdate = birthdate;
		this.gender = gender;
		this.userKind = userKind;
		this.trades = trades;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public Set<Trade> getTrades() {
		return trades;
	}

	public void setTrades(Set<Trade> trades) {
		this.trades = trades;
	}
	
}
