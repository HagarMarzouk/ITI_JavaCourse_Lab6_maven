import tech.tablesaw.api.DateColumn;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import tech.tablesaw.api.NumericColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.selection.Selection;


public class WelcomeToMaven {
    public static void main(String[] args){
        System.out.println("Welcome to Maven!");
        try{
            Table hr=Table.read().csv("HR_comma_sep2.csv");
            System.out.println(hr.summary());
            System.out.println(hr.structure());
            System.out.println(hr.first(5));
            //sorting the data by the name columns
            System.out.println(hr.sortAscendingOn("name"));
            hr.sortAscendingOn("NO.");
            System.out.println(hr.first(5));

            //taking selected columns for the hr table and put them into new one
            Table suptable = hr.select("Name","Av Monthly hours");
            NumericColumn<?> le=suptable.numberColumn("Av Monthly hours");

            // select av Monthly hours from subtable where avmonthlyHour>140
            Selection leScore= le.isGreaterThan(140);
            System.out.println(suptable.where(leScore));

            //print the count of rows
            System.out.println(hr.rowCount());

            List<LocalDate> datelist = new ArrayList<>();
            for(int i = 0 ;i< hr.rowCount();i++){
                datelist.add(LocalDate.of(2021,3,6));
            }

            DateColumn datecol = DateColumn.create("fake Date", datelist);
            hr.insertColumn(hr.columnCount(),datecol);
            System.out.println(hr.structure());
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /* static void readLines() throws IOException {
        try (InputStream is = new FileInputStream("data/text.txt")) {
            String content = IOUtils.toString(is, StandardCharsets.UTF_8);
            System.out.println(content);
        }
    }

    public static void readLinesGuava() {
        File file = new File ("d:\\cities.csv");
        CharSource wordsSource = Files.asCharSource(file, StandardCharsets.UTF_8);
        List<String> lines = wordsSource.readLines();
    }

    static void getSummary(List<String>s){
        IntSummaryStatistics stat= s.stream().mapToInt(a->Integer.parseInt(a)).summaryStatistics();
        System.out.println(stat.getMax());
    }
    */
}
