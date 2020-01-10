package vn.edu.uit.realestate.GraphQLResolver;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;

import vn.edu.uit.realestate.GraphQLResolver.Service.*;
import vn.edu.uit.realestate.Relational.Model.*;

@Component
public class Mutation implements GraphQLMutationResolver {

	@Autowired
	GraphQLTradeService tradeService;
	@Autowired
	GraphQLUserService userService;
	@Autowired
	GraphQLNewsService newsService;
	@Autowired
	GraphQLCategoryService categoryService;
	@Autowired
	GraphQLBookingService bookingService;
	@Autowired
	GraphQLHistoryService historyService;

	public String login(final String email, final String password) {
		return userService.login(email, password);
	}
	
	public User loginBySocial(final String name, final String email, final String authProvider, final String phone,
			final String birthdate, final Boolean gender, final String job, final Long userKindId,
			final String imageLink) {
		return userService.loginBySocial(name, email, authProvider, phone, birthdate, gender, job, userKindId, imageLink);
	}
	
	public User register(final String name, final String email, final String password, final String phone,
			final String birthdate, final Boolean gender, final String job, final Long userKindId, final String imageLink) {
		return userService.register(name, email, password, phone, birthdate, gender, job, userKindId, imageLink);
	}
	
	public User updateUser(final long userId, final String name, final String email, final String phone, final String birthdate,
			final Boolean gender, final String job, final Long userKindId) {
		return userService.updateUserGraphQL(userId, name, email, phone, birthdate, gender, job, userKindId);
	}
	
	
	public Trade saveTrade(final Long tradeId, final String description, final Long cost, final Long userId, final Long realEstateKindId,
			final Long tradeKindId, final String detailAddress, final Long wardId, final Float length, final Float width,
			final Float square, final String direction, final String floors, final String legalDocuments,
			final int bathrooms, final int bedrooms, final String utilities, final String others, final Float longitude,
			final Float latitude, final ArrayList<String> realImages, final ArrayList<String> bluePrints) {
		return tradeService.saveTradeGraphQL(tradeId, description, cost, userId, realEstateKindId, tradeKindId, detailAddress,
				wardId, length, width, square, direction, floors, legalDocuments, bathrooms, bedrooms, utilities,
				others, longitude, latitude, realImages, bluePrints);
	}

	public News saveNews(final Long newsId, final String title, final String content, final Long categoryId, final String composeDate, final String author) {
		return newsService.saveNews(newsId, title, content, categoryId, composeDate, author);
	}
	
	public Category saveCategory(final Long categoryId, final String name) {
		return categoryService.saveCategory(categoryId, name);
	}
	
	public Booking saveBooking(final String name, final String phone, final String email, final String timeStart, final String timeEnd, final Long tradeId) {
		return bookingService.saveBooking(name, phone, email, timeStart, timeEnd, tradeId);
	}
	
	public String saveHistory(final Long userId, final Long district, final int price, final Float square) {
		return historyService.saveHistory(userId, district, price, square);
	}
}
