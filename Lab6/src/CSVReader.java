import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CSVReader {
    BufferedReader reader;
    String delimiter;
    boolean hasHeader;
    List<String> columnLabels = new ArrayList<>();
    Map<String,Integer> columnLabelsToInt = new HashMap<>();
    String[] current;




    public CSVReader(String filename){
        this(filename, ",",false);
    }

    public CSVReader(String filename,String delimiter){
        this(filename, delimiter, false);
    }

    public CSVReader(String filename,String delimiter,boolean hasHeader) {
        try {
            reader = new BufferedReader(new FileReader(filename));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
        if(hasHeader)parseHeader();
    }

    public CSVReader(Reader reader, String delimiter, boolean hasHeader) {
        this.reader = new BufferedReader(reader);
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
        if(hasHeader)parseHeader();
    }

    void parseHeader() {
        try {
            String line = reader.readLine();
            if (line == null) {
                return;
            }
            String[] header = line.split(delimiter);
            for (int i = 0; i < header.length; i++) {
                columnLabelsToInt.put(header[i], i);
                columnLabels.add(header[i]);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    boolean next(){
        String teraz = null;
        try {
            teraz = reader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        if(teraz==null)return false;
        current = teraz.split(delimiter + "(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        return true;
    }

    List<String> getColumnLabels(){
        List<String> result = new ArrayList<>();
        for (int i = 0; i < this.columnLabels.size(); i++) {
            result.add(this.columnLabels.get(i));
        }
        return result;
    }

    int getRecordLength(){
        return this.current.length;
    }

    boolean isMissing(int columnIndex){
        if(this.getRecordLength()<=columnIndex) return true;
        if(current[columnIndex] != null && !current[columnIndex].isEmpty()) return false;
        return true;
    }

    boolean isMissing(String columnLabel){
        if(columnLabelsToInt.size() == 0 || columnLabelsToInt.get(columnLabel) == null){
            return true;
        }
        return isMissing(columnLabelsToInt.get(columnLabel));
    }

    String get(int columnIndex) {
        if(current.length<=columnIndex ||current[columnIndex]==null)return "";
        return current[columnIndex];
    }

    String get(String columnLabel) {
        if(columnLabelsToInt.size()==0 || columnLabelsToInt.get(columnLabel)==null) {
            return "";
        }
        return get(columnLabelsToInt.get(columnLabel));
    }

    int getInt(int columnIndex){
        if(columnIndex>=current.length)throw new RuntimeException("Poza zakresem");
        return Integer.parseInt(this.current[columnIndex]);
    }
    int getInt(String columnLabel) {
        if(columnLabelsToInt.get(columnLabel)==null)throw new RuntimeException("Nieznana nazwa");
        return getInt(columnLabelsToInt.get(columnLabel));
    }

    long getLong(int columnIndex) {
        if(columnIndex>=current.length)throw new RuntimeException("Poza zakresem");
        return Long.parseLong(current[columnIndex]);
    }
    long getLong(String columnLabel) {
        if(columnLabelsToInt.get(columnLabel)==null)throw new RuntimeException("Nieznana nazwa");
        return getLong(columnLabelsToInt.get(columnLabel));
    }
    double getDouble(int columnIndex) {
        if(columnIndex>=current.length)throw new RuntimeException("Poza zakresem");
        return Double.parseDouble(current[columnIndex]);
    }
    double getDouble(String columnLabel) {
        if(columnLabelsToInt.get(columnLabel)==null)throw new RuntimeException("Nieznana nazwa");
        return getDouble(columnLabelsToInt.get(columnLabel));
    }

    LocalTime getTime(int columnIndex) {
        return getTime(columnIndex, "HH:mm:ss");
    }

    LocalTime getTime(int columnIndex, String format) {
        if(get(columnIndex)=="")throw new RuntimeException("Out of bounds");
        return LocalTime.parse(get(columnIndex), DateTimeFormatter.ofPattern(format));
    }
    LocalTime getTime(String columnLabel) {
        return getTime(columnLabel, "HH:mm:ss");
    }

    LocalTime getTime(String columnLabel, String format) {
        if(columnLabelsToInt.get(columnLabel)==null)throw new RuntimeException("Unknown Label");
        return getTime(columnLabelsToInt.get(columnLabel), format);
    }
    LocalDate getDate(int columnIndex) {
        return getDate(columnIndex, "RR-mm-dd");
    }

    LocalDate getDate(int columnIndex, String format) {
        if(get(columnIndex)=="")throw new RuntimeException("Out of bounds");
        return LocalDate.parse(get(columnIndex), DateTimeFormatter.ofPattern(format));
    }
    LocalDate getDate(String columnLabel) {
        return getDate(columnLabel, "RR-mm-dd");
    }

    LocalDate getDate(String columnLabel, String format) {
        if(columnLabelsToInt.get(columnLabel)==null)throw new RuntimeException("Unknown Label");
        return getDate(columnLabelsToInt.get(columnLabel), format);
    }


    public static void main(String[] args) {
        //Test1
        CSVReader reader = new CSVReader("header.csv", ",", true);
        while (reader.next()) {
            int id = reader.getInt("id");
            String name = reader.get("imie");
            String surname = reader.get("nazwisko");
            String ulica = reader.get("ulica");
            double dom = reader.getDouble("nrdomu");
            long mieszkanie = reader.getLong("nrmieszkania");

            System.out.printf(Locale.US, "%d %s %s %s %f %d\n", id, name, surname, ulica, dom, mieszkanie);
        }

        //Test2 -> sprawdzenie poprzez isMissing
        CSVReader reader2 = new CSVReader("missing-values.csv", ";", true);
        while (reader2.next()) {
            int id = reader2.getInt("id");
            int parent = !reader2.isMissing("parent")?reader2.getInt("parent") :0;
            String name = reader2.get("name");
            int admin = !reader2.isMissing("admin_level")?reader2.getInt("admin_level") : 0;
            int population = !reader2.isMissing("population")?reader2.getInt("population") : 0;
            double area = !reader2.isMissing("area")?reader2.getDouble("area") : 0;
            double density = !reader2.isMissing("density")?reader2.getDouble("density") : 0;
            System.out.printf(Locale.US, "%d %d %s %d %d %f %f\n", id, parent, name, admin, population, area, density);
        }

        //Test3 -> odwołanie się do nieistniejących kolumn poprzez index i String
        reader2.get(50);
        reader2.get("Miasto");

        //Test4 -> testowanie z innych źródeł
        String text = "a,b,c\n123.4,567.8,91011.12";
        reader = new CSVReader(new StringReader(text),",",true);
        while (reader.next()){
            for (String s : reader.current) {
                System.out.println(s);
            }
        }

        //Titanic
        CSVReader reader3 = new CSVReader("titanic-part.csv", ",", true);
        while (reader3.next()) {
            int id = reader3.getInt("PassengerId");
            String name = reader3.get("Name");
            int age = !reader3.isMissing("Age")?reader3.getInt("Age") : 0;
            System.out.printf(Locale.US, "%d %s %d\n", id, name, age);
        }

    }

}
