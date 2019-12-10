package vn.edu.uit.realestate.Relational.Model.Security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import vn.edu.uit.realestate.Relational.Model.User;

public class CustomUserDetails implements UserDetails{
	
	private User user;
	public CustomUserDetails(User user) {
		super();
		this.user = user;
	}
	

	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}

	///học lại đoạn này khong hieu
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return user.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority("ROLE_"+ role.getName()))
				.collect(Collectors.toList());
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}
    @Override
    public String getPassword() {
        return user.getPassword();
    }

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return user.isActive();
	}

}
