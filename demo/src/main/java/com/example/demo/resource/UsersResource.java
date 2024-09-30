package com.example.demo.resource;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.response.UserResponseTO;
import com.example.demo.service.SessionService;
import com.example.demo.service.UsersService;

@Controller
@RequestMapping("/users")
public class UsersResource {

	private final UsersService usersService;

	private final SessionService sessionService;
	
	public UsersResource(UsersService usersService, SessionService sessionService) {
		super();
		this.usersService = usersService;
		this.sessionService = sessionService;
	}

	@GetMapping
	public ResponseEntity<List<UserResponseTO>> getAllUsers() {

		return new ResponseEntity<>(usersService.getAllUsers(), HttpStatus.OK);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Void> deleteUser(@RequestHeader("session_token") String token) {
		Integer userId = sessionService.validateSession(token);

		boolean isDeleted = usersService.deleteUser(userId);

		if (isDeleted) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
