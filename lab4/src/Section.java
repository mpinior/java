import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Section {
    String title;
    List<Paragraph> paragraphs = new ArrayList<>();

    Section(){}

    Section(String title){
        this.title=title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    void addParagraph(Paragraph p) {
        this.paragraphs.add(p);
    }

    void addParagraph(String paragraphText){
        this.paragraphs.add(new Paragraph(paragraphText));
    }

    void writeHTML(PrintStream out){
        out.printf("<section> \n");
        out.printf("<h1> %s </h1> \n", this.title);
        for (int i=0; i<paragraphs.size(); i++){
            this.paragraphs.get(i).writeHTML(out);
        }
        out.printf("</section> \n");
    }
}
