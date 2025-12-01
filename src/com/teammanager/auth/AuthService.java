package com.teammanager.auth;

import com.teammanager.auth.User;

public class AuthService {
	
	private User[] users = new User[50];	// 사용자 배열
	private int count = 0;					// 등록된 사용자 수
	
	//회원가입
	public boolean register(String id, String password, String name) {
		for (int i = 0; i < count; i++) {
			User user = users[i];
			if (user.getId().equals(id)) {
				return false; // 실패 
			}
		}
		
		users[count] = new User(id, password, name);
		count++;
		
		return true;
	}
	
	//로그인
	public boolean login(String id, String password) {
		for (int i = 0; i < count; i++) {
			User user = users[i];
			
			if (user.getId().equals(id)) {
				if(user.getPassword().equals(password)) {
					return true; //성공
				}
			}
		}
		
		return false; //실패
	}
	
}