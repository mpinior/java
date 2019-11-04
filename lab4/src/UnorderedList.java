import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class UnorderedList {
    List<ListItem> lista = new ArrayList<>();

    UnorderedList(){};

    void addItem(ListItem element){
        lista.add(element);
    }

    void writeHTML(PrintStream out){
        out.printf("<ul>");
        for (int i=0; i<lista.size(); i++){
            this.lista.get(i).writeHTML(out);
        }
        out.printf("</ul>");
    }

    public static void main(String[] args) {
        UnorderedList list = new UnorderedList();
        list.addItem(new ListItem("asd"));
        list.addItem(new ListItem("xd"));
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        list.writeHTML(ps);
        String result = null;
        try {
            result = os.toString("UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }
}
