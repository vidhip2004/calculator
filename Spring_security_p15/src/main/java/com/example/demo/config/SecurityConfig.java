package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
public class SecurityConfig {

	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	//InMemory means the user is stored in application memory, not in MySQL.
	@Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {

		//User is the Spring Security User class:
		//builder() is a static method provided by that class.
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder.encode("password123"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
	
	@Bean
	//HttpSecurity is the object we use to tell Spring Security:"What security rules should my application follow?"
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

	    http //WHO CAN ACCESS WHICH URL? authorizeHttpRequest
	    .csrf(csrf -> csrf.disable())
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/api/public/**").permitAll()
	            .requestMatchers("/api/secure/**").authenticated()
	            .anyRequest().permitAll()
	        )
	        .httpBasic(httpBasic -> {});

	    return http.build();
	}
}
