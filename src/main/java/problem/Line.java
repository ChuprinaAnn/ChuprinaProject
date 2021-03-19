package problem;

public class Line {
    double A;
    double B;
    double C;
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
    Line (double x1, double y1, double x2, double y2) {
        A = y1-y2;
        B = x2-x1;
        C = x1*y2 - x2*y1;
    }
    public Vector intersection (Line line) {
        if (isParallel(line)) return null;
        else {
            double a = line.getA(), b = line.getB(), c = line.getC();
            double X = (b * C / B - c) / (a - A * b / B);
            double Y = -(A * X + C) / B;
            return new Vector(X, Y);
        }
    }
}
