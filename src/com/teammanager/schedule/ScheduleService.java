package com.teammanager.schedule;

import com.teammanager.progress.Progress;
import com.teammanager.evaluation.Evaluation;

public class ScheduleService {

    // 일정 체크 및 진행도 갱신
    public boolean checkSchedule(Task task, String submitTime, Progress progress, Evaluation evaluation) {
    	boolean onTime = submitTime.compareTo(task.getDeadline()) <= 0; // 제시간 제출 여부
    	task.checkTask(submitTime, ""); // 제출 처리
    	progress.completeTask(); // 진행도 증가
    	return onTime;
    }

    // 지각 사유 적용
    public void applyLateReason(Task task, Evaluation evaluation, String reasonCode) {
        String reasonText = "";
        if (reasonCode.equals("1")) { 
        	evaluation.addLatePersonal();
        	reasonText = "개인사유";
        }
       
        else if (reasonCode.equals("2")) { 
        	evaluation.addLateScheduleChange(); 
        	reasonText = "팀플 일정 변경";
        }
        
        else if (reasonCode.equals("3")) { 
        	evaluation.addLatePreNotice(); 
        	reasonText = "먼저 양해 구함";
        }
        
        else
        { 
        	evaluation.addEtc();
        	reasonText = "";
        }

        task.checkTask(task.getSubmitTime(), reasonText); // 지각 사유 반영
    }

    // 일정 삭제
    public int deleteSchedule(Task[] tasks, int count, int index) {
    	if (count <= 0 || index < 0 || index >= count) {
    		return count;
    	}
    	
    	for (int i = index; i < count - 1; i++) {
    		tasks[i] = tasks[i + 1]; 
    	}
    	tasks[count - 1] = null;
    	return count - 1;
    }

    // 일정 출력
    public void printTasks(Task[] tasks, int count, String title) {
        if (count == 0) {
        	return;
        }

        System.out.println("-------------------------------");
        System.out.println(title);
        System.out.println("-------------------------------");
        
        for (int i = 0; i < count; i++) {
            Task task = tasks[i];
            
            if (task == null) {
            	continue;
            }

            System.out.println((i + 1) + ". " + task.getName() + " | 마감시간 : " + task.getDeadline());
            
            if (task.isCompleted()) {
                if (!task.getLateReason().isEmpty()) {
                    System.out.println("제출시간 : " + task.getSubmitTime() + " | 지각 사유: " + task.getLateReason() + " | [완료]");
                } 
                
                else {
                    System.out.println("제출시간 : " + task.getSubmitTime() + " | [완료]");
                }
            } 
            
            else {
                System.out.println("제출시간 : - [미완료]");
            }
        }
    }

    // 주요 일정 & 개인 일정 출력
    public void printAllTasks(Task[] mainTasks, int mainCount, Task[] myTasks, int myCount) {
        if (mainCount > 0) {
        	printTasks(mainTasks, mainCount, "주요 일정");
        }
        
        if (myCount > 0) {
        	printTasks(myTasks, myCount, "내 역할");
        }
    }
}