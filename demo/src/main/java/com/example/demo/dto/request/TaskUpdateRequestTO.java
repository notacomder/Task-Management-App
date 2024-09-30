package com.example.demo.dto.request;

import java.sql.Date;

import com.example.demo.constant.TaskStatus;

public class TaskUpdateRequestTO extends TaskRequestTO {

	private TaskStatus status;

	public TaskUpdateRequestTO() {
		super();
	}

	public TaskUpdateRequestTO(String description, Date due, Integer priority, TaskStatus status) {
		super(description, due, priority);
		this.status = status;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "TaskRequestExtended [status=" + status + ", description=" + getDescription() + ", due=" + getDue()
				+ ", priority=" + getPriority() + "]";
	}
}
