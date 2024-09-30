package com.example.demo.resource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.request.UserRequestTO;
import com.example.demo.dto.response.UserResponseTO;
import com.example.demo.service.SessionService;
import com.example.demo.service.UsersService;

@Controller
@RequestMapping("/registry")
public class LoginResource {

	
	private final SessionService sessionService;

	private final UsersService usersService;
	

	public LoginResource(SessionService sessionService, UsersService usersService) {
		super();
		this.sessionService = sessionService;
		this.usersService = usersService;
	}

	@PostMapping
	public ResponseEntity<UserResponseTO> createUser(@RequestBody UserRequestTO request) {
		UserResponseTO createdUser = usersService.addUser(request);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<String> loginToken(@RequestHeader("username") String username,
			@RequestHeader("password") String password) {
		try {
			String token = sessionService.login(username, password);

			if (token == null) {
				throw new IllegalAccessException("Invalid credentials");
			}

			HttpHeaders headers = new HttpHeaders();
			headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + token);

			return new ResponseEntity<>("Login successful", headers, HttpStatus.OK);
		} catch (IllegalAccessException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}

}
