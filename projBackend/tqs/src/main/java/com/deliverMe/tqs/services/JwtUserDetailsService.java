package com.deliverMe.tqs.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.deliverMe.tqs.model.Person;
import com.deliverMe.tqs.model.Rider;
import com.deliverMe.tqs.repository.PersonRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Person> p = personRepository.findByUsername(username);
		if (p==null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(p.get().getUsername(), p.get().getPassword(),
				new ArrayList<>());
	}

}
