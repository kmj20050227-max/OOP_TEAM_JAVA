// 임시
package teammanager.team;

import java.util.List;

// 팀 정보 저장
public class Team {
    private String name; // 팀 이름
    private List<String> members; // 팀원 목록

    // 팀 이름과 팀원 리스트 초기화
    public Team(String name, List<String> members) {
        this.name = name;
        this.members = members;
    }

    // 팀 이름 반환
    public String getName() {
        return name;
    }

    // 팀원 목록 반환
    public List<String> getMembers() {
        return members;
    }

    // 팀 정보를 문자열로 출력
    public String toString() {
        return name + " (" + String.join(", ", members) + ")";
    }
}

