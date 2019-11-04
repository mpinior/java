import java.io.PrintStream;

public class Paragraph {
    String content;

    Paragraph(){}

    Paragraph(String tresc){
        this.content = tresc;
    }

    void setContent(String newContent){
        this.content = newContent;
    }

    void writeHTML(PrintStream out){
        out.printf("<p> %s </p>\n", this.content);
    }
}
