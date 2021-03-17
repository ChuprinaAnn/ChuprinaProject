package problem;

import sun.font.FontRunIterator;

import javax.media.opengl.GL2;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Класс задачи
 */
public class Problem {
    /**
     * текст задачи
     */
    public static final String PROBLEM_TEXT = "ПОСТАНОВКА ЗАДАЧИ:\n" +
            "Заданы два множества: множество широких лучей " +
            "и множество \n треугольников. " +
            "Требуется построить пересечения этих множеств \n" +
            "и выбрать пересечение максимальной площади";

    /**
     * заголовок окна
     */
    public static final String PROBLEM_CAPTION = "Итоговый проект ученицы 10-7 класса Чуприной Анны";

    /**
     * путь к файлу
     */
    private static final String FILE_NAME = "points.txt";

    /**
     * список точек
     */
    private final ArrayList<Triangle> triangles;
    private final ArrayList<Ray> rays;

    /**
     * Конструктор класса задачи
     */
    public Problem() {
        triangles = new ArrayList<>();
        rays = new ArrayList<>();
    }

    /**
     * Добавить точку
     *
     * @param x координата X точки
     * @param y координата Y точки
     */
    public void addTriangle(double x, double y, double x2, double y2, double x3, double y3) {
        Triangle triangle = new Triangle(new Vector(x, y), new Vector(x2, y2), new Vector(x3, y3));
        triangles.add(triangle);
    }

    /**
     * Добавить точку
     *
     * @param x      координата X точки
     * @param y      координата Y точки
     */
    public void addRay(double x, double y, double x2, double y2) {
        Ray ray = new Ray(new Vector(x,y),new Vector(x2,y2));
        rays.add(ray);
    }

    /**
     * Решить задачу
     */
    public void solve() {
        // перебираем пары точек
        for (Triangle p : triangles) {
        Line
            for (Ray r : rays) {

            }
        }
    }

    /**
     * Загрузить задачу из файла
     */
    public void loadFromFile() {
        triangles.clear();
        try {
            File file = new File(FILE_NAME);
            Scanner sc = new Scanner(file);
            int r = sc.nextInt();
            for (int i = 0; i < r; i++) {
                double x1 = sc.nextDouble();
                double y1 = sc.nextDouble();
                Vector v1 = new Vector(x1, y1);
                double x2 = sc.nextDouble();
                double y2 = sc.nextDouble();
                Vector v2 = new Vector (x2, y2);
                sc.nextLine();
                Ray ray = new Ray(v1, v2);
                rays.add(ray);
            }
            int t = sc.nextInt();
            for (int j = 0; j <t ; j++) {
                double x1 = sc.nextDouble();
                double y1 = sc.nextDouble();
                Vector v1 = new Vector(x1, y1);
                double x2 = sc.nextDouble();
                double y2 = sc.nextDouble();
                Vector v2 = new Vector (x2, y2);
                double x3 = sc.nextDouble();
                double y3 = sc.nextDouble();
                Vector v3 = new Vector (x2, y2);
                sc.nextLine();
                Triangle triangle = new Triangle(v1, v2, v3);
                triangles.add(triangle);
            }

        } catch (Exception ex) {
            System.out.println("Ошибка чтения из файла: " + ex);
        }
    }

    /**
     * Сохранить задачу в файл
     */
    public void saveToFile() {
        try {
            PrintWriter out = new PrintWriter(new FileWriter(FILE_NAME));
            for (Triangle triangle : triangles) {
                out.printf("%.2f %.2f %d\n", triangle.a.x, triangle.a.y, triangle.b.x, triangle.b.y, triangle.c.x, triangle.c.y);
            }
            for (Ray ray : rays) {
                    out.printf("%.2f %.2f %d\n", ray.a.x, ray.a.y, ray.b.x, ray.b.y);
            }
            out.close();
        } catch (IOException ex) {
            System.out.println("Ошибка записи в файл: " + ex);
        }
    }

    /**
     * Добавить заданное число случайных точек
     *
     * @param n кол-во точек
     */
    public void addRandomRays(int n) {
        for (int i = 0; i < n; i++) {
            Ray r = Ray.getRandomRay();
            rays.add(r);
        }
    }

    /**
     * Добавить заданное число случайных точек
     *
     * @param n кол-во точек
     */
    public void addRandomTriangles(int n) {
        for (int i = 0; i < n; i++) {
            Triangle t = Triangle.getRandomTriangle();
            triangles.add(t);
        }
    }

    /**
     * Очистить задачу
     */
    public void clear() {
        triangles.clear();
        rays.clear();
    }

    /**
     * Нарисовать задачу
     *
     * @param gl переменная OpenGL для рисования
     */
    public void render(GL2 gl) {
        gl.glColor3d(0.4, 0.1, 0.3);
        for (Triangle triangle : triangles) {
            triangle.render(gl);
        }
        gl.glColor3d(0.1, 0.7, 0.3);
        for (Ray ray : rays) {
            ray.render(gl);
        }
    }

}
