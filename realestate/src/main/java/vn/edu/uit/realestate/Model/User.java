package vn.edu.uit.realestate.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

@Entity
public class User {
	@Id
	@GeneratedValue
	private Long id;
	@Size(min=2)
	private String name;
	private String email;
	private String phone;
	private String password;
	@Past
	private Date birthdate;
	private boolean gender;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="userKindId", referencedColumnName="id")
	private UserKind userKind;

	public User() {
		super();
	}	

	public User(Long id, String name, String email, String phone, String password, Date birthdate, boolean gender,
			UserKind userKind) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.birthdate = birthdate;
		this.gender = gender;
		this.userKind = userKind;
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
	
}
