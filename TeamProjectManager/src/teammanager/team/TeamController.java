package teammanager.team;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import teammanager.schedule.ScheduleController;

// 팀 프로젝트 생성, 관리, 종료 담당 
public class TeamController {

    private Scanner scanner;
    private List<String> teams = new ArrayList<>(); // 팀 목록 저장용 리스트

    // scanner 초기화 및 예시 팀 추가
    public TeamController(Scanner scanner) {
        this.scanner = scanner;
       
        teams.add("AI Study");
        teams.add("Database Project");
        teams.add("수뭉이 팀플");
    }

    // 기존 팀 프로젝트 목록을 보여주고 선택할 수 있는 메뉴
    public void showTeamMenu() {
        boolean run = true;
        while (run) {
            System.out.println("=== 기존 팀플 관리창 ===");
            
            // 팀 목록 출력
            for (int i = 0; i < teams.size(); i++) {
                System.out.println((i + 1) + ". " + teams.get(i));
            }
            System.out.println("0. 나가기");
            System.out.print(">> ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                System.out.println("메인 메뉴로 돌아갑니다.\n");
                run = false;    
            } 
            // 선택한 팀 관리 메뉴
            else if (choice > 0 && choice <= teams.size()) {
                String selected = teams.get(choice - 1);
                ScheduleController schedule = new ScheduleController(scanner);
                schedule.showScheduleMenu(selected);
            } 
            else {
                System.out.println("잘못된 입력입니다.");
            }
        }
    }

    // 새로운 팀 프로젝트 생성 메뉴
    public void createTeamMenu() {
        System.out.println("=== 새로운 팀 프로젝트 생성 ===");
        System.out.print("프로젝트 이름: ");
        String name = scanner.nextLine();
        scanner.nextLine();
        
        System.out.print("팀 멤버: ");
        String member = scanner.nextLine();

        System.out.println("팀 프로젝트 [" + name + "] 가 생성되었습니다.");
        
        // 생성 직후 일정 관리 메뉴로 이동
        ScheduleController schedule = new ScheduleController(scanner);
        schedule.showScheduleMenu(name);
    }

    // 기존 팀 프로젝트 종료 메뉴
    public void endTeamMenu() {
        System.out.println("=== 종료할 팀 선택 ===");
        for (int i = 0; i < teams.size(); i++) {
            System.out.println((i + 1) + ". " + teams.get(i));
        }
        System.out.print(">> ");
        int num = scanner.nextInt();
        scanner.nextLine();

        if (num < 1 || num > teams.size()) {
            System.out.println("잘못된 입력입니다.");
            return;
        }

        String name = teams.get(num - 1);
        
        // 종료 요약 출력 (예시)
        System.out.println("=== [" + name + " 프로젝트 종료 요약] ===");
        System.out.println("팀원\t진행도\t지각\t미참여\t기타");
        System.out.println("---------------------------------");
        System.out.println("                       ");
        System.out.println("                       ");
        System.out.println("                       ");
        System.out.println("---------------------------------");
        System.out.println("팀 프로젝트가 종료되었습니다.");
        System.out.println("메인 메뉴로 돌아갑니다.\n");

        // 종료된 팀 삭제
        teams.remove(name);
    }
}
