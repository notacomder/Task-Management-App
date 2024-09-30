package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.User;

public interface UsersRepository extends JpaRepository<User, Integer> {

	List<User> findAll();

	Optional<User> findById(Integer id);

	void deleteById(Integer id);

	@Query(value = "SELECT * FROM user u WHERE u.username = :username", nativeQuery = true)
	User findByUsername(@Param("username") String username);
		
}
