package vn.edu.uit.realestate.Relational.Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import vn.edu.uit.realestate.Common.Common;
import vn.edu.uit.realestate.ExceptionHandler.CustomGraphQLException;

@Entity
@JsonIgnoreProperties("trade")
public class Booking {
	@Id
	@GeneratedValue
	private Long id;
	@Size(min = 2, message = "Name of user that want to book should have at least 2 characters")
	private String name;
	@Pattern(regexp = "([0-9]{10}$)", message = "Please provide a valid phone number")
	private String phone;
	@Email(message = "Please provide a valid email address")
	private String email;
	@Future
	private Date timeStart;
	@Future
	private Date timeEnd;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tradeId", referencedColumnName = "id")
	private Trade trade;

	private transient SimpleDateFormat dateFormat = new SimpleDateFormat(Common.Constains.LOCAL_DATE_TIME_FORMAT);

	public Booking() {
		super();
	}

	public Booking(Long id, String name, String phone, String email, Date timeStart, Date timeEnd, Trade trade) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
		this.trade = trade;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTimeStart() {
		if (timeStart == null) {
			return null;
		}
		return dateFormat.format(timeStart);
	}

	public void setTimeStart(String timeStart) {
		try {
			this.timeStart = dateFormat.parse(timeStart);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomGraphQLException(400,
					"IllegalArgumentException: The variable 'timeStart' is invalid");
		}
	}

	public String getTimeEnd() {
		if (timeEnd == null) {
			return null;
		}
		return dateFormat.format(timeEnd);
	}

	public void setTimeEnd(String timeEnd) {
		try {
			this.timeEnd = dateFormat.parse(timeEnd);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomGraphQLException(400,
					"IllegalArgumentException: The variable 'timeEnđ' is invalid");
		}
	}

	public Trade getTrade() {
		return trade;
	}

	public void setTrade(Trade trade) {
		this.trade = trade;
	}
}
