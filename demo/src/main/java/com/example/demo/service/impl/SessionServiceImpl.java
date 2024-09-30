package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.model.Session;
import com.example.demo.repository.SessionRepository;
import com.example.demo.repository.UsersRepository;
import com.example.demo.service.SessionService;

@Service
public class SessionServiceImpl implements SessionService {

	@Autowired
	public SessionRepository sessionRepository;

	@Autowired
	private UsersRepository userRepository;

	@Override
	public String login(String username, String password) {
		User user = userRepository.findByUsername(username);
		System.out.println(user);
		if (user != null && password.equals(user.getPassword())) {
			String sessionToken = UUID.randomUUID().toString();

			LocalDateTime expirationTime = LocalDateTime.now().plusHours(24);
			Date expirationDate = (Date) Date.from(expirationTime.atZone(ZoneId.systemDefault()).toInstant());

			Session sessions = new Session();
			sessions.setUserId(user.getUserId());
			sessions.setToken(sessionToken);
			sessions.setExpiresAt(expirationDate);
			sessions.setCreatedAt(new Date());
			sessionRepository.save(sessions);

			return sessionToken;
		} else {
			return null;
		}
	}

	@Override
	public Integer validateSession(String sessionToken) {
		Session session = sessionRepository.findByToken(sessionToken);

		if (session != null && session.getExpiresAt().after(new Date())) {
			return session.getUserId();
		} else {
			return null;
		}
	}

}
