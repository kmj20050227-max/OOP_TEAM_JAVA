package com.teammanager.team;

import com.teammanager.team.Team;

public class TeamService {
	
	private Team[] teams = new Team[10]; //생성된 팀 목록
	private int count = 0;	//현재 팀 개수
	
	public void addTeam(Team team) { //팀 추가
		teams[count] = team;
		count++;
	}
	//getter/setter
	public Team[] getTeams() {	
		return teams;
	}

	public void setTeams(Team[] teams) { 
		this.teams = teams;
	}

	public int getCount() {	
		return count;
	}

	public void setCount(int count) { 
		this.count = count;
	}
	
	//팀 삭제
	public void removeTeam(int index) {	
		for (int i = index; i < count - 1; i++) {
			teams[i] = teams[i + 1]; 
		}
		teams[count - 1] = null; 
		count--; 
	}
}