package vn.edu.uit.realestate.Relational.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
//@JsonIgnoreProperties("trade")
public class Image {
	@Id
	@GeneratedValue
	private Long id;
	private String imageLink;
	///True: realImage False: blueprints
//	private boolean tradeImageKind;
	private String description;
//	@ManyToOne(fetch=FetchType.LAZY)
//	private Trade trade;

	public Image() {
		super();
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