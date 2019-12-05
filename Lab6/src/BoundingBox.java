public class BoundingBox {
    double xmin;
    double ymin;
    double xmax;
    double ymax;
    boolean empty=true;

    void addPoint(double x, double y){
        if(empty) {
            xmin=x;
            xmax=x;
            ymax=y;
            ymin=y;
            empty=false;
        } else {
            if(xmin>x)xmin=x;
            if(xmax<x)xmax=x;
            if(ymin>y)ymin=y;
            if(ymax<y)ymax=y;
        }
    }

    boolean contains(double x, double y){
        if (this.empty){
            return false;
        }
        if (this.xmin<=x && this.xmax>=x && this.ymin<=y && this.ymax>=y){
            return true;
        }
        return false;
    }

    boolean contains(BoundingBox bb){
        if(bb.empty || this.empty){
            return false;
        }
        if(this.xmin<=bb.xmin && this.xmax>=bb.xmax && this.ymin<=bb.ymin && this.ymax>=bb.ymax){
            return true;
        }
        return false;
    }

    boolean intersects(BoundingBox bb){
        if(this.empty || bb.empty){
            return false;
        }
        if(this.xmin>bb.xmax || this.xmax<bb.xmin){
            return false;
        }
        if(this.ymin>bb.ymax || this.ymax<bb.ymin){
            return false;
        }
        return true;
    }

    BoundingBox add(BoundingBox bb){
        if(this.contains(bb)){
            return this;
        }
        this.addPoint(bb.xmax, bb.ymax);
        this.addPoint(bb.xmin, bb.ymin);
        return this;
    }
    boolean isEmpty(){
        return empty;
    }

    double getCenterX(){
        if(!isEmpty()){
            return (xmin*xmax)/2;
        }
        else{
            throw new IllegalStateException("Bounding Box jest pusty!");
        }
    }

    double getCenterY(){
        if(!isEmpty()){
            return (ymin*ymax)/2;
        }
        else{
            throw new IllegalStateException("Bounding Box jest pusty!");
        }
    }

    double distanceTo(BoundingBox bb){
        if (isEmpty() || bb.isEmpty()) {
            throw new IllegalStateException("Pusty Bounding Box, nie mozna obliczyc dlugosci");
        }
        double centerX = this.getCenterX();
        double centerY = this.getCenterY();
        double bbcenterX = bb.getCenterX();
        double bbcenterY = bb.getCenterY();

        double distance = Math.sqrt((centerX - bbcenterX)*(centerX - bbcenterX) + (centerY - bbcenterY)*(centerY - bbcenterY));
        return distance;
    }

    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("BoundingBox[" + "xmin=" + xmin + ", xmax=" + xmax + ", ymin=" + ymin + ", ymax=" + ymax +']');
        return builder.toString();
    }
}
