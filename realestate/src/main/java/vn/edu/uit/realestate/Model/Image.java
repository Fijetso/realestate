package vn.edu.uit.realestate.Model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties("trade")
public class Image {
	@Id
	@GeneratedValue
	private Long id;
	private String imageLink;
	private String description;
//	@ManyToOne(fetch=FetchType.LAZY)
//	private Trade trade;

	public Image() {
		super();
	}
	
	public Image(Long id, String imageLink, String description) {
		super();
		this.id = id;
		this.imageLink = imageLink;
		this.description = description;
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
}