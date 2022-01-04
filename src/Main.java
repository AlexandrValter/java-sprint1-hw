import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            int command = scanner.nextInt();

            if (command == 1) MontlyReport.readMonthlyReport();
            else if (command == 2) YearlyReport.readYearlyReport();
            else if (command == 3) {
                HashMap <Integer, String> Monthes = new HashMap<>();
                Monthes.put(1, "Январь");
                Monthes.put(2, "Февраль");
                Monthes.put(3, "Март");
                Monthes.put(4, "Апрель");
                Monthes.put(5, "Май");
                Monthes.put(6, "Июнь");
                Monthes.put(7, "Июль");
                Monthes.put(8, "Август");
                Monthes.put(9, "Сентябрь");
                Monthes.put(10, "Октябрь");
                Monthes.put(11, "Ноябрь");
                Monthes.put(12, "Декабрь");
                ArrayList <Integer> monthlyExpenses = new ArrayList<>();
                ArrayList <Integer> monthlyProfit= new ArrayList<>();
                ArrayList <Integer> yearlyExpense= new ArrayList<>();
                ArrayList <Integer> yearlyProfit= new ArrayList<>();
                monthlyExpenses = MontlyReport.sumMontlyExpenses(MontlyReport.readMonthlyReport());
                monthlyProfit = MontlyReport.sumMontlyProfit(MontlyReport.readMonthlyReport());
                yearlyExpense = YearlyReport.yearlyExpense(YearlyReport.readYearlyReport());
                yearlyProfit = YearlyReport.yearlyProfit(YearlyReport.readYearlyReport());
                yearlyProfit.add(2, 100000);
                yearlyExpense.add(0, 100000);
                ArrayList <Integer> monthesError = new ArrayList<>();
                for (int i = 0; i < yearlyExpense.size(); i++) {
                    if (!Objects.equals(monthlyExpenses.get(i), yearlyExpense.get(i)) || !Objects.equals(monthlyProfit.get(i), yearlyProfit.get(i))) {
                        monthesError.add(i + 1);
                        i++;
                    }
                }
                if (monthesError.isEmpty()){
                        System.out.println("Сверка завершена успешно, несоответсвий не обнаружено");
                    }else{
                    System.out.println("Обнаружено несоответствие. Количество месяцев с неверными данными: " + monthesError.size());
                    System.out.println("Ошибка найдена в месяцах:");
                    for (int i = 0; i < monthesError.size(); i++){
                        int a = monthesError.get(i);
                        System.out.println(Monthes.get(a));
                    }
                }
            }else if (command == 4) {
            }else if (command == 5) {
            }else if (command == 321) {
                System.out.println("Выход");
                break;
            }else{
                System.out.println("Извините, такой команды нет, повторите ввод");
            }
        }
    }
    public static void printMenu() {
        System.out.println("Что вы хотите сделать? ");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("321 - Выход");
    }

}
