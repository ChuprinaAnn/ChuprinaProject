package problem;

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
    private final ArrayList<Point> points;
    private final ArrayList<Triangle> triangles;
    private final ArrayList<Ray> rays;

    /**
     * Конструктор класса задачи
     */
    public Problem() {
        points = new ArrayList<>();
        triangles = new ArrayList<>();
        rays = new ArrayList<>();
    }

    /**
     * Добавить точку
     *
     * @param x      координата X точки
     * @param y      координата Y точки
     * @param setVal номер множества
     */
    public void addPoint(double x, double y, int setVal) {
        Point point = new Point(x, y, setVal);
        points.add(point);
    }

    /**
     * Решить задачу
     */
    public void solve() {
        // перебираем пары точек
        for (Point p : points) {
            for (Point p2 : points) {
                // если точки являются разными
                if (p != p2) {
                    // если координаты у них совпадают
                    if (Math.abs(p.x - p2.x) < 0.0001 && Math.abs(p.y - p2.y) < 0.0001) {
                        p.isSolution = true;
                        p2.isSolution = true;
                    }
                }
            }
        }
    }

    /**
     * Загрузить задачу из файла
     */
    public void loadFromFile() {
        points.clear();
        try {
            File file = new File(FILE_NAME);
            Scanner sc = new Scanner(file);
            // пока в файле есть непрочитанные строки
            while (sc.hasNextLine()) {
                double x = sc.nextDouble();
                double y = sc.nextDouble();
                int setVal = sc.nextInt();
                sc.nextLine();
                Point point = new Point(x, y, setVal);
                points.add(point);
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
            for (Point point : points) {
                out.printf("%.2f %.2f %d\n", point.x, point.y, point.setNumber);
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
        points.clear();
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
