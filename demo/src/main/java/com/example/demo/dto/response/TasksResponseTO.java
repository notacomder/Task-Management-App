package com.example.demo.dto.response;

import java.util.Date;

import com.example.demo.constant.TaskStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TasksResponseTO {

	@JsonProperty(value = "task_id")
	private Integer taskId;

	private String description;

	private TaskStatus status;

	private Date created;
	private Date modified;
	private Date due;
	private Integer priority;

	public TasksResponseTO() {
		super();
	}

	public TasksResponseTO(Integer taskId, String description, TaskStatus status, Date created,
			Date modified, Date due, Integer priority ) {
		super();
		this.taskId = taskId;
		this.description = description;
		this.status = status;
		this.created = created;
		this.modified = modified;
		this.due = due;
		this.priority = priority;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public Date getDue() {
		return due;
	}

	public void setDue(Date due) {
		this.due = due;
	}

	@Override
	public String toString() {
		return "TasksResponseTO [taskId=" + taskId + ", description=" + description + ", status=" + status + ", created=" + created + ", modified=" + modified + ", due=" + due + ", priority=" + priority
				+ "]";
	}

}
