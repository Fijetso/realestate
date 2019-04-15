package vn.edu.uit.realestate.Model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class TradeKind {
	@Id
	@GeneratedValue
	private long tradeKindId;
	private String tradeKindName;
	@OneToMany(mappedBy="tradeKind")
	private Set<Trade> trades;
	
	public TradeKind() {
		super();
	}
	
	public TradeKind(long tradeKindId, String tradeKindName) {
		super();
		this.tradeKindId = tradeKindId;
		this.tradeKindName = tradeKindName;
	}

	public TradeKind(long tradeKindId, String tradeKindName, Set<Trade> trades) {
		super();
		this.tradeKindId = tradeKindId;
		this.tradeKindName = tradeKindName;
		this.trades = trades;
	}

	public long getTradeKindId() {
		return tradeKindId;
	}

	public void setTradeKindId(long tradeKindId) {
		this.tradeKindId = tradeKindId;
	}

	public String getTradeKindName() {
		return tradeKindName;
	}

	public void setTradeKindName(String tradeKindName) {
		this.tradeKindName = tradeKindName;
	}

	public Set<Trade> getTrades() {
		return trades;
	}

	public void setTrades(Set<Trade> trades) {
		this.trades = trades;
	}
	
}
