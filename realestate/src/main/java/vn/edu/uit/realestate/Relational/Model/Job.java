package vn.edu.uit.realestate.Relational.Model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Job {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	@OneToMany(mappedBy="job", fetch=FetchType.LAZY)
	private List<User> users;

	public Job() {
		super();
	}
	
	public Job(String name) {
		super();
		this.name = name;
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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
