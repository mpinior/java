import java.util.Locale;


public class main {
    public static void main(String[] args) {
        CSVReader reader = new CSVReader("admin-units.csv", ",", true);
        int n = 0;
        while (n < 50) {
            reader.next();
            int id = reader.getInt("id");
            String rodzic = reader.get("parent");
            String name = reader.get("name");
            double area = !reader.isMissing("area")?reader.getDouble("area") : 0;
            System.out.printf(Locale.US, "%d %s %s %f\n", id, rodzic, name, area);
            n += 1;
        }
    }
}
