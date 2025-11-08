package teammanager.schedule;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

// 일정 생성, 체크, 삭제 및 진행도 확인
public class ScheduleController {

    private Scanner scanner;
    private List<Schedule> majorSchedules = new ArrayList<>(); // 주요 일정 목록
    private List<Schedule> personalSchedules = new ArrayList<>(); // 내 일정 목록
    private DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); // 날짜 형식 지정

    public ScheduleController(Scanner scanner) {
        this.scanner = scanner;
    }

    // 일정 메뉴 메인 화면
    public void showScheduleMenu(String teamName) {
        boolean run = true;
        while (run) {
            System.out.println();
            System.out.println("==============================");
            System.out.println("   " + teamName + " 팀플 관리");
            System.out.println("==============================");
            System.out.println("메뉴를 선택하세요:");
            System.out.println("1. 주요 일정");
            System.out.println("2. 내 역할(내 일정)");
            System.out.println("3. 진행도");
            System.out.println("0. 뒤로가기");
            System.out.print(">> ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    showTaskMenu("주요 일정");
                    break;
                    
                case 2:
                    showTaskMenu("내 일정");
                    break;
                    
                case 3:
                    showProgress();
                    break;
                    
                case 0:
                    System.out.println("메인 메뉴로 돌아갑니다.");
                    run = false;
                    break;
                    
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }

    //일정 세부 메뉴
    private void showTaskMenu(String type) {
        boolean run = true;
        while (run) {
            System.out.println();
            System.out.println("======= 주요 일정 =======");
            System.out.println("메뉴를 선택하세요: ");
            System.out.println("1. 생성");
            System.out.println("2. 체크");
            System.out.println("3. 삭제");
            System.out.println("0. 뒤로가기");
            System.out.print(">> ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createTask(type);
                    break;
                    
                case 2:
                    checkTask(type);
                    break;
                    
                case 3:
                    deleteTask(type);
                    break;
                    
                case 0:
                    run = false;
                    break;
                    
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }

    // 일정 생성
    private void createTask(String type) {
        System.out.println("==== 주요 일정 생성 ====");
        System.out.print("일정 이름: ");
        String name = scanner.nextLine();
        scanner.nextLine();

        System.out.print("마감 시간 (예: 2025-11-10 18:00): ");
        try {
            LocalDateTime due = LocalDateTime.parse(scanner.nextLine(), fmt);
            Schedule newSchedule = new Schedule(name, due);

            if (type.equals("주요 일정")) {
                majorSchedules.add(newSchedule);
                System.out.println("주요 일정이 추가되었습니다.");
                printList(majorSchedules);
            } else {
                personalSchedules.add(newSchedule);
                System.out.println("내 일정이 추가되었습니다.");
                printList(personalSchedules);
            }

        } catch (Exception e) {
            System.out.println("잘못된 형식입니다. 예: 2025-11-10 18:00");
        }
    }

    //일정 체크 (완료 / 지각 여부 확인)
    private void checkTask(String type) {
        List<Schedule> list = type.equals("주요 일정") ? majorSchedules : personalSchedules;

        // 완료되지 않은 일정만 표시
        List<Schedule> unchecked = new ArrayList<>();
        for (Schedule s : list) {
            if (!s.done) unchecked.add(s);
        }

        if (unchecked.isEmpty()) {
            System.out.println("체크할 일정이 없습니다.");
            return;
        }

        printList(unchecked);
        System.out.print("체크할 일정 번호: ");
        int num = scanner.nextInt();
        scanner.nextLine();

        if (num < 1 || num > unchecked.size()) {
            System.out.println("잘못된 입력입니다.");
            return;
        }

        Schedule task = unchecked.get(num - 1);
        System.out.print("제출 시간 (예: 2025-11-10 18:00): ");

        try {
            LocalDateTime submit = LocalDateTime.parse(scanner.nextLine(), fmt);
            task.check(submit, scanner);
            System.out.println("\n현재 " + type + " 목록:");
            printList(list);
        } catch (Exception e) {
            System.out.println("입력 오류입니다. 다시 시도하세요.");
        }
    }

    // 일정 삭제
    private void deleteTask(String type) {
        List<Schedule> list = type.equals("주요 일정") ? majorSchedules : personalSchedules;

        if (list.isEmpty()) {
            System.out.println("삭제할 일정이 없습니다.");
            return;
        }

        printList(list);
        System.out.print("삭제할 일정 번호: ");
        int num = scanner.nextInt();
        scanner.nextLine();

        if (num < 1 || num > list.size()) {
            System.out.println("잘못된 입력입니다.");
            return;
        }

        list.remove(num - 1);
        System.out.println("삭제되었습니다.");
        printList(list);
    }

    // 진행도 계산 (완료된 일정 비율)
    private void showProgress() {
        int total = majorSchedules.size() + personalSchedules.size();
        long done = majorSchedules.stream().filter(s -> s.done).count()
                + personalSchedules.stream().filter(s -> s.done).count();

        double progress = total == 0 ? 0 : done * 100.0 / total;

        System.out.println("==== 진행도 ====");
        System.out.println("전체 진행도: " + String.format("%.1f", progress) + "%");
    }

    // 알정 목록 출력
    private void printList(List<Schedule> list) {
        if (list.isEmpty()) {
            System.out.println("(등록된 일정이 없습니다)");
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
    }

    // 일정 정보 저장
    class Schedule {
        String name;
        LocalDateTime due;
        boolean done;
        boolean late;

        Schedule(String name, LocalDateTime due) {
            this.name = name;
            this.due = due;
            this.done = false;
            this.late = false;
        }

        // 일정 완료 처리 및 지각 여부 확인
        void check(LocalDateTime submit, Scanner sc) {
            this.done = true;
            if (submit.isAfter(due)) {
                this.late = true;
                System.out.println("마감 시간을 지키지 못했습니다.");
                System.out.println("사유를 선택하세요.");
                System.out.println("1. 개인사유");
                System.out.println("2. 팀플 일정 변경");
                System.out.println("3. 먼저 양해 구함");
                System.out.print(">> ");
                scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("제시간에 완료했습니다.");
            }
        }

        // 일정 상태 출력 형식
        public String toString() {
            String status = done ? (late ? "[지각]" : "[완료]") : "[미완료]";
            return status + " " + name + " (마감: " + due.format(fmt) + ")";
        }
    }
}
