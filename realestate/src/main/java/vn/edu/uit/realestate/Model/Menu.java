package vn.edu.uit.realestate.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Menu {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String link;
	private Long parentId;
	public Menu() {
		super();
	}
	public Menu(Long id, String name, String link, Long parentId) {
		super();
		this.id = id;
		this.name = name;
		this.link = link;
		this.parentId = parentId;
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
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
}
