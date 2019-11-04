package vn.edu.uit.realestate.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import vn.edu.uit.realestate.Model.User;
import vn.edu.uit.realestate.Repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optionalUser = userRepository.findByEmail(username);
		optionalUser.orElseThrow(() -> new UsernameNotFoundException("Email not found"));
//		optionalUser
//		.ifPresent(user ->  new CustomUserDetails(user));
		///khong hieu khuc nay
		User user = optionalUser.get();
		if(user.isActive()==false) {
				throw new RuntimeException("The Account hasn't activated yet");
		}
		List<GrantedAuthority> authorities = new ArrayList<>();
		user.getRoles().forEach(authority ->
			authorities.add(new SimpleGrantedAuthority("ROLE_"+authority.getName()))
				);
		return org.springframework.security.core.userdetails.User.builder().username(user.getEmail()).password(user.getPassword()).authorities(authorities).build();
	}
}
