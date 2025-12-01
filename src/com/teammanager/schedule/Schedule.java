package com.teammanager.schedule;

public class Schedule {
	
	private String task;	//일정 내용
	private String deadline;	//일정 마감 기한
	
	public Schedule(String task, String deadline) {
		this.task = task;
		this.deadline = deadline;
	}
	
	//getter/setter
	public String getTask() {
		return task;
	}
	
	public void setTask(String task) {
		this.task = task;
	}
	
	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}	
}