package com.teammanager.progress;

public class Progress {
    private int totalTasks = 0;	// 전체 일정 수
    private int completedTasks = 0;	// 완료된 일정 수

    public Progress() {
        this.totalTasks = 0;	
        this.completedTasks = 0;	
    }
    
    //항목 증가 & 감소
    public void addTask() {
        totalTasks++;
    }

    public void removeTask() {   
        if (totalTasks > 0) {
            totalTasks--;
        }
    }
    
    public void completeTask() {	
        completedTasks++;
    }

    public void completeMyTask() {	 
        completeTask();
    }
    //getter/setter
    public int getTotalTasks() {	
        return totalTasks;
    }

    public int getCompletedTasks() {
        return completedTasks;
    }

    public void setTotalTasks(int totalTasks) {
		this.totalTasks = totalTasks;
	}

	public void setCompletedTasks(int completedTasks) {
		this.completedTasks = completedTasks;
	}

	//진행도
    public int getProgressPercent() {	
        if (totalTasks == 0) {
            return 0;
        }
        return (completedTasks * 100) / totalTasks;
    }
}
