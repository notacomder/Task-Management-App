package com.example.demo.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.request.TaskRequestTO;
import com.example.demo.dto.request.TaskUpdateRequestTO;
import com.example.demo.dto.response.TasksResponseTO;
import com.example.demo.service.SessionService;
import com.example.demo.service.TasksService;

@Controller
@RequestMapping("/tasks")
public class TasksResource {

	private final TasksService tasksService;

	private final SessionService sessionService;

	@Autowired
	public TasksResource(TasksService tasksService, SessionService sessionService) {
		super();
		this.tasksService = tasksService;
		this.sessionService = sessionService;
	}

	@GetMapping
	public ResponseEntity<List<TasksResponseTO>> getAllTasks(@RequestHeader("session_token") String token) {

		if (sessionService.validateSession(token) == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}

		List<TasksResponseTO> response = tasksService.getAllTasks();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<Void> createTask(@RequestBody TaskRequestTO request,
			@RequestHeader("session_token") String token) throws Exception {

		Integer userId = sessionService.validateSession(token);

		if (userId == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		try {
			tasksService.createTask(request, userId);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);

		}

	}

	@DeleteMapping("/delete/{task_id}")
	public ResponseEntity<Void> deleteTask(@PathVariable("task_id") Integer taskId,
			@RequestHeader("session_token") String token) {

		Integer userId = sessionService.validateSession(token);

		if (userId == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		boolean isDeleted = tasksService.deleteTask(taskId, userId);

		if (isDeleted) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<TasksResponseTO> getTaskById(@PathVariable("id") Integer taskId,
			@RequestHeader("session_token") String token) {

		Integer userId = sessionService.validateSession(token);

		if (userId == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}

		try {
			TasksResponseTO task = tasksService.getTaskById(taskId, userId);

			if (task != null) {
				return new ResponseEntity<>(task, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}

		catch (IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Void> updateTask(@PathVariable("id") Integer taskId,
			@RequestBody TaskUpdateRequestTO updatedTaskDetails, @RequestHeader("session_token") String token) {

		Integer userId = sessionService.validateSession(token);

		if (userId == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		try {
			tasksService.updateTaskDetails(userId, taskId, updatedTaskDetails);
			return new ResponseEntity<>(HttpStatus.OK);
		}

		catch (NullPointerException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/user")
	public ResponseEntity<List<TasksResponseTO>> getTasksByUserId(@RequestHeader("session_token") String token) {

		Integer userId = sessionService.validateSession(token);

		if (userId == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}

		List<TasksResponseTO> response = tasksService.getTasksByUserId(userId);
		if (response.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
