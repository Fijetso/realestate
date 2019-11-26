package vn.edu.uit.realestate.Relational.Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import vn.edu.uit.realestate.Common.Common;

import javax.validation.constraints.Email;

@Entity
//@JsonIgnoreProperties("trades")
@JsonFilter("UserFilter")
public class User {
	@Id
	@GeneratedValue
	private Long id;
	@Size(min = 2, message = "Name should have at least 2 characters")
	private String name;
	@Email(message = "Please provide a valid email address")
	private String email;
	@Pattern(regexp = "([0-9]{10}$)", message = "Please provide a valid phone number")
	private String phone;
	private String password;
	@Past(message = "BirthDate must be in the past")
	private Date birthdate;
	private boolean gender;
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "jobId", referencedColumnName = "id")
	private Job job;
	private boolean active;
	@ManyToOne
	@JoinColumn(name = "userKindId", referencedColumnName = "id")
	private UserKind userKind;
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Trade> trades = new ArrayList<Trade>();
	@JsonIgnore
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Request> requests = new ArrayList<Request>();
	@JsonIgnore
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<FavoriteTrade> favoriteTrades;
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = {
			@JoinColumn(name = "userId", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "roleId", referencedColumnName = "id") })
	private Set<Role> roles;

	private transient SimpleDateFormat dateFormat = new SimpleDateFormat(Common.Constains.LOCAL_DATE_FORMAT);

	public User() {
		super();
		this.active = false;
	}

	public User(String name, String email, String phoneNumber, Set<Role> roles, boolean active) {
		this.name = name;
		this.email = email;
		this.phone = phoneNumber;
		this.name = name;
		this.roles = roles;
		this.active = active;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
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

	public String getBirthdate() {
		if (birthdate == null) {
			return null;
		}
		return dateFormat.format(birthdate);
	}

	public void setBirthdate(String birthdate) {
		try {
			this.birthdate = dateFormat.parse(birthdate);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new vn.edu.uit.realestate.ExceptionHandler.IllegalArgumentException(
					"The variable 'birthdate' is invalid");
		}
	}

	public boolean getGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public List<Trade> getTrades() {
		return trades;
	}

	public void setTrades(List<Trade> trades) {
		this.trades = trades;
	}

	public List<FavoriteTrade> getFavoriteTrades() {
		return favoriteTrades;
	}

	public void setFavoriteTrades(List<FavoriteTrade> favoriteTrades) {
		this.favoriteTrades = favoriteTrades;
	}

	public List<Request> getRequests() {
		return requests;
	}

	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}
}
