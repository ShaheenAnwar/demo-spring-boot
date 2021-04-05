package com.sa.SBSecureApp.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.AntPathMatcher;

//extends the WebSecurityConfigurerAdapter class and add @Configuration annotation
//@EnableWebSecurity enables web security annotation. Now override userDetailsService method .

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter{
	/**to store user credentials in-memory*/
/*	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		List<UserDetails> users = new ArrayList<>(); //UserDetails is inbuilt class
		users.add(User.withDefaultPasswordEncoder().username("mac").password("12345").roles("USER").build());	//User is inbuilt class
		return new InMemoryUserDetailsManager(users); // for in-memory 
		
	}*/
	
	
	@Autowired
	private UserDetailsService userDetailsService;	//need to create a class for this interface
	
	@Bean
	public AuthenticationProvider authProvider() {
		//implementatioan of Aunthentication provider
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		//provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance()); //to set pwd encoding. we chose no encoding here
		/**bcrypt password encoding: By Default Spring Boot has bcrypt library*/
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
	}

	/**Overriding for custom login-logout page*/
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
			csrf().
			disable().
			authorizeRequests().
			antMatchers("/login").
			permitAll().
			and().
			formLogin().
			loginPage("/login").
			permitAll().
			and().
			logout().
			invalidateHttpSession(true).
			clearAuthentication(true).
			logoutRequestMatcher(new AntPathRequestMatcher("/logout")).
			logoutSuccessUrl("/logout-success").
			permitAll();
	}
	
	
}
