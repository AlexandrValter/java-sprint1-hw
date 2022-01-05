import java.util.ArrayList;

public class MontlyReport {

    public static ArrayList<String> readMonthlyReport() { //считываем данные из файлов с месячными отчетами
        ArrayList<String> allMonthlyReports = new ArrayList<>(); //данные будут сохранены в список allMonthlyReports
        for (int i = 1; i < 4; i++) {
            String report = Work.readFileContentsOrNull("C:\\Users\\User\\dev\\java-sprint1-hw\\resources\\m.20210" + i + ".csv");
            allMonthlyReports.add(report);
        }
        return allMonthlyReports;
    }

    public static ArrayList sumMontlyExpenses(ArrayList<String> monthlyReport) {
        ArrayList<Integer> sumMonthlyExpenses = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            String[] lines = monthlyReport.get(i).split("\\n");
            Integer sumExpenses = 0;
            for (int a = 1; a < lines.length; a++){
                String[] lineContents = lines[a].split(",");
                for (int b = 1; b < lineContents.length; b = b + 4){
                    Boolean isExpense = Boolean.parseBoolean(lineContents[b]);
                    if (isExpense == true){
                        Integer quantity = Integer.parseInt(lineContents[b + 1]);
                        Integer sumOfOne = Integer.parseInt(lineContents[b + 2]);
                        sumExpenses +=quantity*sumOfOne;
                    }
                }
            }
            sumMonthlyExpenses.add(sumExpenses);
        }
        return sumMonthlyExpenses;
    }
    public static ArrayList sumMontlyIncome(ArrayList<String> monthlyReport) {
        ArrayList<Integer> sumMonthlyIncome = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            String[] lines = monthlyReport.get(i).split("\\n");
            Integer sumIncome = 0;
            for (int a = 1; a < lines.length; a++){
                String[] lineContents = lines[a].split(",");
                for (int b = 1; b < lineContents.length; b = b + 4){
                    Boolean isExpense = Boolean.parseBoolean(lineContents[b]);
                    if (isExpense == false){
                        Integer quantity = Integer.parseInt(lineContents[b + 1]);
                        Integer sumOfOne = Integer.parseInt(lineContents[b + 2]);
                        sumIncome += quantity*sumOfOne;
                    }
                }
            }
            sumMonthlyIncome.add(sumIncome);
        }
        return sumMonthlyIncome;
    }

    public static void infoMonthlyReport (ArrayList<String> monthlyReport){
        System.out.println("Информация по загруженным месячным отчетам");
        for (int i = 0; i < 3; i++) {
            String[] lines = monthlyReport.get(i).split("\\n");
            int maxExpense = 0;
            int maxIncome = 0;
            String expenseItem = (" ");
            String incomeItem = (" ");
            System.out.println("Месяц " + Work.nameOfMonthes().get(i+1) + ":");
            for (int a = 1; a < lines.length; a++){
                String[] lineContents = lines[a].split(",");
                for (int b = 1; b < lineContents.length; b = b + 4){
                    Boolean isExpense = Boolean.parseBoolean(lineContents[b]);
                    int expense = 0;
                    int income = 0;
                    Integer quantity = Integer.parseInt(lineContents[b + 1]);
                    Integer sumOfOne = Integer.parseInt(lineContents[b + 2]);
                    if (isExpense == true){
                        expense = quantity*sumOfOne;
                        if (expense > maxExpense){
                            maxExpense = expense;
                            expenseItem = lineContents[b-1];
                        }
                    }else{
                        income = quantity*sumOfOne;
                        if (income > maxIncome){
                            maxIncome = income;
                            incomeItem = lineContents[b-1];
                        }
                    }
                }
            }
            System.out.println("Самый прибыльный товар - " + incomeItem + ", сумма дохода - " + maxIncome);
            System.out.println("Самая большая трата - " + expenseItem + ", сумма траты - " + maxExpense);
        }
    }
}

