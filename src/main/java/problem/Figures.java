package problem;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GL2GL3;

import static javax.media.opengl.GL.*;
import static javax.media.opengl.GL2GL3.GL_QUADS;

public class Figures {
    public static void renderLine(GL2 gl, double aX, double aY, double bX, double bY, int width) {
        gl.glPointSize(width);
        gl.glBegin(GL_LINES);
        gl.glVertex2d(aX, aY);
        gl.glColor3d(1, 0, 1);
        gl.glVertex2d(bX, bY);
        gl.glColor3d(1, 1, 1);
        gl.glEnd();
    }

    public static void renderPoint(GL2 gl, double posX, double posY, int size) {
        gl.glPointSize(size);
        gl.glBegin(GL_POINTS);
        gl.glVertex2d(posX, posY);
        gl.glColor3d(1, 0, 1);
        gl.glEnd();
    }

    public static void renderTriangle(GL2 gl, double aX, double aY, double bX, double bY, double cX, double cY, boolean filled, int width) {
        if (filled) {
            gl.glPointSize(width);
            gl.glBegin(GL_TRIANGLES);
            gl.glVertex2d(aX, aY);
            gl.glColor3d(1, 0, 1);
            gl.glVertex2d(bX, bY);
            gl.glColor3d(1, 1, 1);
            gl.glVertex2d(cY, cX);
            gl.glColor3d(0, 1, 1);
            gl.glEnd();
        } else {
            gl.glPointSize(width);
            gl.glBegin(GL_LINE_STRIP);
            gl.glVertex2d(aX, aY);
            gl.glColor3d(1, 0, 1);
            gl.glVertex2d(bX, bY);
            gl.glColor3d(1, 1, 1);
            gl.glVertex2d(cY, cX);
            gl.glColor3d(0, 1, 1);
            gl.glVertex2d(aX, aY);
            gl.glColor3d(1, 0, 1);
            gl.glEnd();
        }
    }
    public static void renderQuad(GL2 gl, double aX, double aY, double bX, double bY, double cX, double cY, double dX, double dY, boolean filled, int width) {
        if (filled) {
            gl.glPointSize(width);
            gl.glBegin(GL_QUADS);
            gl.glColor3d(0, 1, 1);
            gl.glVertex2d(aX, aY);

            gl.glColor3d(1, 0, 1);
            gl.glVertex2d(bX, bY);

            gl.glColor3d(1, 1, 1);
            gl.glVertex2d(cX, cY);

            gl.glColor3d(0, 0, 1);
            gl.glVertex2d(dX, dY);

            gl.glEnd();
        } else {
            gl.glPointSize(width);
            gl.glBegin(GL_LINE_STRIP);
            gl.glVertex2d(aX, aY);
            gl.glColor3d(1, 0, 1);
            gl.glVertex2d(bX, bY);
            gl.glColor3d(1, 1, 1);
            gl.glVertex2d(cX, cY);
            gl.glColor3d(0, 1, 1);
            gl.glVertex2d(dX, dY);
            gl.glColor3d(0, 1 ,0);
            gl.glVertex2d(aX, aY);
            gl.glColor3d(1, 0, 1);
            gl.glEnd();
        }
    }

}
