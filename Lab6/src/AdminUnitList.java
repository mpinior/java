import java.io.PrintStream;
import java.util.*;
import java.util.function.Predicate;

public class AdminUnitList{
    List<AdminUnit> units = new ArrayList<>();
    Map<Long, AdminUnit> longAdminUnitMap = new HashMap<>();
    Map<AdminUnit, Long> adminUnitLongMap = new HashMap<>();

    public void read(String filename) {
        CSVReader reader = new CSVReader(filename, ",", true);
        while(reader.next()){
            AdminUnit unit = new AdminUnit();
            longAdminUnitMap.put(reader.getLong("id"), unit);
            if (!reader.isMissing("parent")){
                adminUnitLongMap.put(unit, reader.getLong("parent"));
            }
            unit.name = reader.get("name");
            unit.adminLevel = !reader.isMissing("admin_level")?reader.getInt("admin_level"):0;
            unit.population = !reader.isMissing("population")?reader.getDouble("population") : 0;
            unit.area = !reader.isMissing("area")?reader.getDouble("area"):0;
            unit.density = !reader.isMissing("density")?reader.getDouble("density") : 0;
            double x1 = !reader.isMissing("x1")?reader.getDouble("x1"):0;
            double y1 = !reader.isMissing("y1")?reader.getDouble("y1"):0;
            double x2 = !reader.isMissing("x2")?reader.getDouble("x2"):0;
            double y2 = !reader.isMissing("y2")?reader.getDouble("y2"):0;
            double x3 = !reader.isMissing("x3")?reader.getDouble("x3"):0;
            double y3 = !reader.isMissing("y3")?reader.getDouble("y3"):0;
            double x4 = !reader.isMissing("x4")?reader.getDouble("x4"):0;
            double y4 = !reader.isMissing("y4")?reader.getDouble("y4"):0;
            unit.bbox.addPoint(x1,y1);
            unit.bbox.addPoint(x2,y2);
            unit.bbox.addPoint(x3,y3);
            unit.bbox.addPoint(x4,y4);
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

    AdminUnitList getNeighbors(AdminUnit unit, double maxdistance){
        AdminUnitList result = new AdminUnitList();
        for(int i=0; i<this.units.size(); i++){
            if(unit.name == units.get(i).name){
                continue;
            }
            if(unit.adminLevel==8 && this.units.get(i).adminLevel==8){
                if(this.units.get(i).bbox.distanceTo(unit.bbox) <= maxdistance){
                    result.units.add(this.units.get(i));
                }
                continue;
            }
            if(unit.adminLevel == this.units.get(i).adminLevel && this.units.get(i).bbox.intersects(unit.bbox)){
                result.units.add(this.units.get(i));
            }
        }
        return result;
    }


    AdminUnitList sortInplaceByName(){
        class SortByName implements Comparator<AdminUnit> {
            @Override
            public int compare(AdminUnit u1, AdminUnit u2) {
                return u1.name.compareTo(u2.name);
            }
        }
        AdminUnitList result = new AdminUnitList();
        units.sort(new SortByName());
        return this;
    }
    AdminUnitList sortInplaceByArea(){
        this.units.sort(new Comparator<AdminUnit>() {
            @Override
            public int compare(AdminUnit u1, AdminUnit u2) {
                return Double.compare(u1.area, u2.area);
            }
        });
        return this;
    }
    AdminUnitList sortInplaceByPopulation(){
        this.units.sort((x1,x2)->Double.compare(x1.population,x2.population));
        return this;
    }
    AdminUnitList sortInplace(Comparator<AdminUnit> cmp){
        this.units.sort(cmp);
        return this;
    }
    AdminUnitList sort(Comparator<AdminUnit> cmp){
        AdminUnitList result = new AdminUnitList();
        result.units = new ArrayList<AdminUnit>();
        for (int i = 0; i < this.units.size(); i++) {
            result.units.add(this.units.get(i));
        }
        return result.sortInplace(cmp);
    }
    AdminUnitList filter(Predicate<AdminUnit> pred, int limit){
        AdminUnitList result = new AdminUnitList();
        for (int i = 0; i < this.units.size() && result.units.size()<=limit; i++) {
            if(pred.test(this.units.get(i)))result.units.add(this.units.get(i));
        }
        return result;
    }
    AdminUnitList filter(Predicate<AdminUnit> pred) {
        return filter(pred, this.units.size());
    }
    AdminUnitList filter(Predicate<AdminUnit> pred, int offset, int limit) {
        AdminUnitList filter = this.filter(pred, limit);
        AdminUnitList result = new AdminUnitList();
        if (offset < 0 || offset > filter.units.size()) throw new RuntimeException("Offset beyond limits");
        for (int i = offset; i < filter.units.size(); i++) {
            result.units.add(filter.units.get(i));
        }
        return result;
    }
}
