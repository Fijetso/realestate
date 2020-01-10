package vn.edu.uit.realestate.Service.EntityService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.edu.uit.realestate.Common.Common;
import vn.edu.uit.realestate.ExceptionHandler.BadRequestException;
import vn.edu.uit.realestate.ExceptionHandler.IllegalArgumentException;
import vn.edu.uit.realestate.Graph.Model.GraphHistory;
import vn.edu.uit.realestate.Graph.Model.GraphTrade;
import vn.edu.uit.realestate.Graph.Model.Enum.HistoryType;
import vn.edu.uit.realestate.Graph.Repository.GraphHistoryRepository;
import vn.edu.uit.realestate.Graph.Repository.GraphTradeRepository;
import vn.edu.uit.realestate.Graph.Repository.GraphUserRepository;
import vn.edu.uit.realestate.Relational.Model.ModelMapper.SuggestModel;
import vn.edu.uit.realestate.Relational.Repository.AddressRepository;
import vn.edu.uit.realestate.Relational.Repository.BluePrintRepository;
import vn.edu.uit.realestate.Relational.Repository.BookingRepository;
import vn.edu.uit.realestate.Relational.Repository.DetailsRepository;
import vn.edu.uit.realestate.Relational.Repository.RealImageRepository;
import vn.edu.uit.realestate.Relational.Repository.TradeRepository;
import vn.edu.uit.realestate.Service.ModelMapperService;

@Service
public class RecommenderService {
	@Autowired
	private GraphTradeRepository graphTradeRepository;
	@Autowired
	private GraphHistoryRepository graphHistoryRepository;
	@Autowired
	private GraphUserRepository graphUserRepository;

	private Float mostFrequentMetterSquare(List<Float> array) {
		if (array == null || array.size() ==0) {
			return null;
		}
		// Sort the array
		Collections.sort(array);

		// find the max frequency using linear
		// traversal
		int max_count = 1;
		Float res = array.get(0);
		int curr_count = 1;

		for (int i = 1; i < array.size(); i++) {
			if (array.get(i).equals(array.get(i - 1))) {
				curr_count++;
			} else {
				if (curr_count > max_count) {
					max_count = curr_count;
					res = array.get(i - 1);
				}
				curr_count = 1;
			}
		}
		// If last element is most frequent
		if (curr_count > max_count) {
			max_count = curr_count;
			res = array.get(array.size() - 1);
		}
		return res;
	}

	private Integer mostFrequentPriceBillion(List<Integer> array) {
		if (array == null || array.size() ==0) {
			return null;
		}
		// Sort the array
		Collections.sort(array);

		// find the max frequency using linear
		// traversal
		int max_count = 1;
		Integer res = array.get(0);
		int curr_count = 1;

		for (int i = 1; i < array.size(); i++) {
			if (array.get(i).equals(array.get(i - 1))) {
				curr_count++;
			} else {
				if (curr_count > max_count) {
					max_count = curr_count;
					res = array.get(i - 1);
				}
				curr_count = 1;
			}
		}
		// If last element is most frequent
		if (curr_count > max_count) {
			max_count = curr_count;
			res = array.get(array.size() - 1);
		}
		return res;
	}

	public List<GraphTrade> recommendTradesWithContentBased(SuggestModel suggestModel) {
		List<Integer> priceList = new ArrayList<>();
		List<Float> squareList = new ArrayList<>();
		List<Long> districtList = new ArrayList<>();

		if (suggestModel.getUserId() != null) {
			List<GraphHistory> history = graphHistoryRepository.getHistoryByUserId(suggestModel.getUserId());
			for (GraphHistory element : history) {
				HistoryType key = HistoryType.valueOf(element.getKey());
				switch (key) {
				case DISTRICT:
					districtList.add(Long.parseLong(element.getValue()));
					break;
				case PRICE:
					priceList.add(Integer.parseInt(element.getValue()));
					break;
				case SQUARE:
					squareList.add(Float.parseFloat(element.getValue()));
					break;
				default:
				}
			}
		} else {
			priceList = suggestModel.getPriceList();
			squareList = suggestModel.getSquareList();
			districtList = suggestModel.getDistrictIdList();
		}
		Integer priceBillion = mostFrequentPriceBillion(priceList);
		Float metterSquare = mostFrequentMetterSquare(squareList);
		String userJob = graphUserRepository.getUserJob(suggestModel.getUserId());
		return graphTradeRepository.recommendTrades(userJob, districtList, priceBillion, metterSquare);
	}

	public List<GraphTrade> recommendTradesByUserAge(String birthdate, Boolean isFemale) {
		if (birthdate == null) {
			throw new BadRequestException("We cannot recommend trade without birthdate. Please add your birthdate");
		}
		int birthYear;
		try {
			DateFormat dateFormat = new SimpleDateFormat(Common.Constains.LOCAL_DATE_FORMAT);
			Date parsedBirthdate = dateFormat.parse(birthdate);
			birthYear = parsedBirthdate.getYear() + 1900;
		} catch (ParseException e) {
			throw new IllegalArgumentException(
					"Something went wrong with 'birthdate' variable. Be sure the date format is "
							+ Common.Constains.LOCAL_DATE_FORMAT);
		}
		int birthYearSum;
		do {
			birthYearSum = 0;
			while (birthYear >= 1) {
				birthYearSum += birthYear % 10;
				birthYear /= 10;
			}
			birthYear = birthYearSum;
		} while (birthYearSum / 10 >= 1);
		birthYearSum = 11 - birthYearSum;
		if (isFemale) {
			birthYearSum = 15 - birthYearSum;
		}
		birthYear = birthYearSum;
		do {
			birthYearSum = 0;
			while (birthYear >= 1) {
				birthYearSum += birthYear % 10;
				birthYear /= 10;
			}
			birthYear = birthYearSum;
		} while (birthYearSum / 10 >= 1);
		switch (birthYearSum) {
		case 1:
			return graphTradeRepository.recommendTradesByUserAge2(".*ắc");
		case 2:
			return graphTradeRepository.recommendTradesByUserAge(".*ây.*am");
		case 3:
			return graphTradeRepository.recommendTradesByUserAge(".*ông");
		case 4:
			return graphTradeRepository.recommendTradesByUserAge(".*ông.*am");
		case 5:
			return graphTradeRepository.recommendTradesByUserAge(".*");
		case 6:
			return graphTradeRepository.recommendTradesByUserAge(".*ây.*ắc");
		case 7:
			return graphTradeRepository.recommendTradesByUserAge(".*ây");
		case 8:
			return graphTradeRepository.recommendTradesByUserAge(".*ông.*ắc");
		case 9:
			return graphTradeRepository.recommendTradesByUserAge2(".*am");
		default:
		}
		return null;
	}
}
