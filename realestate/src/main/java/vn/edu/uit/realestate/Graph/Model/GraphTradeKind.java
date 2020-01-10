package vn.edu.uit.realestate.Graph.Model;

import java.util.List;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity(label = "TradeKind")
public class GraphTradeKind {
	@Id
	private long id;
	private String name;
	@Relationship(type = "CONTAIN", direction = Relationship.OUTGOING)
	private List<GraphTrade> trade;

	public GraphTradeKind() {
		super();
	}

	public GraphTradeKind(long id, String name) {
		super();
		this.id = id;
		this.name = name;
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

//	public List<GraphTrade> getTrade() {
//		return trade;
//	}

	public void setTrade(List<GraphTrade> trade) {
		this.trade = trade;
	}
}
