import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVReader {
    BufferedReader reader;
    String delimiter;
    boolean hasHeader;
    // nazwy kolumn w takiej kolejności, jak w pliku
    List<String> columnLabels = new ArrayList<>();
    // odwzorowanie: nazwa kolumny -> numer kolumny
    Map<String,Integer> columnLabelsToInt = new HashMap<>();
    String[] current;


    public CSVReader(String filename){
        
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
        String teraz = new String();
        try {
            teraz = reader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        current = teraz.split(delimiter);
        if(teraz==null)return false;
        return true;
    }



    public static void main(String[] args) {

    }

}
