package com.teammanager.schedule;

public class Task {
    private String name;	// 일정 이름
    private String deadline;	//마감 시간
    private String submitTime;	//제출 시간
    private boolean completed;	//완료 여부
    private String lateReason;	//지각 사유

    //생성자
    public Task(String name, String deadline) {
        this.name = name;
        this.deadline = deadline;
        this.completed = false;
        this.submitTime = "-";	
        this.lateReason = "";
    }
    //getter/setter
    public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDeadline() {
		return deadline;
	}


	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}


	public String getSubmitTime() {
		return submitTime;
	}


	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}


	public boolean isCompleted() {
		return completed;
	}


	public void setCompleted(boolean completed) {
		this.completed = completed;
	}


	public String getLateReason() {
		return lateReason;
	}


	public void setLateReason(String lateReason) {
		this.lateReason = lateReason;
	}

	// 일정 완료 처리
	public void checkTask(String submitTime, String lateReason) {
        this.completed = true;
        this.submitTime = submitTime;
        this.lateReason = lateReason;
    }
}