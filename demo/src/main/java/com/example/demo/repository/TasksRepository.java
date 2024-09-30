package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Task;

public interface TasksRepository extends JpaRepository<Task, Integer> {

	List<Task> findAll();

	Optional<Task> findById(Integer id);

	void deleteById(Integer id);

	List<Task> findAllByuserId(Integer userId);

	Task findByTaskIdAndUserId(Integer taskId, Integer userId);

	@Query(value = "SELECT t.*, u.username FROM task t " + "JOIN user u ON t.user_id = u.user_id "
			+ "WHERE t.task_id = :taskId AND t.user_id = :userId", nativeQuery = true)
	Object findTaskAndUsernameByTaskIdAndUserId(@Param("taskId") Integer taskId, @Param("userId") Integer userId);

	@Query(value = "SELECT COUNT(*) FROM task t WHERE t.user_id = :userId AND t.description = :checkDesc",nativeQuery = true)
	Integer checkForDescandUserId(@Param("userId")Integer userId, @Param("checkDesc")String checkDesc);
}
