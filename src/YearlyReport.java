import java.util.ArrayList;

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
    public static ArrayList yearlyProfit(String yearlyReport) {
        ArrayList<Integer> yearlyProfit = new ArrayList<>();
        String[] lines = yearlyReport.split("\\n");
        for (int a = 1; a < lines.length; a++){
            String[] lineContents = lines[a].split(",");
            for (int b = 2; b < lineContents.length; b = b + 3){
                Boolean isExpense = Boolean.parseBoolean(lineContents[b]);
                if (isExpense == false){
                    Integer z = Integer.parseInt(lineContents[b-2]);
                    yearlyProfit.add(z - 1, Integer.parseInt(lineContents[b-1]));
                }
            }
        }
        return yearlyProfit;
    }

}


