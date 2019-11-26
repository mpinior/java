import java.io.*;
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

//    public CSVReader(Reader reader, String delimiter, boolean hasHeader) throws IOException {
//        this.columnLabelsToInt = new HashMap<>();
//        this.reader = new BufferedReader(reader);
//        this.delimiter = buildDelimiterRegex(delimiter);
//        this.hasHeader = hasHeader;
//
//        if (hasHeader) {
//            parseHeader();
//        }
//    }
//
//    String buildDelimiterRegex(String delimiter) {
//        return String.format(SPLIT_REGEX, delimiter);
//    }

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
        current = teraz.split(delimiter);
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
        if(this.getRecordLength()<columnIndex) return true;
        if(current[columnIndex] != null && current[columnIndex] != "") return false;
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


    public static void main(String[] args) {
        CSVReader reader = new CSVReader("with-header.csv", ";", true);
        while (reader.next()) {
            int id = reader.getInt("id");
            String name = reader.get("imię");
            double fare = reader.getDouble("nrdomu");

            System.out.printf(Locale.US, "%d %s %d", id, name, fare);
        }
    }

}
