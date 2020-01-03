package vn.edu.uit.realestate.Graph.Repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.edu.uit.realestate.Graph.Model.GraphTrade;

@Repository
public interface GraphTradeRepository extends Neo4jRepository<GraphTrade, Long> {
	@Query("MATCH (n) DETACH DELETE n")
	public void deleteEntireNeo4j();

	@Query("MATCH (t:Trade) WHERE t.Id={id} SET t.tradeStatus = {tradeStatus} RETURN t")
	public GraphTrade updateTradeStatus(long id, String tradeStatus);

	@Query("MATCH (u:User)-[p]-(t:Trade)-[h]-(a:Address) WHERE CASE WHEN NOT {job} IS NULL THEN "
			+ "u.job = {job} ELSE TRUE END OR CASE WHEN NOT  {districtId} IS NULL THEN a.district = {districtId}"
			+ " ELSE TRUE END WITH t LIMIT 5 OPTIONAL MATCH (t)-[W]-(w) RETURN *")
	public List<GraphTrade> recommendTrades(@Param("job") String job, @Param("districtId") List<Long> districtId);

	@Query("MATCH (d:Details)--(t:Trade) WHERE d.direction=~{direction} OPTIONAL MATCH (w)-[W]-(t) RETURN *")
	public List<GraphTrade> recommendTradesByUserAge(@Param("direction") String directionWithRegex);

	@Query("MATCH (d:Details)--(t:Trade) WHERE d.direction=~{direction} AND NOT d.direction=~'.*ông.*' "
			+ "AND NOT d.direction=~'.*ây.*' OPTIONAL MATCH (w)-[W]-(t) RETURN *")
	public List<GraphTrade> recommendTradesByUserAge2(@Param("direction") String directionWithRegex);
}
