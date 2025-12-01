//메인 메뉴 화면
package com.teammanager.main;

import java.util.Scanner;

import com.teammanager.evaluation.Evaluation;
import com.teammanager.progress.Progress;
import com.teammanager.schedule.ScheduleController;
import com.teammanager.team.Team;
import com.teammanager.team.TeamService;

public class MainMenuController {
	
    private TeamService teamService = new TeamService();

    public void mainMenu() {

        Scanner scanner = new Scanner(System.in);
        boolean run = true;

        while (run) {
        	//메인 메뉴 화면
            System.out.println("================================");
            System.out.println("        상명대 전용 팀플 기록기       ");
            System.out.println("================================");
            System.out.println("메뉴를 선택하세요:");
            System.out.println("1. 기존 팀플 관리창 수정");
            System.out.println("2. 새로운 팀플 관리창 생성");
            System.out.println("3. 기존 팀플 종료");
            System.out.println("0. 로그아웃"); 
            System.out.print(">> ");

            String choice = scanner.nextLine();

            // 기존 팀플 관리
            if (choice.equals("1")) {

                if (teamService.getCount() == 0) {
                    System.out.println("\n현재 생성된 팀이 없습니다.\n");
                    continue;
                }
                
                // 팀 목록 출력
                System.out.println("\n팀플을 선택해주세요:");
                Team[] teams = teamService.getTeams();
                int teamCount = teamService.getCount();
                
                for (int i = 0; i < teamCount; i++) {
                	System.out.println((i + 1) + ". " + teams[i].getTeamName());
                }
                System.out.println("0. 나가기");
                System.out.print(">> ");
                String select = scanner.nextLine();
               
                if (select.equals("0")) {
                    continue;
                }
                
                // 일정 관리 메뉴 이동
                int index = Integer.parseInt(select) - 1;
                Team selected = teams[index];
                
                System.out.println("\n선택된 팀: " + selected.getTeamName());
                
                
                ScheduleController schedule = new ScheduleController();
                schedule.open(selected);
            }

            // 새로운 팀플 생성
            else if (choice.equals("2")) {
                System.out.println("=== 새로운 팀 프로젝트 생성 ===");

                System.out.print("프로젝트 이름: ");
                String name = scanner.nextLine();
                
                
                Team team = new Team(name);	
                teamService.addTeam(team);		
                
                // 팀원 추가
                System.out.println("팀원 ID를 입력하세요 (완료 시 0 입력): ");
                while (true) {
                    System.out.print(">> ");
                    String userId = scanner.nextLine();

                    if (userId.equals("0")) {
                    	break;
                    }
                    boolean added = team.addMember(userId);

                    if (added) {
                        System.out.println("팀원 등록 완료!");
                    } 
                    
                    else {
                        System.out.println("팀원이 너무 많습니다.");
                        break;
                    }
                }
                
                System.out.println("팀 프로젝트가 생성되었습니다!\n");
                
                ScheduleController schedule = new ScheduleController();
                schedule.open(team);
            }

            // 팀플 종료
            else if (choice.equals("3")) {

                if (teamService.getCount() == 0) {
                    System.out.println("\n현재 생성된 팀이 없습니다.\n");
                    continue;
                }

                Team[] teams = teamService.getTeams();
                int teamCount = teamService.getCount();
                System.out.println("종료할 팀을 선택하세요: ");
              
                
                for (int i = 0; i < teamCount; i++) {
                	System.out.println((i + 1) + ". " + teams[i].getTeamName());
                }
                System.out.println("0. 나가기");
                System.out.print(">> ");

                String select = scanner.nextLine();
                if (select.equals("0")) {
                	continue;
                }
                
                
                int index = Integer.parseInt(select) - 1;
                Team team = teams[index];

                //진행도 & 평가 정보 불러오기
                Progress progress = team.getProgress();
                int progressPercent = 0;
                if (progress != null) {
                	progressPercent = progress.getProgressPercent();
                }

                Evaluation evaluation = team.getEvaluation();
                if (evaluation == null) {
                	evaluation = new Evaluation();
                }
                
                //팀플 종료 리포트 출력
                System.out.println("==========================");
                System.out.println("       팀플 종료 리포트");
                System.out.println("==========================");
                System.out.println("팀 이름 : " + team.getTeamName());
                System.out.println("진행도: " + progressPercent + "%");
                System.out.println("---------------------------");
                System.out.println("      성취도 / 평가도");
                System.out.println("---------------------------");
                System.out.println("항목                     횟수");
                System.out.println("지각(개인 사유)		" + evaluation.getLatePersonal());
                System.out.println("지각(팀플 일정 변경)		" + evaluation.getLateScheduleChange());
                System.out.println("지각(먼저 양해 구함)		" + evaluation.getLatePreNotice());
                System.out.println("미참여           		" + evaluation.getAbsent());
                System.out.println("기타            		" + evaluation.getEtc());
                System.out.println("---------------------------\n");

                System.out.println("리포트 출력이 완료되었습니다.");
                System.out.println("팀플이 종료되었습니다.\n");
            }

            //로그아웃
            else if (choice.equals("0")) {
                System.out.println("\n로그아웃 되었습니다.");
                run = false;
            }
        }
    }
}
