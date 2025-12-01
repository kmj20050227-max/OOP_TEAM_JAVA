package com.teammanager.team;

public class TeamMember {
	private String userId;	//팀원 ID
	
	public TeamMember(String userId) {	
		this.userId = userId;
	}
	
	//getter/setter
	public String getUserId() {	
		return userId;
	}

	public void setUserId(String userId) {	
		this.userId = userId;
	}
}