import java.util.ArrayList;
import java.util.HashMap;

public class YearlyReport {

    public static String readYearlyReport(){
        String yearlyReport = Work.readFileContentsOrNull("C:\\Users\\User\\dev\\java-sprint1-hw\\resources\\y.2021.csv");
        return yearlyReport;
    }

    public static ArrayList yearlyExpense(String yearlyReport) {
        ArrayList<Integer> yearlyExpense = new ArrayList<>();
        String[] lines = yearlyReport.split("\\n");
        for (int a = 1; a < lines.length; a++){
            String[] lineContents = lines[a].split(",");
            for (int b = 2; b < lineContents.length; b = b + 3){
                Boolean isExpense = Boolean.parseBoolean(lineContents[b]);
                if (isExpense == true){
                    Integer z = Integer.parseInt(lineContents[b-2]);
                    yearlyExpense.add(z - 1, Integer.parseInt(lineContents[b-1]));
                }
            }
        }
        return yearlyExpense;
    }
    public static ArrayList yearlyIncome(String yearlyReport) {
        ArrayList<Integer> yearlyIncome = new ArrayList<>();
        String[] lines = yearlyReport.split("\\n");
        for (int a = 1; a < lines.length; a++){
            String[] lineContents = lines[a].split(",");
            for (int b = 2; b < lineContents.length; b = b + 3){
                Boolean isExpense = Boolean.parseBoolean(lineContents[b]);
                if (isExpense == false){
                    Integer z = Integer.parseInt(lineContents[b-2]);
                    yearlyIncome.add(z - 1, Integer.parseInt(lineContents[b-1]));
                }
            }
        }
        return yearlyIncome;
    }

    public static void infoYearlyReport (String yearlyReport){

        HashMap<Integer, Integer> expense = new HashMap<>();
        HashMap<Integer, Integer> income = new HashMap<>();
        String[] lines = yearlyReport.split("\\n");
        System.out.println("Информация по 2021 году:");
        for (int a = 1; a < lines.length; a++){
            String[] lineContents = lines[a].split(",");
            for (int b = 2; b < lineContents.length; b = b + 3){
                Boolean isExpense = Boolean.parseBoolean(lineContents[b]);
                Integer x = Integer.parseInt(lineContents[b-2]);
                Integer y = Integer.parseInt(lineContents[b-1]);
                if (isExpense == true){
                    expense.put(x, y);
                }else{
                    income.put(x, y);
                }
            }
        }
        for (Integer i : expense.keySet()){
            System.out.println("Прибыль в месяце " + Work.nameOfMonthes().get(i) + " составила: " + (income.get(i) - expense.get(i)));
        }
        int avgExpense = 0;
        int sumExpense = 0;
        for (Integer i : expense.keySet()){
            sumExpense +=expense.get(i);
        }
        avgExpense = sumExpense / expense.size();
        System.out.println("Средний расход в году составил: " + avgExpense);
        int avgIncome = 0;
        int sumIncome = 0;
        for (Integer i : income.keySet()){
            sumIncome +=income.get(i);
        }
        avgIncome = sumIncome / income.size();
        System.out.println("Средний доход в году составил: " + avgIncome);
    }

}


