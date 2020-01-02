package vn.edu.uit.realestate.Relational.Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import vn.edu.uit.realestate.Common.Common;

@Entity
public class News {
	@Id
	@GeneratedValue
	private Long id;
	private String title;
	private Date composeDate = new Date();
	@Column(columnDefinition="LONGTEXT")
	private String content;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "categoryId", referencedColumnName = "id")
	private Category category;
	private String author;

	private transient SimpleDateFormat dateFormat = new SimpleDateFormat(Common.Constains.LOCAL_DATE_FORMAT);
	
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
	
	public News(String title, String content, String author) {
		super();
		this.title = title;
		this.content = content;
		this.author = author;
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

	public String getComposeDate() {
		if (composeDate == null) {
			return null;
		}
		return dateFormat.format(composeDate);
	}

	public void setComposeDate(String composeDate) {
		try {
			this.composeDate = dateFormat.parse(composeDate);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(
					"The variable 'composeDate' is invalid");
		}
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
}
