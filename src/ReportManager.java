import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class ReportManager {
    static HashMap<String, HashMap<Integer, Integer>> yearlyReport = new HashMap<>();
    static HashMap<String, HashMap<Integer, HashMap<String, Integer>>> monthlyReport = new HashMap<>();

    public static String readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }

    public static void printMenu() {
        System.out.println("Что вы хотите сделать? ");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("` - Выход");
    }

    public static HashMap<Integer, String> nameOfMonthes() {
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

    public static void readMonthlyReport() {
        HashMap<Integer, HashMap<String, Integer>> expenses = new HashMap<>();
        HashMap<Integer, HashMap<String, Integer>> earnings = new HashMap<>();
        ArrayList<String> allMonthlyReports = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            String report = ReportManager.readFileContentsOrNull("resources\\m.20210" + i + ".csv");
            allMonthlyReports.add(report);
        }
        for (int i = 0; i < 3; i++) {
            String[] lines = allMonthlyReports.get(i).split("\\n");
            HashMap<String, Integer> monthExpenses = new HashMap<>();
            HashMap<String, Integer> monthEarnings = new HashMap<>();
            for (int a = 1; a < lines.length; a++) {
                String[] lineContents = lines[a].split(",");
                for (int b = 1; b < lineContents.length; b = b + 4) {
                    boolean isExpense = Boolean.parseBoolean(lineContents[b]);
                    Integer quantity = Integer.parseInt(lineContents[b + 1]);
                    Integer sumOfOne = Integer.parseInt(lineContents[b + 2]);
                    if (isExpense) {
                        monthExpenses.put(lineContents[b - 1], quantity * sumOfOne);
                    } else {
                        monthEarnings.put(lineContents[b - 1], quantity * sumOfOne);
                    }
                }
            }
            earnings.put((i + 1), monthEarnings);
            expenses.put((i + 1), monthExpenses);
        }
        monthlyReport.put("Expenses", expenses);
        monthlyReport.put("Earnings", earnings);
    }

    public static void readYearlyReport() {
        String report = ReportManager.readFileContentsOrNull("resources\\y.2021.csv");
        HashMap<Integer, Integer> expenses = new HashMap<>();
        HashMap<Integer, Integer> earnings = new HashMap<>();
        String[] lines = report.split("\\n");
        for (int a = 1; a < lines.length; a++) {
            String[] lineContents = lines[a].split(",");
            for (int b = 2; b < lineContents.length; b = b + 3) {
                boolean isExpense = Boolean.parseBoolean(lineContents[b]);
                Integer month = Integer.parseInt(lineContents[b - 2]);
                Integer sum = Integer.parseInt(lineContents[b - 1]);
                if (isExpense) {
                    expenses.put(month, sum);
                } else {
                    earnings.put(month, sum);
                }
            }
        }
        yearlyReport.put("Expenses", expenses);
        yearlyReport.put("Earnings", earnings);
    }

    public static void reviseReport() {
        HashMap<String, ArrayList> monthesError = new HashMap<>();
        if ((monthlyReport.size() == 0) || (yearlyReport.size() == 0)) {
            System.out.println("Недостаточно данных для проведения сверки, произведите считывание отчётов");
        } else {
            for (String type : monthlyReport.keySet()) {
                HashMap<Integer, HashMap<String, Integer>> dataMonthly = monthlyReport.get(type);
                HashMap<Integer, Integer> dataYearly = yearlyReport.get(type);
                if (type.equals("Expenses")) {
                    if (!findErrors(dataMonthly, dataYearly).isEmpty()) {
                        monthesError.put("тратах", findErrors(dataMonthly, dataYearly));
                    }
                } else {
                    if (!findErrors(dataMonthly, dataYearly).isEmpty()) {
                        monthesError.put("доходах", findErrors(dataMonthly, dataYearly));
                    }
                }
            }
            if (monthesError.isEmpty()) {
                System.out.println("Сверка завершена успешно, несоответсвий не обнаружено");
            } else {
                System.out.println("Обнаружено несоответствие:");
                for (String type : monthesError.keySet()) {
                    ArrayList<Integer> numbers = monthesError.get(type);
                    for (Integer i : numbers) {
                        System.out.println("Ошибка выявлена в " + type + " за месяц " + nameOfMonthes().get(i));
                    }
                }
            }
        }
    }

    public static ArrayList<Integer> findErrors(HashMap<Integer, HashMap<String, Integer>> dataMonthly,
                                                HashMap<Integer, Integer> dataYearly) {
        ArrayList<Integer> errors = new ArrayList<>();
        for (Integer i : dataMonthly.keySet()) {
            HashMap<String, Integer> monthOperations = dataMonthly.get(i);
            Integer sumOperations = 0;
            for (Integer x : monthOperations.values()) {
                sumOperations += x;
            }
            if (!dataYearly.get(i).equals(sumOperations)) {
                errors.add(i);
            }
        }
        return errors;
    }
}