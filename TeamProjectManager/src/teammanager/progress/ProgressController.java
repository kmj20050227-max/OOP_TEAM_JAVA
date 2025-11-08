package teammanager.progress;

import java.util.Scanner;

public class ProgressController {

    private Scanner scanner;

    // MainMenuController에서 Scanner를 전달받음
    public ProgressController(Scanner scanner) {
        this.scanner = scanner;
    }

    // 진행도 화면을 출력하는 메서드 (미구현)
    public void showProgressMenu() {
        System.out.println("==== 진행도 ====");
        System.out.println("팀원\t진행률");
        System.out.println("----------------");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("----------------\n");
    }
}
