package vn.edu.uit.realestate.Model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class UserKind {
	@Id
	@GeneratedValue
	private long id;
	private String name;
	@OneToMany(mappedBy="userKind")
	private Set<User> users;
	
	public UserKind() {
		super();
	}
	
	public UserKind(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public UserKind(long id, String name, Set<User> users) {
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

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
}
