import java.io.PrintStream;

public class ParagraphWithList extends Paragraph{
    UnorderedList list = new UnorderedList();

    ParagraphWithList(){}

    ParagraphWithList(String text){
        super.content = text;
    }

    void addItemToList(ListItem element){
        this.list.addItem(element);
    }
    void setContent(String newText){
        this.content = newText;
    }

    void writeHTML(PrintStream out){
        super.writeHTML(out);
        this.list.writeHTML(out);
    }

}
