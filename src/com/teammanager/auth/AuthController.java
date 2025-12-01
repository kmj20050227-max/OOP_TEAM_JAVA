//로그인 메인 화면
package com.teammanager.auth;

import java.util.Scanner;
import com.teammanager.main.MainMenuController;

public class AuthController {
	
	private AuthService authService = new AuthService();
	
	public void start() {
		
		Scanner scanner = new Scanner(System.in);
		boolean run = true;
		
		while(run) {
			//로그인 메뉴 화면
            System.out.println("메뉴를 선택하세요:");
            System.out.println("1. 로그인");
            System.out.println("2. 회원가입");
            System.out.println("3. 프로그램 종료");
            System.out.print(">> ");

            String choice = scanner.nextLine();
            
            //1번: 로그인
            if(choice.equals("1")) {
            	 System.out.println("===== 회원 로그인 =====");
                 System.out.println("로그인을 위한 회원정보를 입력하세요");
                 
                 System.out.print("아이디: ");
                 String id = scanner.nextLine();
                 
                 System.out.print("비밀번호: ");
                 String password = scanner.nextLine();
                 
                 //로그인 검증
                 boolean loginSuccess = authService.login(id, password);
                 
                 if (loginSuccess) {
                	 System.out.println("로그인 성공!\n");
                	 MainMenuController menu = new MainMenuController();
                	 menu.mainMenu();
                 } 
                 
                 else {
                	 System.out.println("존재하지 않는 아이디입니다.\n");
                 }
            }    
            //2번: 회원가입
            else if(choice.equals("2")) {
            	
            	 System.out.println("===== 회원가입 =====");
                 System.out.println("회원가입을 위해 필요한 정보를 입력하세요.");
                 
                 System.out.print("아이디: ");
                 String id = scanner.nextLine();
                 
                 System.out.print("비밀번호: ");
                 String password = scanner.nextLine();
                 
                 System.out.print("이름: ");
                 String name = scanner.nextLine();
                 
                 // 회원가입 처리
                 boolean registerSuccess = authService.register(id, password, name);
                 
                 if (registerSuccess) {
                	 System.out.println("회원가입 완료");
                 }
                 else {
                	 System.out.println("이미 존재하는 아이디입니다.");
                 }
                
            }
            //3번: 프로그램 종료
            else if(choice.equals("3")) {
            	System.out.println("프로그램이 종료되었습니다.");
                    run = false;        
            }
		}
	}
}