//프로그램 진입점
package teammanager.main;

import java.util.Scanner;

import teammanager.auth.AuthController;

public class Main {
    public static void main(String[] args) {
    	//프로그램 시작 화면 
        System.out.println("============================");
        System.out.println("     상명대 전용 팀플 기록기     ");
        System.out.println("============================");
        
        // 로그인/ 회원가입 메뉴 실행
        Scanner scanner = new Scanner(System.in);
        AuthController auth = new AuthController(scanner);
        auth.start();
    }
}
