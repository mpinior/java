import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlacowkiList {
    List<Placowki> placowki = new ArrayList<>();

    public void read(String filename){
        CSVReader reader = new CSVReader(filename, ";", true);
        while (reader.next()) {
            Placowki placowka = new Placowki();
            placowka.nazwa = reader.get("Nazwa placówki");
            placowka.rodzaj = reader.get("Rodzaj placówki");
            placowka.tel = reader.get("Telefony");
            placowka.email = reader.get("E-mail");
            placowka.dyrektor = reader.get("Dyrektor");
            placowki.add(placowka);
        }
    }

    public static void main(String[] args) {
        PlacowkiList lista= new PlacowkiList();
        lista.read("placowki.csv");
        //dużo funkcji mi brakuje i jestem pod stresem, więc nie wiem, jak znaleźć na szybko wielkość, dlatego spradziłam w csv
        String[] bezupraw = new String[320];
        String[] kobiety = new String[320];
        String[] przedszkola = new String[320];

        Pattern bez = Pattern.compile("\\[bez\\]");
        Pattern kobieta = Pattern.compile("\\[\\^a$\\]");
        Pattern przedszkole = Pattern.compile("\\[P|przedszkole\\]");

        for (int i=0; i<lista.placowki.size() ; i++){
            Matcher b = bez.matcher(lista.placowki.get(i).rodzaj);
            Matcher k = kobieta.matcher(lista.placowki.get(i).dyrektor);
            Matcher p = przedszkole.matcher(lista.placowki.get(i).nazwa);
            while(b.find()){
                bezupraw[i] = b.group();
            }
            while(k.find()){
                kobiety[i] = k.group();
            }
            while(p.find()){
                przedszkola[i] = p.group();
            }
        }
        for (int i=0; i<320; i++){
            System.out.println(bezupraw[i]);
        }
        for (int i=0; i<320; i++){
            System.out.println(kobiety[i]);
        }
        for (int i=0; i<320; i++){
            System.out.println(przedszkola[i]);
        }

    }
}
