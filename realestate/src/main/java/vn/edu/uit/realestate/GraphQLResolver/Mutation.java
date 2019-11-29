package vn.edu.uit.realestate.GraphQLResolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;

import vn.edu.uit.realestate.GraphQLResolver.Service.GraphQLTradeService;
import vn.edu.uit.realestate.Relational.Model.Trade;
import vn.edu.uit.realestate.Relational.Model.User;

@Component
public class Mutation implements GraphQLMutationResolver {

	@Autowired
	GraphQLTradeService tradeService;

	public Trade saveTrade(final String description, final Long cost, final Long userId, final Long realEstateKindId,
			final Long tradeKindId, final String detailAddress, final Long wardId, final Long length, final Long width,
			final Long square, final String direction, final String floors, final String legalDocuments,
			final int bathrooms, final int bedrooms, final String utilities, final String others, final Long longitude,
			final Long latitude) {
		return tradeService.saveTradeGraphQL(description, cost, userId, realEstateKindId, tradeKindId, detailAddress,
				wardId, length, width, square, direction, floors, legalDocuments, bathrooms, bedrooms, utilities,
				others, longitude, latitude);
	}

	public Trade updateTrade(final Long tradeId, final String description, final Long cost, final Long realEstateKindId,
			final Long tradeKindId, final String detailAddress, final Long wardId, final Long length, final Long width,
			final Long square, final String direction, final String floors, final String legalDocuments,
			final int bathrooms, final int bedrooms, final String utilities, final String others, final Long longitude,
			final Long latitude) {
		return tradeService.updateTradeGraphQL(tradeId, description, cost, realEstateKindId, tradeKindId, detailAddress,
				wardId, length, width, square, direction, floors, legalDocuments, bathrooms, bedrooms, utilities,
				others, longitude, latitude);
	}

	public User updateUser(final Long userId, final String name, final String email, final String phone, final String birthdate,
			final Boolean gender, final String job, final Long userKindId) {
		return tradeService.updateUserGraphQL(userId, name, email, phone, birthdate, gender, job, userKindId);
	}
}
