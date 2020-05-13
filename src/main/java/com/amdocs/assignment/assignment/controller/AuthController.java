package com.amdocs.assignment.assignment.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.amdocs.assignment.assignment.authutill.JWTAuthenticationFilter;
import com.amdocs.assignment.assignment.service.AuthService;
import com.amdocs.assignment.assignment.vo.UserAuthData;

@RestController
public class AuthController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JWTAuthenticationFilter jwtAuthenticationFilter;
	@Autowired
	private AuthService authService;

	@PostMapping("/login")
	public String login(@RequestBody UserAuthData user) {
		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword()));
		} catch (BadCredentialsException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Please check your credentials");
		}

		final UserDetails userDetails = authService.loadUserByUsername(user.getName());
		Map<String, Object> claims = new HashMap<>();
		final String jwt = jwtAuthenticationFilter.createToken(claims, userDetails.getUsername());

		return jwt;
	}
}
