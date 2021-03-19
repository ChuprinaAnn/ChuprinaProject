package problem;

import java.util.ArrayList;

public class Polygon {
    ArrayList<Vector> polygon_coordinates = new ArrayList<>();

    public Vector middlepoint(ArrayList<Vector> polygon_coordinates) {
        Vector a = new Vector(0, 0);
        for (Vector polygon_coordinate : polygon_coordinates) {
            a = a.sum(polygon_coordinate);
        }
        return a.multiply(1.0 / polygon_coordinates.size());
    }


}
