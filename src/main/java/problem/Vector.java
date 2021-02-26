package problem;

public class Vector {
    double rx,ry;
    Vector(Point p1, Point p2) {
        rx = p2.x-p1.x;
        ry = p2.y-p1.y;
    }

    public Vector rotate(double a){
        double cs = Math.cos(a), sn = Math.sin(a);
        double rrx = rx*cs - ry*sn;
        double rry = rx*sn + ry*cs;
        return new Vector(new Point(0,0), new Point(rrx,rry));
    }

    public Vector rotate(){
        rotate(Math.PI/2);
    }


}
