package com.deliverMe.tqs.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.deliverMe.tqs.configuration.JwtTokenUtil;
import com.deliverMe.tqs.model.JwtRequest;
import com.deliverMe.tqs.model.JwtResponse;
import com.deliverMe.tqs.model.Manager;
import com.deliverMe.tqs.model.Person;
import com.deliverMe.tqs.model.Rider;
import com.deliverMe.tqs.repository.PersonRepository;
import com.deliverMe.tqs.services.JwtUserDetailsService;
import com.deliverMe.tqs.services.ManagerService;
import com.deliverMe.tqs.services.RiderService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PersonRepository repository;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@Autowired
	private RiderService riderService;

	@Autowired
	private ManagerService managerService;

	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		Person p = repository.findByUsername(authenticationRequest.getUsername()).get();
		
		final String token = jwtTokenUtil.generateToken(userDetails);

		p.setToken(token);
		System.out.println("skfksajd");

		return ResponseEntity.ok(p);
	}

	@RequestMapping(value = "/registerRider", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody Rider r) throws Exception {
		return ResponseEntity.ok(riderService.saveRider(r));
	}

	@RequestMapping(value = "/registerManager", method = RequestMethod.POST)
	public ResponseEntity<?> saveManager(@RequestBody Manager m) throws Exception {
		return ResponseEntity.ok(managerService.saveManager(m));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}