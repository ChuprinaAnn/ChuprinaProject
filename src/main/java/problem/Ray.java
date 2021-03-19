package problem;

import javax.media.opengl.GL2;
import java.util.Random;

public class Ray {
    Vector a;
    Vector b;
    Vector c;
    Vector d;

    Vector l;
    Vector r;

    double x;
    double y;

    public Ray(Vector a, Vector b) {
        this.a = a;
        this.b = b;
        l = new Vector(a, b);
        r = l.rotate();
        c = b.sum(r.norm().multiply(2));
        d = a.sum(r.norm().multiply(2));
    }

    public void render(GL2 gl) {
        Figures.renderQuad(gl, a.x, a.y, b.x, b.y, c.x, c.y, d.x, d.y, false, 2);
    }

    static Ray getRandomRay() {
        Random r = new Random();
        double x1 = r.nextDouble() * 2 - 1;
        double y1 = r.nextDouble() * 2 - 1;
        double x2 = r.nextDouble() * 2 - 1;
        double y2 = r.nextDouble() * 2 - 1;
        Vector v1 = new Vector(x1, y1);
        Vector v2 = new Vector(x2, y2);
        return new Ray(v1, v2);
    }
//    public boolean isInside (Vector v) {
//        double x = v.x; double y = v.y;
//        if
//    }
//}
}
