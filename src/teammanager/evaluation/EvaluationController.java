package teammanager.evaluation;

import java.util.Scanner;

public class EvaluationController {

    private Scanner scanner;

    // 외부에서 Scanner를 받아 사용
    public EvaluationController(Scanner scanner) {
        this.scanner = scanner;
    }

    // 팀 평과 결과 출력 메뉴 (예시)
    public void showEvaluationMenu() {
        System.out.println("==== 평가 결과 ====");
        System.out.println("팀원\t점수\t지각\t미참여");
        System.out.println("-----------------------");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("-----------------------\n");
    }
}
