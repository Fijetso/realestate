package vn.edu.uit.realestate.Relational.Model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties("trades")
public class RealEstateKind {
	@Id
	@GeneratedValue
	private long id;
	private String name;
	@OneToMany(mappedBy="realEstateKind",fetch=FetchType.LAZY)
	private List<Trade> trades;
	
	public RealEstateKind() {
		super();
	}
	
	public RealEstateKind(long id, String name, List<Trade> trades) {
		super();
		this.id = id;
		this.name = name;
		this.trades = trades;
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

	public List<Trade> getTrades() {
		return trades;
	}

	public void setTrades(List<Trade> trades) {
		this.trades = trades;
	}
}
