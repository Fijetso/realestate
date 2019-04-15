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
	private long userKindId;
	private String userKindName;
	@OneToMany(mappedBy="userKind")
	private Set<User> users;
	
	public UserKind() {
		super();
	}
	
	public UserKind(long userKindId, String userKindName) {
		super();
		this.userKindId = userKindId;
		this.userKindName = userKindName;
	}

	public UserKind(long userKindId, String userKindName, Set<User> users) {
		super();
		this.userKindId = userKindId;
		this.userKindName = userKindName;
		this.users = users;
	}

	public long getUserKindId() {
		return userKindId;
	}
	public void setUserKindId(long userKindId) {
		this.userKindId = userKindId;
	}
	public String getUserKindName() {
		return userKindName;
	}
	public void setUserKindName(String userKindName) {
		this.userKindName = userKindName;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
}
