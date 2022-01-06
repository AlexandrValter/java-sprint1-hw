import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        loop:
        while (true) {
            ReportManager.printMenu();
            String command = scanner.next();
            switch (command) {
                case ("1"):
                    ReportManager.readMonthlyReport();
                    break;
                case ("2"):
                    ReportManager.readYearlyReport();
                    break;
                case ("3"):
                    ReportManager.reviseReport();
                    break;
                case ("4"):
                    MonthlyReport.infoMonthlyReport(ReportManager.monthlyReport);
                    break;
                case ("5"):
                    YearlyReport.infoYearlyReport(ReportManager.yearlyReport);
                    break;
                case ("`"):
                    System.out.println("Выход");
                    break loop;
                default:
                    System.out.println("Извините, такой команды нет, повторите ввод");
            }
        }
    }
}