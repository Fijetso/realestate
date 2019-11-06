package vn.edu.uit.realestate.GraphQLResolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;

import vn.edu.uit.realestate.Relational.Model.Trade;
import vn.edu.uit.realestate.Service.EntityService.TradeService;

@Component
public class Mutation implements GraphQLMutationResolver {

	@Autowired
	TradeService tradeService;

	public Trade saveTrade(final String description, final Long cost, final Long userId, final Long realEstateKindId,
			final Long tradeKindId, final String detailAddress, final Long wardId, final Long length, final Long width,
			final Long square, final String direction, final String floors, final String legalDocuments,
			final int bathrooms, final int bedrooms, final String utilities, final String others) {
		return tradeService.saveTradeGraphQL(description, cost, userId, realEstateKindId, tradeKindId, detailAddress,
				wardId, length, width, square, direction, floors, legalDocuments, bathrooms, bedrooms, utilities,
				others);
	}
}
