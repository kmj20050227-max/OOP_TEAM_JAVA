package com.teammanager.evaluation;

public class Evaluation {
	private int latePersonal;		//지각(개인 사유)
	private int lateScheduleChange;	//지각(일정 변경)
	private int latePreNotice;		//지각(사전 공지)
	private int absent;				//미참여
	private int etc;				//기타 사유
	
	//생성자
	public Evaluation() {}
	
	//항목 증가
	public void addLatePersonal() {
		latePersonal++;
	}
	
	public void addLateScheduleChange() {
		lateScheduleChange++;
	}
	
	public void addLatePreNotice() {
		latePreNotice++;
	}
	
	public void addAbsent() {
		absent++;
	}
	
	public void addEtc() {
		etc++;
	}
	// setter/getter
	public int getLatePersonal() {
		return latePersonal;
	}

	public void setLatePersonal(int latePersonal) {
		this.latePersonal = latePersonal;
	}

	public int getLateScheduleChange() {
		return lateScheduleChange;
	}

	public void setLateScheduleChange(int lateScheduleChange) {
		this.lateScheduleChange = lateScheduleChange;
	}

	public int getLatePreNotice() {
		return latePreNotice;
	}

	public void setLatePreNotice(int latePreNotice) {
		this.latePreNotice = latePreNotice;
	}

	public int getAbsent() {
		return absent;
	}

	public void setAbsent(int absent) {
		this.absent = absent;
	}

	public int getEtc() {
		return etc;
	}

	public void setEtc(int etc) {
		this.etc = etc;
	}
	
	//전체 합계
	public int getTotalCount() {
		int total = latePersonal + lateScheduleChange + latePreNotice + absent + etc;
		return total;
	}
	
}
