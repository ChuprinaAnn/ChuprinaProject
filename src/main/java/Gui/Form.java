package Gui;

import problem.Problem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Класс формы приложения
 */
public class Form extends JFrame {
    /**
     * панель для отображения OpenGL
     */
    private JPanel GLPlaceholder;
    private JPanel root;
    private JTextField x1TriangleField;
    private JTextField x2TriangleField;
    private JButton randomRayBtn;
    private JTextField rayCntField;
    private JButton loadFromFileBtn;
    private JButton saveToFileBtn;
    private JButton clearBtn;
    private JButton solveBtn;
    private JLabel problemText;
    private JTextField triangleCntField;
    private JButton randomTriangleButton;
    private JTextField y1TriangleField;
    private JTextField y2TriangleField;
    private JTextField x3TriangleField;
    private JTextField y3TriangleField;
    private JButton addTriagnlebtn;
    private JButton addRayBtn;
    private JTextField x1RayField;
    private JTextField y1RayField;
    private JTextField x2RayField;
    private JTextField y2RayField;
    private JLabel X;
    /**
     * таймер
     */
    private final Timer timer;
    /**
     * рисовалка OpenGL
     */
    private final RendererGL renderer;

    /**
     * Конструктор формы
     */
    private Form() {
        super(Problem.PROBLEM_CAPTION);
        // инициализируем OpenGL
        renderer = new RendererGL();
        // связываем элемент на форме с рисовалкой OpenGL
        GLPlaceholder.setLayout(new BorderLayout());
        GLPlaceholder.add(renderer.getCanvas());
        // указываем главный элемент формы
        getContentPane().add(root);
        // задаём размер формы
        setSize(getPreferredSize());
        // показываем форму
        setVisible(true);
        // обработчик зарытия формы
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new Thread(() -> {
                    renderer.close();
                    timer.stop();
                    System.exit(0);
                }).start();
            }
        });
        // тинициализация таймера, срабатывающего раз в 100 мсек
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onTime();
            }
        });
        timer.start();
        initWidgets();
    }

    /**
     * Инициализация виджетов
     */
    private void initWidgets() {
        // задаём текст полю описания задачи
        problemText.setText("<html>" + Problem.PROBLEM_TEXT.replaceAll("\n", "<br>"));

        addTriagnlebtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double x1 = Double.parseDouble(x1TriangleField.getText());
                double y1 = Double.parseDouble(y1TriangleField.getText());
                double x2 = Double.parseDouble(x2TriangleField.getText());
                double y2 = Double.parseDouble(y2TriangleField.getText());
                double x3 = Double.parseDouble(x3TriangleField.getText());
                double y3 = Double.parseDouble(y3TriangleField.getText());
                renderer.problem.addTriangle(x1, y1, x2, y2, x3, y3);
            }
        });
        addRayBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double x1 = Double.parseDouble(x1RayField.getText());
                double y1 = Double.parseDouble(y1RayField.getText());
                double x2 = Double.parseDouble(x2RayField.getText());
                double y2 = Double.parseDouble(y2RayField.getText());
                renderer.problem.addRay(x1, y1, x2, y2);
            }
        });
        randomRayBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                renderer.problem.addRandomRays(Integer.parseInt(rayCntField.getText()));
            }
        });
        randomTriangleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                renderer.problem.addRandomTriangles(Integer.parseInt(triangleCntField.getText()));
            }
        });
        loadFromFileBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                renderer.problem.loadFromFile();
            }
        });
        saveToFileBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                renderer.problem.saveToFile();
            }
        });
        clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                renderer.problem.clear();
            }
        });
        solveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                renderer.problem.solve();
            }
        });
    }

    /**
     * Событие таймера
     */
    private void onTime() {
        // события по таймеру
    }

    /**
     * Главный метод
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        new Form();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
