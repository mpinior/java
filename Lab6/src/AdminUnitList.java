import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminUnitList{
    List<AdminUnit> units = new ArrayList<>();
    Map<Long, AdminUnit> longAdminUnitMap = new HashMap<>();
    Map<AdminUnit, Long> adminUnitLongMap = new HashMap<>();

    public void read(String filename) {
        CSVReader reader = new CSVReader(filename, ",", true);
        while(reader.next()){
            AdminUnit unit = new AdminUnit();
            longAdminUnitMap.put(reader.getLong("id"), unit);
            adminUnitLongMap.put(unit, reader.getLong("parent"));
            unit.name = reader.get("name");
            unit.adminLevel = !reader.isMissing("admin_level")?reader.getInt("admin_level"):0;
            unit.population = !reader.isMissing("population")?reader.getDouble("population") : 0;
            unit.area = !reader.isMissing("area")?reader.getDouble("area"):0;
            unit.density = !reader.isMissing("density")?reader.getDouble("density") : 0;
            units.add(unit);
        }
        for (int i = 0; i < units.size(); i++) {
            AdminUnit tmp = units.get(i);
            if(adminUnitLongMap.get(tmp) != null)tmp.parent=longAdminUnitMap.get(adminUnitLongMap.get(tmp));
            else tmp.parent=null;
            if(units.get(i).parent != null)units.get(i).parent.children.add(units.get(i));
        }
    }

    void list(PrintStream out){
        for (int i=0; i<units.size(); i++){
            out.println(units.get(i).toString());
        }
    }

    void list(PrintStream out,int offset, int limit ){
        for(int i=offset; i<limit; i++){
            out.println(units.get(i).toString());
        }
    }

    AdminUnitList selectByName(String pattern, boolean regex){
        AdminUnitList ret = new AdminUnitList();
        for (int i=0; i<units.size(); i++){
            if(regex){
                if (units.get(i).name.matches(pattern)) ret.units.add(this.units.get(i));

            }
            else{
                if (units.get(i).name.contains(pattern)) ret.units.add(this.units.get(i));
            }
        }
        return ret;
    }

    void fixMissingValues() {
        for (int i = 0; i < units.size(); i++) {
            if(units.get(i).density == 0 || units.get(i).population == 0) {
                fixMissingValues(units.get(i));
            }
        }
    }
    void fixMissingValues(AdminUnit au) {
        AdminUnit parent = longAdminUnitMap.get(adminUnitLongMap.get(au));
        if(au.density == 0 &&  parent != null) {
            if(parent.density == 0) {
                fixMissingValues(parent);
            }
            au.density = parent.density;
        }
        if(au.population == 0 && parent != null) {
            if(parent.population == 0) {
                fixMissingValues(parent);
            }
            au.population = au.area * au.density;
        }
    }
}
