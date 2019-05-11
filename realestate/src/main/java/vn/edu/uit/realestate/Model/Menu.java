package vn.edu.uit.realestate.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Menu {
	@Id
	@GeneratedValue
	private Long id;
	private String title;
	private String icon;
	private Long parentId;
	public Menu() {
		super();
	}
	public Menu(Long id, String title, String icon, Long parentId) {
		super();
		this.id = id;
		this.title = title;
		this.icon = icon;
		this.parentId = parentId;
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
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
}
