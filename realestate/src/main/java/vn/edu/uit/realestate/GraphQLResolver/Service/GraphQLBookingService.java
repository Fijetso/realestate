package vn.edu.uit.realestate.GraphQLResolver.Service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import vn.edu.uit.realestate.Common.Common;
import vn.edu.uit.realestate.ExceptionHandler.CustomGraphQLException;
import vn.edu.uit.realestate.Relational.Model.Booking;
import vn.edu.uit.realestate.Relational.Model.Trade;
import vn.edu.uit.realestate.Relational.Model.AddressTree.Ward;
import vn.edu.uit.realestate.Relational.Repository.TradeRepository;
import vn.edu.uit.realestate.Relational.Repository.AddressTree.WardRepository;
import vn.edu.uit.realestate.Service.EmailSenderService;

@Service
public class GraphQLBookingService {
	@Autowired
	private TradeRepository tradeRepository;
	@Autowired
	private WardRepository wardRepository;
	@Autowired
	private EmailSenderService emailSenderService;

	public Booking saveBooking(final String name, final String phone, final String email, final String timeStart,
			final String timeEnd, final Long tradeId) {
		Booking newBooking = new Booking();
		if (name != null) {
			newBooking.setName(name);
		}
		if (phone != null) {
			String regex = "([0-9]{10}$)";
			if (!phone.matches(regex)) {
				throw new CustomGraphQLException(400, "IllegalArgumentException: Please add a valid phone number");
			}
			newBooking.setPhone(phone);
		}
		if (email != null) {
			String regex = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";
			if (!email.matches(regex)) {
				throw new CustomGraphQLException(400, "IllegalArgumentException: Please add a valid email");
			}
			newBooking.setEmail(email);
		}
		if (timeStart != null) {
			newBooking.setTimeStart(timeStart);
		}
		if (timeEnd != null) {
			newBooking.setTimeEnd(timeEnd);
		}
		if (tradeId != null) {
			Optional<Trade> foundTrade = tradeRepository.findById(tradeId);
			Trade trade = foundTrade.get();
			if (foundTrade.isPresent()) {
				newBooking.setTrade(foundTrade.get());
				Optional<Ward> ward = wardRepository.findById(trade.getAddress().getWard());
				ward.orElseThrow(() -> new CustomGraphQLException(400,
						"Not Found Exception: Cannot find Ward Id = " + trade.getAddress().getWard()));

				SimpleMailMessage mailMessage = new SimpleMailMessage();
				mailMessage.setTo(trade.getUser().getEmail());
				mailMessage.setSubject("[REVN]Someone wants to book a date for checking your real estate");
				mailMessage.setFrom("realestate.uit.edu@gmail.com");
				mailMessage.setText(
						"We have to inform you that you have someone wanted to see your real estate: Here are his/her information:\nName: "
								+ name + "\nPhone number: " + phone + "\nEmail: " + email + "\nFreetime: " + timeStart
								+ " - " + timeEnd + "\n Trade information that she/he wants to see: \n\tDescription: "
								+ trade.getDescription() + "\n\tCost: " + trade.getCost() + "\n\tAddress: "
								+ trade.getAddress().getDetail() + ward.get().getPathWithType());
				emailSenderService.sendEmail(mailMessage);
			} else {
				throw new CustomGraphQLException(400, "Cannot find any Trade with id=" + tradeId);
			}
		}
		return newBooking;
	}
}
