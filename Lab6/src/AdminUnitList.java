import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class AdminUnitList{
    List<AdminUnit> units = new ArrayList<>();



    public void read(String filename) {
        CSVReader reader = new CSVReader("admin-units.csv", ",", true);
        while(reader.next()){
            AdminUnit unit = new AdminUnit();
            unit.name = reader.get("name");
            unit.adminLevel = reader.getInt("admin_level");
            unit.population = !reader.isMissing("population")?reader.getDouble("area") : 0;
            unit.area = reader.getInt("area");
            unit.density = !reader.isMissing("density")?reader.getDouble("area") : 0;
            units.add(unit);
        }
    }

    void list(PrintStream out){
        for (int i=0; i<units.size(); i++){
            out.print(units.get(i).toString());
        }
    }

    void list(PrintStream out,int offset, int limit ){
        for(int i=offset; i<limit; i++){
            out.print(units.get(i).toString());
        }
    }

    AdminUnitList selectByName(String pattern, boolean regex){
        AdminUnitList ret = new AdminUnitList();
        for (int i=0; i<units.size(); i++){
            if(regex){
                units.get(i).name.matches(pattern);
                ret.

            }
        }
        // przeiteruj po zawartości units
        // jeżeli nazwa jednostki pasuje do wzorca dodaj do ret
        return ret;
    }
}
