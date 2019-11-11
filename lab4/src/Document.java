import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Document {
    String title;
    Photo photo;
    List<Section> sections = new ArrayList<>();

    Document(String title){
        this.title = title;
    }

    void setTitle(String title){
        this.title = title;
    }

    void setPhoto(String photoUrl){
        this.photo = new Photo(photoUrl);
    }

    void addSection(String sectionTitle){
        this.sections.add(new Section(sectionTitle));
    }

    void addSection(Section s){
        this.sections.add(s);
    }

    void writeHTML(PrintStream out){
        out.printf("<h1> %s <h1>\n", this.title);
        this.photo.writeHTML(out);
        for (int i=0; i<sections.size(); i++) {
            this.sections.get(i).writeHTML(out);
        }
    }
}
