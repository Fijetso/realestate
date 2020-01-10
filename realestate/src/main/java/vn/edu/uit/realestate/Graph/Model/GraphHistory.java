package vn.edu.uit.realestate.Graph.Model;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;


@NodeEntity(label = "History")
public class GraphHistory {
		@Id
		@GeneratedValue
		private Long id;
		private String key;
		private String value;
		@Relationship(type = "BROWSE", direction = Relationship.INCOMING)
		private GraphUser user;
		
		public GraphHistory() {
			super();
		}
		
		public GraphHistory(String key, String value) {
			super();
			this.key = key;
			this.value = value;
		}

		public GraphHistory(String key, String value, GraphUser user) {
			super();
			this.key = key;
			this.value = value;
			this.user = user;
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public GraphUser getUser() {
			return user;
		}

		public void setUser(GraphUser user) {
			this.user = user;
		}
}
