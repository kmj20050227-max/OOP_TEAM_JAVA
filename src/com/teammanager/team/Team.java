package com.teammanager.team;

import com.teammanager.progress.Progress;
import com.teammanager.evaluation.Evaluation;

public class Team {
	private String teamName;				// 팀 이름
	private String[] memberUserId = new String[10];	//팀원 ID 목록 (최대 10명)
	private int memberCount = 0;			//현재 팀원 수
	
	private Progress progress;				//팀 전체 진행도 관리
	private Evaluation evaluation;			//팀 평가 관리
	
	public Team(String teamName) {			
		this.teamName = teamName;
		this.progress = new Progress();
		this.evaluation = new Evaluation();
	}
	//팀원 추가
	public boolean addMember(String userId) {	
		if (memberCount >= memberUserId.length) {
			return false;
		}
		memberUserId[memberCount] = userId;
		memberCount++;
		return true;
	}
	//getter/setter
	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String[] getMemberUserId() {
		return memberUserId;
	}

	public void setMemberUserId(String[] memberUserId) {
		this.memberUserId = memberUserId;
	}

	public int getMemberCount() {
		return memberCount;
	}

	public void setMemberCount(int memberCount) {
		this.memberCount = memberCount;
	}
	
	public Progress getProgress() {
		return progress;
	}
	
	public Evaluation getEvaluation() {	
		return evaluation;
	}
}

