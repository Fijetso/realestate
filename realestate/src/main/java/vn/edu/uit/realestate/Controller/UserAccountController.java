package vn.edu.uit.realestate.Controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.uit.realestate.Common.Common;
import vn.edu.uit.realestate.Common.SpecificString;
import vn.edu.uit.realestate.ExceptionHandler.ExistContentException;
import vn.edu.uit.realestate.ExceptionHandler.NotFoundException;
import vn.edu.uit.realestate.Filter.JwtTokenProvider;
import vn.edu.uit.realestate.Relational.Model.Role;
import vn.edu.uit.realestate.Relational.Model.User;
import vn.edu.uit.realestate.Relational.Model.Security.ConfirmationToken;
import vn.edu.uit.realestate.Relational.Model.Security.CustomUserDetails;
import vn.edu.uit.realestate.Relational.Repository.ConfirmationTokenRepository;
import vn.edu.uit.realestate.Relational.Repository.RoleRepository;
import vn.edu.uit.realestate.Relational.Repository.UserRepository;
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
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider tokenProvider;

//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public LoginResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
//
//		// Xác thực từ username và password.
//		Authentication authentication = authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//
//		// Nếu không xảy ra exception tức là thông tin hợp lệ
//		// Set thông tin authentication vào Security Context
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//
//		// Trả về jwt cho người dùng.
//		String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
//		return new LoginResponse(jwt);
//	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		if (userRepository.findByEmail(user.getEmail()).isPresent()) {
			throw new ExistContentException(SpecificString.email_is_existed);
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
			return new ResponseEntity<>(SpecificString.successful_registration, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/confirm-account", method = RequestMethod.GET)
	public ResponseEntity<?> confirmUserAccount(@RequestParam("token") String confirmationToken) {
		ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
		if (token != null) {
			User user = userRepository.findByEmail(token.getUser().getEmail()).get();
			user.setActive(true);
			userRepository.save(user);
			return new ResponseEntity<>(SpecificString.account_verified, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(SpecificString.invalid_or_broken_link, HttpStatus.BAD_REQUEST);
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
		return new ResponseEntity<>(SpecificString.check_email_to_confirm_changing_password, HttpStatus.OK);
		/// nói T chuyển hướng thành dạng post có chứa password mới
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
			return new ResponseEntity<>(SpecificString.new_password_has_been_updated, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(SpecificString.invalid_or_broken_link, HttpStatus.BAD_REQUEST);
		}
	}
}
