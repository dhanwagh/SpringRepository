package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	public void  configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeRequests()
		.anyRequest().hasAnyRole().and().httpBasic();
		httpSecurity.csrf().disable();
		//.antMatchers("/svc/v1/private/accounts/*").hasRole("USER")
	

	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		 BCryptPasswordEncoder encoder = passwordEncoder();
		auth.inMemoryAuthentication()
		.passwordEncoder(encoder)
		.withUser("dhanwagh").password("1234").roles("USER")
		.and()
		.withUser("wagh").password("1234").roles("USER","ADMIN");
	}
	
	    @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
}
