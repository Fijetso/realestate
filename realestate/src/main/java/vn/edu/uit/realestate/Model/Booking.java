package vn.edu.uit.realestate.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Booking {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String phone;
	private String email;
	private Date timeStart;
	private Date timeEnd;
	@OneToOne(mappedBy = "booking")
	private Trade trade;
	public Booking() {
		super();
	}
	public Booking(Long id, String name, String phone, String email, Date timeStart, Date timeEnd,
			Trade trade) {
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
