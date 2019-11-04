import java.io.PrintStream;

public class ListItem {
    String element;

    ListItem(String item){
        this.element = item;
    }

    void writeHTML(PrintStream out){
        out.printf("<li> %s </li>\n", this.element);
    }
}
