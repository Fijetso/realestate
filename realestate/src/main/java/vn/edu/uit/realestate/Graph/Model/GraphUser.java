package vn.edu.uit.realestate.Graph.Model;

import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import vn.edu.uit.realestate.Common.Common;
import vn.edu.uit.realestate.ExceptionHandler.IllegalArgumentException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.Email;

@NodeEntity(label = "User")
public class GraphUser {
	@Id
	private long id;
	@Size(min = 2, message = "Name should have at least 2 characters")
	private String name;
	@Email(message = "Please provide a valid email address")
	private String email;
	@Pattern(regexp = "([0-9]{10}$)", message = "Please provide a valid phone number")
	private String phone;
	@Past(message = "BirthDate must be in the past")
	private Date birthdate;
	private boolean gender;
	private String job;
	@Relationship(type = "POST", direction = Relationship.OUTGOING)
	private List<GraphTrade> trade;
	@Relationship(type = "BROWSE", direction = Relationship.OUTGOING)
	private List<GraphHistory> history;
	private transient SimpleDateFormat dateFormat = new SimpleDateFormat(Common.Constains.LOCAL_DATE_FORMAT);

	public GraphUser() {
		super();
	}

	public GraphUser(Long id, @Size(min = 2, message = "Name should have at least 2 characters") String name,
			@Email(message = "Please provide a valid email address") String email,
			@Pattern(regexp = "([0-9]{10}$)", message = "Please provide a valid phone number") String phone,
			@Past(message = "BirthDate must be in the past. Date Format: dd/MM/yyyy") String birthdate, boolean gender,
			String job) {
		super();
		this.id = id;
		if (name != null) {
			this.name = name;
		}
		if (email != null) {
			this.email = email;
		}
		if (phone != null) {
			this.phone = phone;
		}
		if (birthdate != null && birthdate != "") {
			try {
				this.birthdate = dateFormat.parse(birthdate);
			} catch (ParseException e) {
				e.printStackTrace();
				throw new IllegalArgumentException("The variable 'birthdate' is invalid");
			}
		}
		this.gender = gender;
		if (job != "") {
			this.job = job;
		}
	}

	public Long getId() {
		return id;
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

	public String getBirthdate() {
		if(this.birthdate == null) {
			return null;
		}
		return dateFormat.format(this.birthdate);
	}

	public void setBirthdate(String birthdate) {
		if (birthdate == null) {
			return;
		}
		try {
			this.birthdate = dateFormat.parse(birthdate);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("The variable 'birthdate' is invalid");
		}
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

//	public List<GraphTrade> getTrade() {
//		return trade;
//	}

	public void setTrade(List<GraphTrade> trade) {
		this.trade = trade;
	}

	public void setId(long id) {
		this.id = id;
	}

//	public List<GraphHistory> getHistory() {
//		return history;
//	}

	public void setHistory(List<GraphHistory> history) {
		this.history = history;
	}

//	public List<GraphTrade> getTrade() {
//		return trade;
//	}
}
