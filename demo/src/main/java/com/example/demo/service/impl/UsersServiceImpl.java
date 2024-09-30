package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.request.UserRequestTO;
import com.example.demo.dto.response.UserResponseTO;
import com.example.demo.model.User;
import com.example.demo.repository.UsersRepository;
import com.example.demo.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersRepository usersRepository;

	@Override
	public List<UserResponseTO> getAllUsers() {
		List<User> user = usersRepository.findAll();
		List<UserResponseTO> response = new ArrayList<>();

		for (User users : user) {
			UserResponseTO responseTO = new UserResponseTO();
			responseTO.setPassword(users.getPassword());
			responseTO.setRole(users.getRole());
			responseTO.setUsername(users.getUsername());

			response.add(responseTO);
		}
		return response;
	}

	@Override
	public Optional<User> getUserById(Integer id) {
		return usersRepository.findById(id);
	}

	@Override
	public UserResponseTO addUser(UserRequestTO request) {
		User user = new User();
		UserResponseTO response = new UserResponseTO();

		user.setPassword(request.getPassword());
		response.setPassword(request.getPassword());

		user.setRole(request.getRole());
		response.setRole(request.getRole());

		user.setUsername(request.getUsername());
		response.setUsername(request.getUsername());

		usersRepository.save(user);

		return response;
	}

	@Override
	public boolean deleteUser(Integer userId) {
		if (usersRepository.existsById(userId)) {
			usersRepository.deleteById(userId);
			return true;
		} else {
			return false;
		}
	}

}
