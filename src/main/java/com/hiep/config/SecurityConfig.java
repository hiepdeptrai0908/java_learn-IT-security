package com.hiep.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hiep.service.CustomerUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	CustomerUserDetailService customerUserDetailService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/", "/login", "/create-user", "/do-create-user", "/style.css").permitAll()
			.anyRequest().authenticated().and()
			.formLogin()
			.loginPage("/login").permitAll()
			.loginProcessingUrl("/check")
			.defaultSuccessUrl("/home")
			.failureUrl("/login-fail")
			.usernameParameter("email")
			.passwordParameter("password");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(customerUserDetailService).passwordEncoder(passwordEncoder());
		
//		auth.inMemoryAuthentication()
//			.withUser("user1").password(passwordEncoder().encode("123"))
//			.authorities("ROLE_USER");
	}

}
