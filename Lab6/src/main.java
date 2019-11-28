import java.io.File;
import java.io.PrintStream;
import java.util.Locale;


public class main {
    public static void main(String[] args){

//        CSVReader readerTest = new CSVReader("admin-units.csv", ",", true);
//        int n = 0;
//        //testowanie odczytu
//        while (n < 50) {
//            readerTest.next();
//            int id = readerTest.getInt("id");
//            String rodzic = readerTest.get("parent");
//            String name = readerTest.get("name");
//            double area = !readerTest.isMissing("area")?readerTest.getDouble("area") : 0;
//            System.out.printf(Locale.US, "%d %s %s %f\n", id, rodzic, name, area);
//            n += 1;
//        }
        //wypisanie wybranych jednostek
        AdminUnitList Lista = new AdminUnitList();
        Lista.read("admin-units.csv");
        PrintStream out = new PrintStream(System.out);
        Lista.list(out, 10,20);

    }
}
