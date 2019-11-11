import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class ListItemTest {

    @Test
    public void writeHTML() {
        String text = "Japonia";
        ListItem lista = new ListItem(text);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(os);
        lista.writeHTML(out);
        String result = null;
        try {
            result = os.toString("UTF-8");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        assertTrue(result.contains(" Japonia "));
        assertTrue(result.contains("<li>"));
        assertTrue(result.contains("</li>"));
    }

}