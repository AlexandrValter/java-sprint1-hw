import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Work {

    public static String readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }

    public static void reviseData() {

        ArrayList <Integer> monthlyExpenses;
        ArrayList <Integer> monthlyIncome;
        ArrayList <Integer> yearlyExpense;
        ArrayList <Integer> yearlyIncome;
        monthlyExpenses = MontlyReport.sumMontlyExpenses(MontlyReport.readMonthlyReport());
        monthlyIncome = MontlyReport.sumMontlyIncome(MontlyReport.readMonthlyReport());
        yearlyExpense = YearlyReport.yearlyExpense(YearlyReport.readYearlyReport());
        yearlyIncome = YearlyReport.yearlyIncome(YearlyReport.readYearlyReport());
        ArrayList <Integer> monthesError = new ArrayList<>();
        for (int i = 0; i < yearlyExpense.size(); i++) {
            if (!Objects.equals(monthlyExpenses.get(i), yearlyExpense.get(i)) || !Objects.equals(monthlyIncome.get(i), yearlyIncome.get(i))) {
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
                System.out.println(nameOfMonthes().get(a));
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

    public static HashMap nameOfMonthes() {
        HashMap<Integer, String> monthes = new HashMap<>();
        monthes.put(1, "Январь");
        monthes.put(2, "Февраль");
        monthes.put(3, "Март");
        monthes.put(4, "Апрель");
        monthes.put(5, "Май");
        monthes.put(6, "Июнь");
        monthes.put(7, "Июль");
        monthes.put(8, "Август");
        monthes.put(9, "Сентябрь");
        monthes.put(10, "Октябрь");
        monthes.put(11, "Ноябрь");
        monthes.put(12, "Декабрь");

        return monthes;
    }
}
