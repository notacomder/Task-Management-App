package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.dto.request.UserRequestTO;
import com.example.demo.dto.response.UserResponseTO;
import com.example.demo.model.User;

public interface UsersService {

	public List<UserResponseTO> getAllUsers();

	public Optional<User> getUserById(Integer id);

	public UserResponseTO addUser(UserRequestTO request);

	public boolean deleteUser(Integer userId);

}