package com.example.demo.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "session")
public class Session {
	
	@Id
	@Column(name = "user_id")
	private Integer userId;
	
	private String token;
	
	@Column(name = "created_at")
	private Date createdAt;
	
	@Column(name = "expires_at")
	private Date expiresAt;

	public Session() {
		super();
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(Date expiresAt) {
		this.expiresAt = expiresAt;
	}

	@Override
	public String toString() {
		return "session [userId=" + userId + ", token=" + token + ", createdAt=" + createdAt + ", expiresAt="
				+ expiresAt + "]";
	}
	
	
	
}
