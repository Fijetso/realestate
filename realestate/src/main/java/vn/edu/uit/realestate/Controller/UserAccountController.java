package vn.edu.uit.realestate.Controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.uit.realestate.Common.Common;
import vn.edu.uit.realestate.DataAccess.ConfirmationTokenRepository;
import vn.edu.uit.realestate.DataAccess.RoleRepository;
import vn.edu.uit.realestate.DataAccess.UserRepository;
import vn.edu.uit.realestate.ExceptionHandler.ExistContentException;
import vn.edu.uit.realestate.ExceptionHandler.NotFoundException;
import vn.edu.uit.realestate.Model.Role;
import vn.edu.uit.realestate.Model.User;
import vn.edu.uit.realestate.Model.Security.ConfirmationToken;
import vn.edu.uit.realestate.Service.EmailSenderService;

@RestController
public class UserAccountController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;
	@Autowired
	private EmailSenderService emailSenderService;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		if (userRepository.findByEmail(user.getEmail()).isPresent()) {
			throw new ExistContentException("This email has already existed!");
		} else {
			ConfirmationToken confirmationToken = new ConfirmationToken(user);

			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(user.getEmail());
			mailMessage.setSubject("Complete Registration!");
			mailMessage.setFrom("realestate.uit.edu@gmail.com");
			mailMessage.setText("To confirm your account, please click here : " + Common.Constains.DOMAIN
					+ "/confirm-account?token=" + confirmationToken.getConfirmationToken());
			emailSenderService.sendEmail(mailMessage);

			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(hashedPassword);
			Set<Role> roles = new HashSet<>();
			Role userRole = roleRepository.findByName(Common.Constains.ROLE_USER).get();
			roles.add(userRole);
			user.setRoles(roles);
			userRepository.save(user);
			confirmationTokenRepository.save(confirmationToken);
			return new ResponseEntity<>("Register successfully! Please check your email to confirm", HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/confirm-account", method = RequestMethod.GET)
	public ResponseEntity<?> confirmUserAccount(@RequestParam("token") String confirmationToken) {
		ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
		if (token != null) {
			User user = userRepository.findByEmail(token.getUser().getEmail()).get();
			user.setActive(true);
			userRepository.save(user);
			return new ResponseEntity<>("Account verified", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("The link is invalid or broken!", HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/reset-password", method = RequestMethod.GET)
	public ResponseEntity<?> resetPassword(@RequestParam("email") String userEmail) {
		Optional<User> user = userRepository.findByEmail(userEmail);
		if (user.isPresent() == false) {
			throw new NotFoundException("Cannot find user with email " + userEmail);
		}
		ConfirmationToken confirmationToken = new ConfirmationToken(user.get());
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(userEmail);
		mailMessage.setSubject("Reset Password");
		mailMessage.setFrom("realestate.uit.edu@gmail.com");
		mailMessage.setText("To reset your account password, please click here : " + Common.Constains.DOMAIN
				+ "/reset-password/verify?token=" + confirmationToken.getConfirmationToken());
		emailSenderService.sendEmail(mailMessage);
		confirmationTokenRepository.save(confirmationToken);
		return new ResponseEntity<>("Please check your email to confirm for changing password", HttpStatus.OK);
	}

	@RequestMapping(value = "/reset-password/verify", method = RequestMethod.POST)
	public ResponseEntity<?> resetPasswordVerify(@RequestParam("token") String resetPwdToken,
			@RequestBody String password) {
		ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(resetPwdToken);
		if (token != null) {
			User user = userRepository.findByEmail(token.getUser().getEmail()).get();
			String hashPwd = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
			user.setPassword(hashPwd);
			userRepository.save(user);
			return new ResponseEntity<>("Your new password has been updated", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("The link is invalid or broken!", HttpStatus.BAD_REQUEST);
		}
	}
}