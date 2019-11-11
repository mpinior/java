import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class PhotoTest {

    @Test
    public void writeHTML() {
        String url = "moje_zdjecie.jpg";
        Photo photo = new Photo(url);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(os);
        photo.writeHTML(out);
        String result = null;
        try {
            result = os.toString("UTF-8");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertTrue(result.contains("/>"));
        assertTrue(result.contains(url));
        assertTrue(result.contains("height=\"42\" width=\"42\""));
    }
}