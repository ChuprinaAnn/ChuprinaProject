package problem;

public class Line {
    double A;
    double B;
    double C;

    Vector pointA;
    Vector pointB;

    public double getA() {
        return A;
    }

    public double getB() {
        return B;
    }

    public double getC() {
        return C;
    }

    public boolean isParallel(Line line) {
        return Math.abs(A * line.getB() - (line.getA() * B)) < 0.001;
    }

    Line(double x, double y, double z) {
        A = x;
        B = y;
        C = z;
    }

    Line(double x1, double y1, double x2, double y2) {
        A = y1 - y2;
        B = x2 - x1;
        C = x1 * y2 - x2 * y1;
        pointA = new Vector(x1, y1);
        pointB = new Vector(x2, y2);
    }

    public Vector intersection(Line t) {
        if (isParallel(t))
            return null;
        double a1 = (C / B - t.C / t.B) / (t.A / t.B - A / B);
        double b1 = (-A / B) * a1 - C / B;
        //String S=String.format("(%.2f, %.2f",a1,b1);
        return new Vector(a1, b1);
    }

}
