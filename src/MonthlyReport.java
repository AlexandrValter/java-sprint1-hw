import java.util.HashMap;

public class MonthlyReport {

    public static void infoMonthlyReport(HashMap<String, HashMap<Integer, HashMap<String, Integer>>> monthlyReport) {
        if (monthlyReport.size() == 0) {
            System.out.println("Недостаточно данных для вывода информации, произведите считывание месячных отчетов");
        } else {
            System.out.println("Информация по загруженным месячным отчетам");
            HashMap<Integer, HashMap<String, Integer>> expenses = new HashMap<>();
            HashMap<Integer, HashMap<String, Integer>> earnings = new HashMap<>();
            for (String type : monthlyReport.keySet()) {
                HashMap<Integer, HashMap<String, Integer>> dataMonthly = monthlyReport.get(type);
                if (type.equals("Expenses")) {
                    expenses = formationTables(dataMonthly);
                } else {
                    earnings = formationTables(dataMonthly);
                }
            }
            for (Integer i : expenses.keySet()) {
                System.out.println("Месяц " + ReportManager.nameOfMonthes().get(i));
                System.out.println("Максимальная трата " + expenses.get(i));
                System.out.println("Максимальный доход " + earnings.get(i));
            }
        }
    }

    public static HashMap<Integer, HashMap<String, Integer>> formationTables(HashMap<Integer, HashMap<String, Integer>> dataMonthly) {
        HashMap<Integer, HashMap<String, Integer>> table = new HashMap<>();
        for (Integer i : dataMonthly.keySet()) {
            HashMap<String, Integer> monthOperations = dataMonthly.get(i);
            HashMap<String, Integer> maxOperations = new HashMap<>();
            Integer maxOperation = 0;
            String itemMaxOperation = " ";
            for (String name : monthOperations.keySet()) {
                if (monthOperations.get(name) > maxOperation) {
                    maxOperation = monthOperations.get(name);
                    itemMaxOperation = name;
                }
            }
            maxOperations.put(itemMaxOperation, maxOperation);
            table.put(i, maxOperations);
        }
        return table;
    }
}