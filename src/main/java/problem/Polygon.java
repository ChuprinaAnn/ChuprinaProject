package problem;

import javax.media.opengl.GL2;
import java.util.ArrayList;

public class Polygon {
    ArrayList<Vector> polygon_coordinates;
    Polygon (ArrayList<Vector> a) {polygon_coordinates = a; }
    public Vector middlepoint(ArrayList<Vector> polygon_coordinates) {
        Vector a = new Vector(0, 0);
        for (Vector polygon_coordinate : polygon_coordinates) {
            a = a.sum(polygon_coordinate);
        }
        return a.multiply(1.0 / polygon_coordinates.size());
    }
   public boolean isInside (Vector a) {
       double r = 0;
       double n = 0;
        for(int i = 0; i<polygon_coordinates.size(); i++) {
           Vector k = middlepoint(polygon_coordinates);
           Triangle t = new Triangle(k, polygon_coordinates.get(i), polygon_coordinates.get(i+1));
           double s = t.SquareTriangle();
           r +=s;
       }
       for(int i = 0; i<polygon_coordinates.size(); i++) {
           Triangle t = new Triangle(a, polygon_coordinates.get(i), polygon_coordinates.get(i + 1));
           double s = t.SquareTriangle();
           n += s;
       }
       if (n==r) {
           return true; }
       else
           return false;
    }
    public void render (GL2 gl) {
        Polygon t = new Polygon(polygon_coordinates);
        for (int i = 0; i < polygon_coordinates.size(); i++) {
            Vector a = t.middlepoint(polygon_coordinates);
            Vector b = polygon_coordinates.get(i);
            Vector c = polygon_coordinates.get(i+1);
            Triangle w = new Triangle (a,b,c);
            Figures r = new Figures();
            r.renderTriangle(gl, a.x, a.y, b.x, b.y, c.x, c.y, true, 5);
        }
    }
}

