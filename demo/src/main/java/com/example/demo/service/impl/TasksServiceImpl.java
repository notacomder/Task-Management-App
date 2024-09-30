package com.example.demo.service.impl;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.constant.TaskStatus;
import com.example.demo.dto.request.TaskRequestTO;
import com.example.demo.dto.request.TaskUpdateRequestTO;
import com.example.demo.dto.response.TasksResponseTO;
import com.example.demo.model.Task;
import com.example.demo.repository.TasksRepository;
import com.example.demo.service.TasksService;


@Service
public class TasksServiceImpl implements TasksService {

	@Autowired
	private TasksRepository tasksRepository;

	@Override
	public List<TasksResponseTO> getAllTasks() {
		List<Task> tasks = tasksRepository.findAll();
		List<TasksResponseTO> response = new ArrayList<>();

		for (Task task : tasks) {
			TasksResponseTO responseTO = new TasksResponseTO();
			responseTO.setTaskId(task.getTaskId());
			responseTO.setStatus(TaskStatus.getStatus(task.getStatusId()));
			responseTO.setDescription(task.getDescription());
			responseTO.setCreated(task.getCreated());
			responseTO.setModified(task.getModified());
			responseTO.setDue(task.getDue());

			response.add(responseTO);
		}
		return response;

	}

	@Override
	public TasksResponseTO getTaskById(Integer taskId, Integer userId) throws ParseException {
		Object[] taskCheck = null;
		System.out.println("getTaskById  1");
		taskCheck = (Object[]) tasksRepository.findTaskAndUsernameByTaskIdAndUserId(taskId, userId);
		System.out.println("getTaskById  2 " + taskCheck);

		if (taskCheck == null) {
			System.out.println("IllegalArgumentException");

			throw new IllegalArgumentException("User with ID " + userId + "and" + taskId + " does not exist");
		}


		String DATE_FORMAT = "dd-MM-yyyy";
		final SimpleDateFormat dateTimeFormat = new SimpleDateFormat(DATE_FORMAT);

		Task task = new Task();
		task.setTaskId((Integer) taskCheck[0]);
		task.setDescription((String) taskCheck[2]);

		task.setCreated(dateTimeFormat.parse(String.valueOf(taskCheck[5])));

		task.setDue(dateTimeFormat.parse(String.valueOf(taskCheck[7])));
		task.setStatusId((Integer) taskCheck[3]);
		task.setPriority((Integer) taskCheck[4]);


		TasksResponseTO response = new TasksResponseTO();

		response.setTaskId(task.getTaskId());

		response.setCreated(task.getCreated());

		response.setStatus(TaskStatus.getStatus(task.getStatusId()));

		response.setDue(task.getDue());

		response.setModified(new Date(System.currentTimeMillis()));

		response.setPriority(task.getPriority());

		response.setDescription(task.getDescription());

		System.out.println("response " + response);

		return response;

	}

	@Override
	public boolean deleteTask(Integer taskId, Integer userId) {

		Task task = tasksRepository.findByTaskIdAndUserId(taskId, userId);
		if (task != null) {
			tasksRepository.deleteById(taskId);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void updateTaskDetails(Integer userId, Integer taskId, TaskUpdateRequestTO updatedTaskDetails) {

		Task task = tasksRepository.findByTaskIdAndUserId(taskId, userId);

		if (task == null)

		{
			throw new NullPointerException();
		}

		if (updatedTaskDetails.getStatus() != null) {
			task.setStatusId(updatedTaskDetails.getStatus().getId());
		}

		if (updatedTaskDetails.getDue() != null) {
			task.setDue(updatedTaskDetails.getDue());
		}

		task.setModified(new Date(System.currentTimeMillis()));

		if (updatedTaskDetails.getPriority() != null) {
			task.setPriority(updatedTaskDetails.getPriority());
		}

		if (updatedTaskDetails.getDescription() != null && !updatedTaskDetails.getDescription().isEmpty()) {
			task.setDescription(updatedTaskDetails.getDescription());
		}

		tasksRepository.save(task);
	}

	@Override
	public void createTask(TaskRequestTO request, Integer userId) throws Exception {
		Task task = new Task();

		String checkDesc = request.getDescription();
		Integer check = tasksRepository.checkForDescandUserId(userId, checkDesc);
		if (check > 1) {
			throw new Exception("Duplicate Entry");
		}

		if (request.getDescription() != null && !request.getDescription().isEmpty()) {
			task.setDescription(request.getDescription());
		} else {
			throw new Exception("Description cannot be null or empty");
		}

		if (request.getDue() != null) {
			task.setDue(request.getDue());
		} else {
			throw new IllegalArgumentException("Due date cannot be null");
		}

		if (request.getPriority() != null) {
			task.setPriority(request.getPriority());
		} else {
			throw new IllegalArgumentException("Priority cannot be null");
		}

		task.setUserId(userId);

		task.setStatusId(TaskStatus.TO_DO.getId());

		task.setModified(new Date(System.currentTimeMillis()));
		task.setCreated(new Date(System.currentTimeMillis()));

		tasksRepository.save(task);
	}

	@Override
	public List<TasksResponseTO> getTasksByUserId(Integer userId) {
		List<Task> tasks = tasksRepository.findAllByuserId(userId);
		List<TasksResponseTO> response = new ArrayList<>();

		for (Task task : tasks) {
			TasksResponseTO responseTO = new TasksResponseTO();
			responseTO.setTaskId(task.getTaskId());
			responseTO.setDescription(task.getDescription());
			responseTO.setStatus(TaskStatus.getStatus(task.getStatusId()));
			responseTO.setCreated(task.getCreated());
			responseTO.setModified(task.getModified());
			responseTO.setDue(task.getDue());
			response.add(responseTO);
		}
		return response;
	}

}
