package vn.edu.uit.realestate.GraphQLResolver.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.uit.realestate.ExceptionHandler.CustomGraphQLException;
import vn.edu.uit.realestate.Graph.Model.GraphHistory;
import vn.edu.uit.realestate.Graph.Model.GraphUser;
import vn.edu.uit.realestate.Graph.Model.Enum.HistoryType;
import vn.edu.uit.realestate.Graph.Repository.GraphHistoryRepository;
import vn.edu.uit.realestate.Graph.Repository.GraphUserRepository;

@Service
public class GraphQLHistoryService {

	@Autowired
	private GraphHistoryRepository historyRepository;
	@Autowired
	private GraphUserRepository userRepository;

	public String saveHistory(final Long userId, final Long district, final int price, final Float square) {
		String result = "";
		Optional<GraphUser> user = userRepository.findUserById(userId);
		if (!user.isPresent()) {
			throw new CustomGraphQLException(400, "NotFoundException: Cannot find any user with Id: " + userId);
		}
		if (district != null) {
			GraphHistory history = new GraphHistory(HistoryType.DISTRICT.toString(), Long.toString(district), user.get());
			historyRepository.save(history);
			result += HistoryType.DISTRICT.toString() + ": " + Long.toString(district) + "/n";
		}
		if (price > 0) {
			GraphHistory history = new GraphHistory(HistoryType.PRICE.toString(), Integer.toString(price), user.get());
			historyRepository.save(history);
			result += HistoryType.PRICE.toString() + ": " + Integer.toString(price) + "/n";
		}
		if (square != null) {
			GraphHistory history = new GraphHistory(HistoryType.SQUARE.toString(), Float.toString(square), user.get());
			historyRepository.save(history);
			result += HistoryType.SQUARE.toString() + ": " + Float.toString(square) + "/n";
		}
		return result;
	}
}
