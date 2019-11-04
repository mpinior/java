import java.io.PrintStream;

public class ParagraphWithList extends Paragraph{
    UnorderedList list;

    ParagraphWithList(){}

    ParagraphWithList(String text, UnorderedList lista){
        this.content = text;
        this.list = lista;
    }

    void setContent(String newText, UnorderedList newList){
        this.content = newText;
        this.list = newList;
    }

    void writeHTML(PrintStream out){
        super.writeHTML(out);
        this.list.writeHTML(out);
    }

}
