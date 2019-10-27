package vn.edu.uit.realestate.Model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties("trade")
public class RealImage {
	@Id
	@GeneratedValue
	private Long id;
	private String imageLink;
	private String description;
	@ManyToOne
	@JoinColumn(name="tradeId", referencedColumnName = "id")
	private Trade trade;

	public RealImage() {
		super();
	}
	
	public RealImage(Long id, String imageLink, String description, Trade trade) {
		super();
		this.id = id;
		this.imageLink = imageLink;
		this.description = description;
		this.trade = trade;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Trade getTrade() {
		return trade;
	}

	public void setTrade(Trade trade) {
		this.trade = trade;
	}
}