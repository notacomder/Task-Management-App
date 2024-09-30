package com.example.demo.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.request.TaskRequestTO;
import com.example.demo.dto.request.TaskUpdateRequestTO;
import com.example.demo.dto.response.TasksResponseTO;

@Service
public interface TasksService {

	public List<TasksResponseTO> getAllTasks();

	public TasksResponseTO getTaskById(Integer taskId, Integer userId) throws ParseException;

	public boolean deleteTask(Integer taskId, Integer userId);

	public void updateTaskDetails(Integer userId, Integer taskId, TaskUpdateRequestTO updatedTaskDetails);

	public void createTask(TaskRequestTO request, Integer userId) throws Exception;

	public List<TasksResponseTO> getTasksByUserId(Integer userId);

}
