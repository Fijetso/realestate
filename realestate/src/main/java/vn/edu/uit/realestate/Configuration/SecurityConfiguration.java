package vn.edu.uit.realestate.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import vn.edu.uit.realestate.Security.CustomUserDetailsService;
import vn.edu.uit.realestate.Security.JwtAuthenticationFilter;
import vn.edu.uit.realestate.Security.RestAuthenticationEntryPoint;
import vn.edu.uit.realestate.Security.oauth2.CustomOAuth2UserService;
import vn.edu.uit.realestate.Security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository;
import vn.edu.uit.realestate.Security.oauth2.OAuth2AuthenticationFailureHandler;
import vn.edu.uit.realestate.Security.oauth2.OAuth2AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailsService userDetailsService;
	@Autowired
	private CustomOAuth2UserService customOAuth2UserService;
	@Autowired
	private OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
	@Autowired
	private OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

	@Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }
	/*
	 * By default, Spring OAuth2 uses
	 * HttpSessionOAuth2AuthorizationRequestRepository to save the authorization
	 * request. But, since our service is stateless, we can't save it in the
	 * session. We'll save the request in a Base64 encoded cookie instead.
	 */
	@Bean
	public HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository() {
		return new HttpCookieOAuth2AuthorizationRequestRepository();
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(encoder());
		return authProvider;
	}

	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// Get AuthenticationManager bean
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
		auth.userDetailsService(userDetailsService) // Cung cáp userservice cho spring security
				.passwordEncoder(encoder()); // cung cấp password encoder
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().csrf()
				.disable().formLogin().disable().httpBasic().disable().exceptionHandling()
				.authenticationEntryPoint(new RestAuthenticationEntryPoint()).and().authorizeRequests()
				.antMatchers("/", "/register", "/confirm-account", "/reset-password/**", "/login", "/logout",
						"/auth/**", "/oauth2/**")
				.permitAll();

		// Chỉ cho phép user có quyền ADMIN truy cập đường dẫn /admin/**
		http.authorizeRequests().antMatchers("*/admin/**").access("hasRole('ROLE_ADMIN')");
		http.authorizeRequests().antMatchers("*/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')");
		// Chỉ cho phép user có quyền ADMIN hoặc USER truy cập đường dẫn /user/**
		http.authorizeRequests().antMatchers("*/user/**")
				.access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') or hasRole('ROLE_USER')");

		http.oauth2Login().authorizationEndpoint().baseUri("/oauth2/authorize")
				.authorizationRequestRepository(cookieAuthorizationRequestRepository()).and().redirectionEndpoint()
				.baseUri("/login/oauth2/code/*").and().userInfoEndpoint().userService(customOAuth2UserService).and()
				.successHandler(oAuth2AuthenticationSuccessHandler).failureHandler(oAuth2AuthenticationFailureHandler);
		// Thêm một lớp Filter kiểm tra jwt
		http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		// Khi người dùng đã login, với vai trò USER, Nhưng truy cập vào trang yêu cầu
		// vai trò ADMIN, sẽ chuyển hướng tới trang /403
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
		http.authorizeRequests().antMatchers("/secured/**").authenticated();
//		.and().formLogin().disable().oauth2Login().userInfoEndpoint()
//				.userService(this.oauth2UserService()).oidcUserService(this.oidcUserService());
		http.logout().logoutSuccessUrl("/logout/success").logoutUrl("/logout").permitAll();
	}

	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}
}
