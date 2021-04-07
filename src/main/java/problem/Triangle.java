package problem;

import javax.media.opengl.GL2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Triangle {
    Vector a;
    Vector b;
    Vector c;

    public Triangle(Vector a, Vector b, Vector c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public void render(GL2 gl) {
        Figures.renderTriangle(gl, a.x, a.y, b.x, b.y, c.x, c.y, false, 2);

    }

    public List<Vector> getTrianglePoints() {
        List<Vector> lst = new ArrayList<Vector>();
        lst.add(a);
        lst.add(b);
        lst.add(c);
        return lst;
    }

    static Triangle getRandomTriangle() {
        Random r = new Random();
        double x1 = r.nextDouble() * 2 - 1;
        double y1 = r.nextDouble() * 2 - 1;
        double x2 = r.nextDouble() * 2 - 1;
        double y2 = r.nextDouble() * 2 - 1;
        double x3 = r.nextDouble() * 2 - 1;
        double y3 = r.nextDouble() * 2 - 1;
        Vector v1 = new Vector(x1, y1);
        Vector v2 = new Vector(x2, y2);
        Vector v3 = new Vector(x3, y3);
        return new Triangle(v1, v2, v3);
    }

    public double SquareTriangle() {
        return Math.abs(1.0 / 2 * (a.x - c.x) * (b.y - c.y) - 1.0 / 2 * (b.x - c.x) * (a.y - c.y));
    }
}
