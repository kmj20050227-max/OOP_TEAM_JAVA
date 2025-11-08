package teammanager.main;

import java.util.Scanner;
import teammanager.team.TeamController;

public class MainMenuController {

    private Scanner scanner;

    public MainMenuController(Scanner scanner) {
        this.scanner = scanner;
    }

    // 메인 메뉴 화면 출력 및 메뉴 선택 처리
    public void showMainMenu() {
        TeamController teamController = new TeamController(scanner);
        boolean run = true;

        while (run) {
            System.out.println();
            System.out.println("============================");
            System.out.println("     상명대 전용 팀플 기록기     ");
            System.out.println("============================");
            System.out.println("메뉴를 선택하세요:");
            System.out.println("1. 기존 팀플 관리창 수정");
            System.out.println("2. 새로운 팀플 관리창 생성");
            System.out.println("3. 기존 팀플 종료");
            System.out.println("0. 로그아웃");
            System.out.print(">> ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                	// 기존 팀플 관리 메뉴로 이동
                    teamController.showTeamMenu();
                    break;
                    
                case 2:
                	// 새로운 팀플 생성 메뉴로 이동
                    teamController.createTeamMenu();
                    break;
                    
                case 3:
                	// 팀 프로젝트 종료 메뉴 실행
                    teamController.endTeamMenu();
                    break;
                    
                case 0:
                	// 로그아웃 처리
                    System.out.println("로그아웃 되었습니다");
                    run = false;
                    break;
                    
                default:
                	// 잘못된 입력 처리
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }
}

