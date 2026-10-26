package com.example.demo.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.example.demo.entity.AppUser;
import com.example.demo.repository.AppUserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	private final AppUserRepository appUserRepository;

	public CustomUserDetailsService(AppUserRepository appUserRepository) {
		super();
		this.appUserRepository = appUserRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		AppUser appUser=appUserRepository.findByUsername(username)
			.orElseThrow(() ->
                new UsernameNotFoundException("User not found: " + username)
            );
		
		return User.builder()
				.username(appUser.getUsername())
				.password(appUser.getPassword())
				.roles(appUser.getRole())
				.build();
	}
	
}
