package vn.edu.uit.realestate.Security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import vn.edu.uit.realestate.Relational.Model.User;
import vn.edu.uit.realestate.Relational.Model.Security.CustomUserDetails;
import vn.edu.uit.realestate.Relational.Repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optionalUser = userRepository.findByEmail(username);
		optionalUser.orElseThrow(() -> new UsernameNotFoundException("Email not found"));
		User user = optionalUser.get();
		if (user.isActive() == false) {
			throw new BadCredentialsException("Your Account hasn't activated yet. Please check your email first");
		}
		List<GrantedAuthority> authorities = new ArrayList<>();
		user.getRoles()
				.forEach(authority -> authorities.add(new SimpleGrantedAuthority("ROLE_" + authority.getName())));
		return new CustomUserDetails(user);
//		return org.springframework.security.core.userdetails.User.builder().username(user.getEmail())
//				.password(user.getPassword()).authorities(authorities).disabled(!user.isActive()).build();
	}

	@Transactional
	public UserDetails loadUserById(Long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		optionalUser.orElseThrow(() -> new UsernameNotFoundException("user Id=" + id + " not found"));
		User user = optionalUser.get();
		if (user.isActive() == false) {
			throw new BadCredentialsException("Your Account hasn't activated yet. Please check your email first");
		}
		List<GrantedAuthority> authorities = new ArrayList<>();
		user.getRoles()
				.forEach(authority -> authorities.add(new SimpleGrantedAuthority("ROLE_" + authority.getName())));

		return UserPrincipal.create(user);
//		return org.springframework.security.core.userdetails.User.builder().username(user.getEmail())
//				.password(user.getPassword()).authorities(authorities).disabled(!user.isActive()).build();
	}
}
