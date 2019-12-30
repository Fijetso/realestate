package vn.edu.uit.realestate.Relational.Model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties("news")
public class Category {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	@OneToMany(mappedBy="category", fetch = FetchType.LAZY)
	private List<News> news;
	
	public Category() {
		super();
	}
	
	public Category(Long id) {
		super();
		this.id = id;
	}

	public Category(Long id, String name, List<News> news) {
		super();
		this.id = id;
		this.name = name;
		this.news = news;
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

	public List<News> getNews() {
		return news;
	}

	public void setNews(List<News> news) {
		this.news = news;
	}
	 
}
