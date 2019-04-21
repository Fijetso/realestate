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
	private long id;
	private String name;
//	@OneToMany(mappedBy="realEstateKind")
//	private Set<Trade> trades;
	
	public RealEstateKind() {
		super();
	}
	
	public RealEstateKind(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

//	public RealEstateKind(long id, String name, Set<Trade> trades) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.trades = trades;
//	}

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

//	public Set<Trade> getTrades() {
//		return trades;
//	}
//
//	public void setTrades(Set<Trade> trades) {
//		this.trades = trades;
//	}
}
