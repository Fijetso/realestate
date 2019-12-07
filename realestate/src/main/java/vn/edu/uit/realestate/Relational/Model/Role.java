package vn.edu.uit.realestate.Relational.Model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties("users")
public class Role {
	@Id
	@GeneratedValue
	private long id;
	private String name;
	@ManyToMany(mappedBy="roles",cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch=FetchType.LAZY)
	private List<User> users;
	public Role() {
		super();
	}

	public Role(long id, String name, List<User> users) {
		super();
		this.id = id;
		this.name = name;
		this.users = users;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
}
