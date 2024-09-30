package com.example.demo.dto.request;

import jakarta.validation.constraints.NotBlank;

public class UserRequestTO {

	@NotBlank
	private String username;
	@NotBlank
	private String password;
	@NotBlank
	private String role;

	public UserRequestTO() {
		super();
	}

	public UserRequestTO(@NotBlank String username, @NotBlank String password, @NotBlank String role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserRequest [username=" + username + ", password=" + password + ", role=" + role + "]";
	}

}
