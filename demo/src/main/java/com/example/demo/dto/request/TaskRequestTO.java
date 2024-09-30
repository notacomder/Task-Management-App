
package com.example.demo.dto.request;

import java.sql.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TaskRequestTO {

	@NotBlank
	private String description;

	@NotNull
	private Date due;

	@NotNull
	private Integer priority;

	public TaskRequestTO() {
		super();
	}

	public TaskRequestTO(String description, Date due, Integer priority) {
		super();
		this.description = description;
		this.due = due;
		this.priority = priority;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDue() {
		return due;
	}

	public void setDue(Date due) {
		this.due = due;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	@Override
	public String toString() {
		return "TaskRequest [description=" + description + ", due=" + due + ", priority=" + priority + "]";
	}
}
