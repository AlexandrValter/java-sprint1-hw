import java.util.HashMap;

public class YearlyReport {

    public static void infoYearlyReport(HashMap<String, HashMap<Integer, Integer>> yearlyReport) {
        if (yearlyReport.size() == 0) {
            System.out.println("Недостаточно данных для вывода информации, произведите считывание годового отчета");
        } else {
            System.out.println("Информация по 2021 году");
            int avgEarning = 0;
            int avgExpense = 0;
            HashMap<Integer, Integer> yearlyExpenses = new HashMap<>();
            HashMap<Integer, Integer> yearlyEarnings = new HashMap<>();
            for (String type : yearlyReport.keySet()) {
                if (type.equals("Expenses")) {
                    yearlyExpenses = yearlyReport.get(type);
                    avgExpense = average(yearlyExpenses);
                } else {
                    yearlyEarnings = yearlyReport.get(type);
                    avgEarning = average(yearlyEarnings);
                }
            }
            for (Integer i : yearlyEarnings.keySet()) {
                System.out.println("Прибыль за " + ReportManager.nameOfMonthes().get(i) + " составила " +
                        (yearlyEarnings.get(i) - yearlyExpenses.get(i)));
            }
            System.out.println("Средний расход за год составил " + avgExpense);
            System.out.println("Средний доход за год составил " + avgEarning);
        }
    }

    public static int average(HashMap<Integer, Integer> data) {
        int sum = 0;
        for (Integer i : data.values()) {
            sum += i;
        }
        return sum / data.size();
    }
}