package teammanager.team;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


// 팀 데이터 관리 서비스 (팀 추가, 조회 등 담당)
public class TeamService {
	// 전체 팀 정봊 저장
    private static List<Team> teams = new ArrayList<>();

    // 예시 팀 데이터
    static {
        teams.add(new Team("AI Study", Arrays.asList("Yuna","Minsu","Jiyoon")));
        teams.add(new Team("SW Project", Arrays.asList("Yuna","Hyunwoo","Mina")));
    }

    // 모든 팀 목록 반환
    public List<Team> getAllTeams() { 
    	return teams; }

    // 새로운 팀 추가
    public void addTeam(String name, List<String> members) {
        teams.add(new Team(name, members));
    }

    // 팀 이름으로 팀을 검색 
    public Team findByName(String name) {
        for (Team t : teams) {
            if (t.getName().equalsIgnoreCase(name)) return t;
        }
        
        return null; // 일치하는 팀이 없을 경우
    }
}

