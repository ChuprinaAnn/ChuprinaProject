package problem;

import javax.media.opengl.GL2;

import static javax.media.opengl.GL.GL_TRIANGLES;

public class Triangle {
    double ax;
    double ay;
    double bx;
    double by;
    double cx;
    double cy;
    public Triangle( double ax, double ay, double bx, double by, double cx, double cy) {
        this.ax = ax;
        this.ay = ay;
        this.bx = bx;
        this.by = by;
        this.cx = cx;
        this.cy = cy;
    }
    public void render(GL2 gl) {
        gl.glBegin(GL_TRIANGLES);
        gl.glVertex2d(ax, ay);
        gl.glColor3d(1, 0, 1);
        gl.glVertex2d(bx, by);
        gl.glColor3d(1, 1, 1);
        gl.glVertex2d(cx, cy);
        gl.glColor3d(0, 1, 1);
        gl.glEnd();

    }

}
