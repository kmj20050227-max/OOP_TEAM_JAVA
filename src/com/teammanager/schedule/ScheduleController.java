// 일정 메인 메뉴
package com.teammanager.schedule;

import java.util.Scanner;
import com.teammanager.progress.Progress;
import com.teammanager.team.Team;
import com.teammanager.evaluation.Evaluation;

public class ScheduleController {

    private ScheduleService service = new ScheduleService();

    public void open(Team team) {
    	
        Scanner scanner = new Scanner(System.in);
        boolean run = true;

        Progress progress = team.getProgress();	//팀 진행도
        Evaluation evaluation = team.getEvaluation();	//팀 평가 스코어
        String teamName = team.getTeamName();	//팀 이름

        Task[] mainTasks = new Task[50];	//주요 일정
        Task[] myTasks = new Task[50];	//개인 일정
        int mainCount = 0, myCount = 0;	

        while (run) {
            System.out.println("===========================");
            System.out.println("\t" + teamName + "팀플 관리");
            System.out.println("===========================");
            System.out.println("메뉴를 선택하세요:");
            System.out.println("1. 주요 일정");
            System.out.println("2. 내 역할(내 일정)");
            System.out.println("3. 진행도");
            System.out.println("0. 뒤로가기");
            System.out.print(">> ");

            String choice = scanner.nextLine();

            //주요 일정 메뉴
            if (choice.equals("1")) {
                boolean taskRun = true;
                
                while (taskRun) {  
                    service.printAllTasks(mainTasks, mainCount, myTasks, myCount);

                    System.out.println("====== 주요 일정 ======");
                    System.out.println("메뉴를 선택하세요:");
                    System.out.println("1. 생성");
                    System.out.println("2. 체크");
                    System.out.println("3. 삭제");
                    System.out.println("0. 뒤로가기");
                    System.out.print(">> ");
                    
                    String subChoice = scanner.nextLine();
                    
                    //일정 생성
                    if (subChoice.equals("1")) { 
                        System.out.println("==== 주요 일정 생성 ====");
                        System.out.print("일정 이름: ");
                        String name = scanner.nextLine();
                      
                        System.out.print("마감 시간 (예: 2025-11-10 18:00): ");
                        String deadline = scanner.nextLine();

                        mainTasks[mainCount] = new Task(name, deadline);
                        mainCount++;
                        progress.addTask();	// 진행도 total 증가

                        System.out.println("\n주요 일정이 추가되었습니다.");
                    } 
                    //일정 체크
                    else if (subChoice.equals("2")) { // 체크
                        if (mainCount == 0) {
                        	System.out.println("\n등록된 일정이 없습니다."); 
                        	continue;
                        }

                        for (int i = 0; i < mainCount; i++) {
                        	System.out.println((i + 1) + ". "+mainTasks[i].getName());
                        }
                        System.out.print(">> ");
                        int index = Integer.parseInt(scanner.nextLine()) - 1;

                        System.out.print("제출 시간 (예: 2025-11-10 18:00): ");
                        String submitTime = scanner.nextLine();

                        boolean onTime = service.checkSchedule(mainTasks[index], submitTime, progress, evaluation);
                        
                        //지각 시 사유 선택
                        if (!onTime) {
                            System.out.println("\n마감 시간을 지키지 못했습니다.");
                            System.out.println("사유를 선택하세요:");
                            System.out.println("1. 개인사유");
                            System.out.println("2. 팀플 일정 변경");
                            System.out.println("3. 먼저 양해 구함");
                            System.out.print(">> ");
                            String reason = scanner.nextLine();
                            service.applyLateReason(mainTasks[index], evaluation, reason);
                        } 
                        else {
                            System.out.println("\n제시간에 제출했습니다.");
                        }
                    } 
                    //일정 삭제
                    else if (subChoice.equals("3")) { 
                        if (mainCount == 0) { 
                        	System.out.println("\n등록된 일정이 없습니다.");
                        	continue;
                        }

                        for (int i = 0; i < mainCount; i++) {
                        	System.out.println((i + 1) + ". "+ mainTasks[i].getName());
                        }
                        System.out.print(">> ");
                        int index = Integer.parseInt(scanner.nextLine())-1;

                        mainCount = service.deleteSchedule(mainTasks, mainCount, index);
                        progress.removeTask(); //진행도 total 감소
                        System.out.println("\n일정이 삭제되었습니다.");
                    }
                    else if (subChoice.equals("0")) {
                    	taskRun = false; 
                    }
                }
            }

            // 내 일정 메뉴
            else if (choice.equals("2")) {
                boolean myRun = true;
                
                while (myRun) {
                    service.printAllTasks(mainTasks, mainCount, myTasks, myCount);

                    System.out.println("======= 내 일정 =======");
                    System.out.println("1. 생성");
                    System.out.println("2. 체크");
                    System.out.println("3. 삭제");
                    System.out.println("0. 뒤로가기");
                    System.out.print(">> ");
                    
                    String subChoice = scanner.nextLine();
                    
                    //내 일정 생성
                    if (subChoice.equals("1")) {
                        System.out.println("==== 내 일정 생성 ====");
                        System.out.print("일정 이름: ");
                        String name = scanner.nextLine();
                        System.out.print("마감 시간 (예: 2025-11-10 18:00): ");
                        String deadline = scanner.nextLine();

                        myTasks[myCount] = new Task(name, deadline);
                        myCount++;
                        progress.addTask();
                        System.out.println("\n내 일정이 추가되었습니다.");
                    } 
                    //내 일정 체크
                    else if (subChoice.equals("2")) {
                        if (myCount == 0) { 
                        	System.out.println("\n등록된 일정이 없습니다."); 
                        	continue;
                        }

                        for (int i = 0; i < myCount; i++) {
                        	System.out.println((i + 1) + ". "+ myTasks[i].getName());
                        }
                        System.out.print(">> ");
                        int index = Integer.parseInt(scanner.nextLine())-1;

                        System.out.print("제출 시간 (예: 2025-11-10 18:00): ");
                        String submitTime = scanner.nextLine();

                        boolean onTime = service.checkSchedule(myTasks[index], submitTime, progress, evaluation);

                        if (!onTime) {
                            System.out.println("\n마감 시간을 지키지 못했습니다.");
                            System.out.println("사유를 선택하세요:");
                            System.out.println("1. 개인사유");
                            System.out.println("2. 팀플 일정 변경");
                            System.out.println("3. 먼저 양해 구함");
                            System.out.print(">> ");
                            String reason = scanner.nextLine();
                            service.applyLateReason(myTasks[index], evaluation, reason);
                        } else {
                            System.out.println("\n제시간에 제출했습니다.");
                        }
                    } 
                    //내 일정 삭제
                    else if (subChoice.equals("3")) {
                        if (myCount == 0) { 
                        	System.out.println("\n등록된 일정이 없습니다."); 
                        	continue;
                        }

                        for (int i = 0; i < myCount; i++) {
                        	System.out.println((i+1)+". "+myTasks[i].getName());
                        }
                        
                        System.out.print(">> ");
                        int index = Integer.parseInt(scanner.nextLine())-1;

                        myCount = service.deleteSchedule(myTasks, myCount, index);
                        progress.removeTask();
                        System.out.println("\n일정이 삭제되었습니다.");
                    } 
                    else if (subChoice.equals("0")) { myRun = false; }
                }
            }

            //진행도
            else if (choice.equals("3")) {
                System.out.println("==== 진행도 ====");
                int progressPercent = progress.getProgressPercent(); //퍼센트 계산
                System.out.println("현재 진행도: " + progressPercent + "%\n");
            }

            //뒤로가기
            else if (choice.equals("0")) { 
            	run = false; 
            }
        }
    }
}