package vn.edu.uit.realestate.GraphQLResolver.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import vn.edu.uit.realestate.Common.Common;
import vn.edu.uit.realestate.Common.SpecificString;
import vn.edu.uit.realestate.ExceptionHandler.CustomGraphQLException;
import vn.edu.uit.realestate.Graph.Repository.GraphUserRepository;
import vn.edu.uit.realestate.Relational.Model.Job;
import vn.edu.uit.realestate.Relational.Model.Role;
import vn.edu.uit.realestate.Relational.Model.User;
import vn.edu.uit.realestate.Relational.Model.UserKind;
import vn.edu.uit.realestate.Relational.Model.Security.ConfirmationToken;
import vn.edu.uit.realestate.Relational.Repository.ConfirmationTokenRepository;
import vn.edu.uit.realestate.Relational.Repository.JobRepository;
import vn.edu.uit.realestate.Relational.Repository.RoleRepository;
import vn.edu.uit.realestate.Relational.Repository.UserKindRepository;
import vn.edu.uit.realestate.Relational.Repository.UserRepository;
import vn.edu.uit.realestate.Security.AuthProvider;
import vn.edu.uit.realestate.Security.JwtTokenProvider;
import vn.edu.uit.realestate.Service.EmailSenderService;
import vn.edu.uit.realestate.Service.ModelMapperService;

@Service
public class GraphQLUserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	ModelMapperService modelMapper;
	@Autowired
	private JobRepository jobRepository;
	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;
	@Autowired
	private EmailSenderService emailSenderService;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private GraphUserRepository graphUserRepository;
	@Autowired
	private UserKindRepository userKindRepository;

	public User register(final String name, final String email, final String password, final String phone,
			final String birthdate, final Boolean gender, final String job, final Long userKindId,
			final String imageLink) {
		if (userRepository.findByEmail(email).isPresent()) {
			throw new CustomGraphQLException(400, SpecificString.email_is_existed);
		} else {
			User newUser = new User();

			newUser.setEmail(email);
			if (name != null) {
				newUser.setName(name);
			}
			newUser.setName(name);

			if (phone != null)
				newUser.setPhone(phone);
			if (birthdate != null) {
				newUser.setBirthdate(birthdate);
			}
			if (gender != null)
				newUser.setGender(gender);
			if (job != null) {
				Optional<Job> jobInMySQL = jobRepository.findByName(job);
				if (jobInMySQL.isPresent()) {
					newUser.setJob(jobInMySQL.get());
				} else {
					Job newJob = new Job(job);
					newJob = jobRepository.save(newJob);
					newUser.setJob(newJob);
				}
			}
			if (userKindId != null) {
				Optional<UserKind> userKindInMySQL = userKindRepository.findById(userKindId);
				if (userKindInMySQL.isPresent()) {
					newUser.setUserKind(userKindInMySQL.get());
				} else {
					throw new CustomGraphQLException(400,
							"Not Found Exception: Cannot find any UserKind in MySQL with Id=" + userKindId);
				}
			}
			if (imageLink != null) {
				newUser.setImageUrl(imageLink);
			}
			newUser.setProvider(AuthProvider.local);
			newUser.setProviderId("0");

			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(password);
			newUser.setPassword(hashedPassword);

			Set<Role> roles = new HashSet<>();
			Role userRole = roleRepository.findByName(Common.Constains.ROLE_USER).get();
			roles.add(userRole);
			newUser.setRoles(roles);
			/// setId cho newUser
			newUser = userRepository.save(newUser);
			graphUserRepository.save(modelMapper.convertUser(newUser));

			ConfirmationToken confirmationToken = new ConfirmationToken(newUser);
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(email);
			mailMessage.setSubject("Complete Registration!");
			mailMessage.setFrom("realestate.uit.edu@gmail.com");
			mailMessage.setText("To confirm your account, please click here : " + Common.Constains.DOMAIN
					+ "/confirm-account?token=" + confirmationToken.getConfirmationToken());
			emailSenderService.sendEmail(mailMessage);

			confirmationTokenRepository.save(confirmationToken);
			return newUser;
		}
	}

	public User loginBySocial(final String name, final String email, final String authProvider, final String phone,
			final String birthdate, final Boolean gender, final String job, final Long userKindId,
			final String imageLink) {
		Optional<User> foundUser = userRepository.findByEmail(email);
		if (foundUser.isPresent()) {
			if (AuthProvider.valueOf(authProvider.toLowerCase()) == AuthProvider.local) {
				throw new CustomGraphQLException(400, "This function is for Social Account only");
			}
			if (foundUser.get().getProvider() != AuthProvider.valueOf(authProvider.toLowerCase())) {
				throw new CustomGraphQLException(400, "You've already register this app with email via "
						+ foundUser.get().getProvider() + ", not " + authProvider);
			} else {
				return foundUser.get();
			}
		} else {
			User newUser = new User();
			newUser.setEmail(email);
			if (name != null) {
				newUser.setName(name);
			}
			newUser.setName(name);
			if (authProvider != null) {
				newUser.setProvider(AuthProvider.valueOf(authProvider));
			}
			if (phone != null)
				newUser.setPhone(phone);
			if (birthdate != null) {
				newUser.setBirthdate(birthdate);
			}
			if (gender != null)
				newUser.setGender(gender);
			if (job != null) {
				Optional<Job> jobInMySQL = jobRepository.findByName(job);
				if (jobInMySQL.isPresent()) {
					newUser.setJob(jobInMySQL.get());
				} else {
					Job newJob = new Job(job);
					newJob = jobRepository.save(newJob);
					newUser.setJob(newJob);
				}
			}
			if (userKindId != null) {
				Optional<UserKind> userKindInMySQL = userKindRepository.findById(userKindId);
				if (userKindInMySQL.isPresent()) {
					newUser.setUserKind(userKindInMySQL.get());
				} else {
					throw new CustomGraphQLException(400,
							"Not Found Exception: Cannot find any UserKind in MySQL with Id=" + userKindId);
				}
			}
			if (imageLink != null) {
				newUser.setImageUrl(imageLink);
			}

			Set<Role> roles = new HashSet<>();
			Role userRole = roleRepository.findByName(Common.Constains.ROLE_USER).get();
			roles.add(userRole);
			newUser.setRoles(roles);
			newUser.setActive(true);
			/// setId cho newUser
			newUser = userRepository.save(newUser);
			graphUserRepository.save(modelMapper.convertUser(newUser));
			return newUser;
		}
	}

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenProvider tokenProvider;

	public String login(final String email, final String password) {
		// Xác thực từ username và password.
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(email, password));

		// Nếu không xảy ra exception tức là thông tin hợp lệ
		// Set thông tin authentication vào Security Context
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Trả về jwt cho người dùng.
		String token = tokenProvider.createToken(authentication);
