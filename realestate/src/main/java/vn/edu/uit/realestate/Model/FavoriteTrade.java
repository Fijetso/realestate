package vn.edu.uit.realestate.Model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"trade","user"})
@Entity
public class FavoriteTrade {
	@EmbeddedId
	private FavoriteTradeKey id;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("userId")
	@JoinColumn(name = "userId", referencedColumnName = "id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("tradeId")
	@JoinColumn(name = "tradeId", referencedColumnName = "id")
	private Trade trade;

	public FavoriteTrade() {
		super();
	}

	public FavoriteTradeKey getId() {
		return id;
	}

	public void setId(Long userId, Long tradeId) {
		this.id = new FavoriteTradeKey(userId, tradeId);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Trade getTrade() {
		return trade;
	}

	public void setTrade(Trade trade) {
		this.trade = trade;
	}
}

@Embeddable
class FavoriteTradeKey implements Serializable {
	private static final long serialVersionUID = 1L;
	Long userId;
	Long tradeId;

	public FavoriteTradeKey() {
		super();
	}

	public FavoriteTradeKey(Long userId, Long tradeId) {
		super();
		this.userId = userId;
		this.tradeId = tradeId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getTradeId() {
		return tradeId;
	}

	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || o.getClass() != this.getClass())
			return false;
		// type casting of the argument.
		FavoriteTradeKey favoriteTradeKey = (FavoriteTradeKey) o;
		// comparing the state of argument with
		// the state of 'this' Object.
		return (favoriteTradeKey.userId == this.userId && favoriteTradeKey.tradeId == this.tradeId);
	}
	public int hashCode() {
		return Objects.hash(userId, tradeId);
	}
}
