package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Session;

public interface SessionRepository extends JpaRepository<Session, Integer> {

	@Query(value = "SELECT * FROM session WHERE token = :sessionToken",nativeQuery = true)
	Session findByToken(@Param("sessionToken") String sessionToken);
	
}