//		String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
		return token;
	}

	public User updateUserGraphQL(final Long userId, final String name, final String email, final String phone,
			final String birthdate, final Boolean gender, final String job, final Long userKindId) {
		Optional<User> user = userRepository.findById(userId);
		user.orElseThrow(() -> new CustomGraphQLException(400,
				"Not Found Exception: Cannot find any User in MySQL with Id=" + userId));
		User foundUser = user.get();
		if (name != null)
			foundUser.setName(name);
		if (email != null)
			foundUser.setEmail(email);
		if (phone != null)
			foundUser.setPhone(phone);
		if (birthdate != null) {
			foundUser.setBirthdate(birthdate);
		}
		if (gender != null)
			foundUser.setGender(gender);
		if (job != null) {
			Optional<Job> jobInMySQL = jobRepository.findByName(job);
			jobInMySQL.ifPresentOrElse((foundJob) -> foundUser.setJob(foundJob), () -> foundUser.setJob(new Job(job)));
		}
		if (userKindId != null) {
			Optional<UserKind> userKindInMySQL = userKindRepository.findById(userKindId);
			userKindInMySQL.ifPresent((foundUserKind) -> foundUser.setUserKind(foundUserKind));
			userKindInMySQL.orElseThrow(() -> new CustomGraphQLException(400,
					"Not Found Exception: Cannot find any UserKind in MySQL with Id=" + userKindId));
		}
		graphUserRepository.save(modelMapper.convertUser(foundUser));
		return userRepository.save(foundUser);
	}
}
