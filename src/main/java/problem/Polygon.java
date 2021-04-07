package problem;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Polygon {
    ArrayList<Vector> polygon_coordinates;

    Polygon(List<Vector> a) {
        polygon_coordinates = new ArrayList<>(a);
        Vector center = middlepoint(polygon_coordinates);
        // сортируем по углу
        polygon_coordinates.sort(Comparator.comparingDouble(aPoint -> aPoint.subtract(center).angle()));
    }

    public Vector middlepoint(ArrayList<Vector> polygon_coordinates) {
        Vector a = new Vector(0, 0);
        for (Vector polygon_coordinate : polygon_coordinates) {
            a = a.sum(polygon_coordinate);
        }
        return a.multiply(1.0 / polygon_coordinates.size());
    }

    public double getSquare() {
        if (polygon_coordinates.size() < 3)
            return 0;

        Vector t_t = middlepoint(polygon_coordinates);
        double S = 0;

        for (int i = 0; i < polygon_coordinates.size() - 1; i++) {
            Triangle a = new Triangle(t_t, polygon_coordinates.get(i), polygon_coordinates.get(i + 1));
            double s = a.SquareTriangle();
            S += s;
        }
        return S;
    }


    public void render(GL2 gl) {
        gl.glBegin(GL.GL_LINE_LOOP);
        for (Vector b : polygon_coordinates) {
            gl.glVertex2d(b.x, b.y);
//            //Vector a = middlepoint(polygon_coordinates);
//            Vector c = polygon_coordinates.get(i+1);
//            Triangle w = new Triangle (a,b,c);
//            Figures.renderTriangle(gl, a.x, a.y, b.x, b.y, c.x, c.y, false, 5);
        }
        gl.glEnd();
    }

    // Define Infinite (Using INT_MAX
    // caused overflow problems)
    static int INF = 10000;

    // Given three colinear points p, q, r,
    // the function checks if point q lies
    // on line segment 'pr'
    static boolean onSegment(Vector p, Vector q, Vector r) {
        if (q.x <= Math.max(p.x, r.x) &&
                q.x >= Math.min(p.x, r.x) &&
                q.y <= Math.max(p.y, r.y) &&
                q.y >= Math.min(p.y, r.y)) {
            return true;
        }
        return false;
    }

    // To find orientation of ordered triplet (p, q, r).
    // The function returns following values
    // 0 --> p, q and r are colinear
    // 1 --> Clockwise
    // 2 --> Counterclockwise
    static int orientation(Vector p, Vector q, Vector r) {
        double val = (q.y - p.y) * (r.x - q.x)
                - (q.x - p.x) * (r.y - q.y);

        if (Math.abs(val) <= 0.001) {
            return 0; // colinear
        }
        return (val > 0) ? 1 : 2; // clock or counterclock wise
    }

    // The function that returns true if
    // line segment 'p1q1' and 'p2q2' intersect.
    static boolean doIntersect(Vector p1, Vector q1,
                               Vector p2, Vector q2) {
        // Find the four orientations needed for
        // general and special cases
        int o1 = orientation(p1, q1, p2);
        int o2 = orientation(p1, q1, q2);
        int o3 = orientation(p2, q2, p1);
        int o4 = orientation(p2, q2, q1);

        // General case
        if (o1 != o2 && o3 != o4) {
            return true;
        }

        // Special Cases
        // p1, q1 and p2 are colinear and
        // p2 lies on segment p1q1
        if (o1 == 0 && onSegment(p1, p2, q1)) {
            return true;
        }

        // p1, q1 and p2 are colinear and
        // q2 lies on segment p1q1
        if (o2 == 0 && onSegment(p1, q2, q1)) {
            return true;
        }

        // p2, q2 and p1 are colinear and
        // p1 lies on segment p2q2
        if (o3 == 0 && onSegment(p2, p1, q2)) {
            return true;
        }

        // p2, q2 and q1 are colinear and
        // q1 lies on segment p2q2
        if (o4 == 0 && onSegment(p2, q1, q2)) {
            return true;
        }

        // Doesn't fall in any of the above cases
        return false;
    }

    // Returns true if the point p lies
    // inside the polygon[] with n vertices
    boolean isInside(Vector p) {
        // There must be at least 3 vertices in polygon[]
        if (polygon_coordinates.size() < 3) {
            System.out.println("list too small");
            return false;
        }

        // Create a point for line segment from p to infinite
        Vector extreme = new Vector(INF, p.y);

        // Count intersections of the above line
        // with sides of polygon
        int count = 0, i = 0;
        do {
            int next = (i + 1) % polygon_coordinates.size();

            // Check if the line segment from 'p' to
            // 'extreme' intersects with the line
            // segment from 'polygon[i]' to 'polygon[next]'
            if (doIntersect(polygon_coordinates.get(i), polygon_coordinates.get(next), p, extreme)) {
                // If the point 'p' is colinear with line
                // segment 'i-next', then check if it lies
                // on segment. If it lies, return true, otherwise false
                if (orientation(polygon_coordinates.get(i), p, polygon_coordinates.get(next)) == 0) {
                    return onSegment(polygon_coordinates.get(i), p, polygon_coordinates.get(next));
                }

                count++;
            }
            i = next;
        } while (i != 0);

        // Return true if count is odd, false otherwise
        return (count % 2 == 1); // Same as (count%2 == 1)
    }
}

