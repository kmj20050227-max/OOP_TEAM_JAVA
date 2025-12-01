//프로그램 시작점
package com.teammanager.main;

import com.teammanager.auth.AuthController;

public class Main {
    public static void main(String[] args) {
    	//프로그램 시작 화면 
        System.out.println("============================");
        System.out.println("        수뭉이 팀플 관리       ");
        System.out.println("============================");
        
        //로그인/회원가입 화면으로 이동
        AuthController auth = new AuthController();
        auth.start();
    }
}
