package com.example.demo.constant;

import java.util.HashMap;

public enum TaskStatus {

	TO_DO(1), IN_PROGRESS(2), DONE(3);

	private int id;

	public int getId() {
		return this.id;
	}

	private TaskStatus(int id) {
		this.id = id;
	}

	private static HashMap<Integer, TaskStatus> hm = new HashMap<Integer, TaskStatus>();

	static {

		for (TaskStatus status : TaskStatus.values()) {
			hm.put(status.id, status);
		}

	}

	public static TaskStatus getStatus(Integer value) {
		return hm.get(value);
	}

}
