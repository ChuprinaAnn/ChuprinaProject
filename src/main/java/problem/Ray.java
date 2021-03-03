package problem;

import javax.media.opengl.GL2;

import static javax.media.opengl.GL.GL_LINES;
import static javax.media.opengl.GL2GL3.GL_QUADS;

public class Ray {
  Vector l;
  Vector r;
  Vector c;
  Vector d;
  Vector a;
  Vector b;
  double x;
  double y;
  public Ray(Vector a, Vector b) {
    this.a = a;
    this.b = b;
    l= new Vector(a,b);
    r = l.rotate();
    c = b.sum(r.norm().multiply(2));
    d = a.sum(r.norm().multiply(2));
  }
  public void render(GL2 gl) {
    gl.glBegin(GL_QUADS);

    gl.glColor3d(1, 0, 1);
    gl.glVertex2d(a.x, a.y);

    gl.glColor3d(1, 1, 1);
    gl.glVertex2d(b.x, b.y);

    gl.glColor3d(0, 0, 1);
    gl.glVertex2d(c.x, c.y);

    gl.glColor3d(0, 1, 1);
    gl.glVertex2d(d.x, d.y);
    gl.glEnd();
  }
}
