import java.util.ArrayList;
import java.util.List;

public class AdminUnit {
    String name;
    int adminLevel;
    double population;
    double area;
    double density;
    AdminUnit parent;
    BoundingBox bbox = new BoundingBox();
    List<AdminUnit> children = new ArrayList<>();

    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append(name + ", ");
        builder.append(adminLevel+ ", ");
        builder.append(area+", ");
        if (population != 0) {
            builder.append(population + ", ");
            builder.append(density + ", ");
        }
        if (parent != null){
            builder.append(parent +", ");
        }
        builder.append(bbox);

        return builder.toString();
    }
}
