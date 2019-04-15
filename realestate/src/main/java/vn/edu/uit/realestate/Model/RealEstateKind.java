package vn.edu.uit.realestate.Model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class RealEstateKind {
	@Id
	@GeneratedValue
	private long realEstateKindId;
	private String realEstateKindName;
	@OneToMany(mappedBy="userKind")
	private Set<User> users;
	
	public RealEstateKind() {
		super();
	}
	
	public RealEstateKind(long realEstateKindId, String realEstateKindName) {
		super();
		this.realEstateKindId = realEstateKindId;
		this.realEstateKindName = realEstateKindName;
	}

	public RealEstateKind(long realEstateKindId, String realEstateKindName, Set<User> users) {
		super();
		this.realEstateKindId = realEstateKindId;
		this.realEstateKindName = realEstateKindName;
		this.users = users;
	}

	public long getRealEstateKindId() {
		return realEstateKindId;
	}
	public void setRealEstateKindId(long realEstateKindId) {
		this.realEstateKindId = realEstateKindId;
	}
	public String getRealEstateKindName() {
		return realEstateKindName;
	}
	public void setRealEstateKindName(String realEstateKindName) {
		this.realEstateKindName = realEstateKindName;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
}
