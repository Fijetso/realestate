package vn.edu.uit.realestate.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties("category")
public class News {
	@Id
	@GeneratedValue
	private Long id;
	private String title;
	private Date composeDate = new Date();
	private String content;
	@ManyToOne(fetch=FetchType.LAZY)
	private Category category;
	
	public News() {
		super();
	}
	
	public News(Long id, String title, Date composeDate, String content, Category category) {
		super();
		this.id = id;
		this.title = title;
		this.composeDate = composeDate;
		this.content = content;
		this.category = category;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getComposeDate() {
		return composeDate;
	}

	public void setComposeDate(Date composeDate) {
		this.composeDate = composeDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
}
