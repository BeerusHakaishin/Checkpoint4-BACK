package com.argonaute.checkpoint.api.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.argonaute.checkpoint.api.dto.UserDto;
import com.argonaute.checkpoint.api.jwt.JwtUtils;
import com.argonaute.checkpoint.api.model.BlackList;
import com.argonaute.checkpoint.api.model.User;
import com.argonaute.checkpoint.api.payload.request.LoginRequest;
import com.argonaute.checkpoint.api.payload.response.JwtResponse;
import com.argonaute.checkpoint.api.repository.BlackListRepository;
import com.argonaute.checkpoint.api.repository.RoleRepository;
import com.argonaute.checkpoint.api.repository.UsersRepository;
import com.argonaute.checkpoint.api.service.UserDetailsImpl;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UsersRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	BlackListRepository blackListRepository;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), roles));
	}

	@PostMapping("/signup")
	public User createUser(@Valid @RequestBody UserDto userDto) {
		User user = new User();
		user.setUsername(userDto.getUsername());
		user.setRoles(userDto.getRoles());
		return userRepository.save(user);
	}

	@GetMapping("/istokenexpired")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public boolean isTokenExpired() {
		return true;
	}

	@GetMapping("/logout")
	public void logOut(HttpServletRequest request) {
		String headerAuth = request.getHeader("Authorization");
		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
			String jwt = headerAuth.substring(7, headerAuth.length());
			BlackList blackList = new BlackList();
			blackList.setToken(jwt);
			blackList.setDate(new Date());
			blackListRepository.save(blackList);
		}
	}
}
