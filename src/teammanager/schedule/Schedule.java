package teammanager.schedule;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 일정 정보 관리 클래스
public class Schedule {
	//개별 제출 정보를 담는 내부 클래스
    public static class Submission {
        private LocalDateTime submitted; // 제출 시간
        private String status; // 제출 상태 ( 완료, 지각, 미참여)
        private String reason; // 지각 사유 등 추가 정보
        
        //초기 상태를 '미참여'로 설정
        public Submission() { 
        	this.submitted = null; 
        	this.status = "미참여"; 
        	this.reason = ""; 
        }
        
        // getter / setter
        public LocalDateTime getSubmitted() { return submitted; }
        public void setSubmitted(LocalDateTime s) { this.submitted = s; }
        
        public String getStatus() { return status; }
        public void setStatus(String s) { this.status = s; }
        
        public String getReason() { return reason; }
        public void setReason(String r) { this.reason = r; }
    }

    private String name; //일정 이름
    private LocalDateTime deadline; // 마감 시간
    private List<String> assigned = new ArrayList<>(); // 담당 팀원 목록
    private Map<String, Submission> submissions = new HashMap<>(); // 팀원별 제출 현황

    // 일정 이름, 마감 시간, 담당자 목록 설정
    public Schedule(String name, LocalDateTime deadline, List<String> assigned) {
        this.name = name;
        this.deadline = deadline;
        
        // 담당자가 있을 경우 리스트와 제출정보 맵 초기화
        if (assigned != null) {
            this.assigned.addAll(assigned);
            for (String m : assigned) submissions.put(m, new Submission());
        }
    }

    public String getName() { return name; }
    public LocalDateTime getDeadline() { return deadline; }
    public List<String> getAssigned() { return assigned; }
    
    // 특정 팀원의 제출 정보 가져오기
    public Submission getSubmission(String member) { 
    	return submissions.get(member); 
    	}
    
    // 담당자 추가 (중복 방지)
    public void addAssigned(String member) {
        if (!assigned.contains(member)) {
            assigned.add(member);
            submissions.put(member, new Submission());
        }
    }
}




