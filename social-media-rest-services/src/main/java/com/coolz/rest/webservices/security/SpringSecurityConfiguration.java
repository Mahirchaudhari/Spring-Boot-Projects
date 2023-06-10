package com.coolz.rest.webservices.security;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * SpringSecurityConfiguration is configuration class which is used to
 * authenticate each and every REST request
 * 
 * @author Mahir
 *
 */
@Configuration
public class SpringSecurityConfiguration {

	/**
	 * Override method to authenticate each and every request
	 * 
	 * @param httpSecurity
	 * @return
	 * @throws Exception
	 */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

		// all request should be authenticated
		httpSecurity.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());

		// if a request if not authenticated, a web page is shown
		httpSecurity.httpBasic(withDefaults());

		// authenticate CSRF (POST, PUT)
		httpSecurity.csrf().disable();

		return httpSecurity.build();
	}

}
