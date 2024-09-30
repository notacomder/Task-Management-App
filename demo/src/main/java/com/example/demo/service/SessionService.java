package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public interface SessionService {
	
	public String login(String username, String password) ;
	
	public Integer validateSession(String sessionToken);
	
}
