package vn.edu.uit.realestate.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties("trade")
public class Booking {
	@Id
	@GeneratedValue
	private Long id;
	@Size(min=2, message="Name of user that want to book should have at least 2 characters")
	private String name;
	private String phone;
	@Email
	private String email;
	@Future
	private Date timeStart;
	@Future
	private Date timeEnd;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tradeId", referencedColumnName="id")
	private Trade trade;
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
	public Date getTimeStart() {
		return timeStart;
	}
	public void setTimeStart(Date timeStart) {
		this.timeStart = timeStart;
	}
	public Date getTimeEnd() {
		return timeEnd;
	}
	public void setTimeEnd(Date timeEnd) {
		this.timeEnd = timeEnd;
	}
	public Trade getTrade() {
		return trade;
	}
	public void setTrade(Trade trade) {
		this.trade = trade;
	} 
	}
