import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            Work.printMenu();
            int command = scanner.nextInt();

            if (command == 1) {
                MontlyReport.readMonthlyReport();
            } else if (command == 2) {
                YearlyReport.readYearlyReport();
            } else if (command == 3) {
                Work.reviseData();
            } else if (command == 4) {
                MontlyReport.infoMonthlyReport(MontlyReport.readMonthlyReport());
            }else if (command == 5) {
                YearlyReport.infoYearlyReport(YearlyReport.readYearlyReport());
            }else if (command == 321) {
                System.out.println("Выход");
                break;
            }else{
                System.out.println("Извините, такой команды нет, повторите ввод");
            }
        }
    }
}
