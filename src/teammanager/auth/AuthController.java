package teammanager.auth;

import java.util.Scanner;
import teammanager.main.MainMenuController;

public class AuthController {
	private Scanner scanner;
	
	public AuthController(Scanner scanner) {
		this.scanner = scanner;
	}
    // 로그인 / 회원가입 메뉴 시작
    public void start() {
        while (true) {
            System.out.println();
            System.out.println("메뉴를 선택하세요:");
            System.out.println("1. 로그인");
            System.out.println("2. 회원가입");
            System.out.println("3. 프로그램 종료");
            System.out.print(">> ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    loginMenu(); //로그인 메뉴 실행
                    break;
                    
                case 2:
                    signupMenu(); //회원가입 메뉴 실행
                    break;
                    
                case 3:
                    System.out.println("프로그램이 종료되었습니다.");
                    return;
                    
                default:
                    System.out.println("잘못된 선택입니다.");
            }
        }
    }

    // 로그인 메뉴
    private void loginMenu() {
        System.out.println("===== 회원 로그인 =====");
        System.out.println("로그인을 위한 회원정보를 입력하세요");
        
        System.out.print("아이디: ");
        String id = scanner.nextLine();
        scanner.nextLine();
        
        System.out.print("비밀번호: ");
        String pw = scanner.nextLine();

        System.out.println("로그인 성공! 환영합니다.");
        
        // 메인 메뉴로 이동
        MainMenuController mainMenu = new MainMenuController(scanner);
        mainMenu.showMainMenu();
    }

    // 회원가입 메뉴
    private void signupMenu() {
        System.out.println("===== 회원가입 =====");
        System.out.println("회원가입을 위해 필요한 정보를 입력하세요.");
        
        System.out.print("아이디: ");
        scanner.nextLine();
        String id = scanner.nextLine();
        
        System.out.print("비밀번호: ");
        String pw = scanner.nextLine();
        
        System.out.print("이름: ");
        String name = scanner.nextLine();

        System.out.println("회원가입이 완료되었습니다.");
    }
}

