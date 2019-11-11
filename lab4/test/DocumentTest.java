import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class DocumentTest {

    @org.junit.Test
    public void writeHTML() {
        Document cv = new Document("Moje CV");
        cv.setPhoto("Moje_zdjecie.jpg");
        cv.sections.add(new Section("Wyksztalcenie"));
        cv.sections.get(0).addParagraph(new Paragraph("2012 - ukonczenie szkoly u Ksiezniczki Elzy i Anny"));
        cv.sections.get(0).addParagraph(new Paragraph("2011 - ukonczenie szkoly za siedmioma gorami"));
        cv.sections.add(new Section("Zainteresowania"));
        cv.sections.get(1).addParagraph(new ParagraphWithList("Moje zainteresowania:"));
        ((ParagraphWithList) cv.sections.get(1).paragraphs.get(0)).addItemToList(new ListItem("Japonia"));
        ((ParagraphWithList) cv.sections.get(1).paragraphs.get(0)).addItemToList(new ListItem("Crafting"));
        ((ParagraphWithList) cv.sections.get(1).paragraphs.get(0)).addItemToList(new ListItem("Szycie"));

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(os);
        cv.writeHTML(out);
        cv.writeHTML(System.out);

        String result = null;
        try {
            result = os.toString("UTF-8");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        assertTrue(result.contains("<h1> Wyksztalcenie </h1>"));
        assertTrue(result.contains("<li> Japonia </li>"));
        assertTrue(result.contains("<p> 2011 - ukonczenie szkoly za siedmioma gorami </p>"));
    }
}