package ui;

import Task.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FrameMain extends JFrame {
    private final JTextArea inputArea = new JTextArea();
    private final JTextArea outputArea = new JTextArea();
    private final JLabel statusLabel = new JLabel("Введите отрезки и нажмите «Рассчитать»");

    public FrameMain() {
        setTitle("Task 4.13 — visual demo");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(900, 600));
        setLayout(new BorderLayout(10, 10));

        add(createTopPanel(), BorderLayout.NORTH);
        add(createCenterPanel(), BorderLayout.CENTER);
        add(createBottomPanel(), BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }

    private JPanel createTopPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 0, 10));

        JLabel title = new JLabel("Нахождение интервалов с максимальным покрытием");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 18f));

        JLabel hint = new JLabel(
                "<html>Формат ввода: пары чисел по одному отрезку в строке. "
                        + "Подойдут варианты вроде <b>(1, 5)</b>, <b>2 7</b>, <b>4; 6</b>.</html>"
        );

        panel.add(title, BorderLayout.NORTH);
        panel.add(hint, BorderLayout.CENTER);
        return panel;
    }

    private JComponent createCenterPanel() {
        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);
        inputArea.setText("""
                (1, 5)
                (2, 7)
                (4, 6)
                (8, 10)
                """);

        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);

        JScrollPane inputScroll = new JScrollPane(inputArea);
        JScrollPane outputScroll = new JScrollPane(outputArea);

        JPanel left = new JPanel(new BorderLayout(5, 5));
        left.add(new JLabel("Ввод"), BorderLayout.NORTH);
        left.add(inputScroll, BorderLayout.CENTER);

        JPanel right = new JPanel(new BorderLayout(5, 5));
        right.add(new JLabel("Результат"), BorderLayout.NORTH);
        right.add(outputScroll, BorderLayout.CENTER);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, left, right);
        splitPane.setResizeWeight(0.5);
        splitPane.setBorder(new EmptyBorder(0, 10, 0, 10));

        return splitPane;
    }

    private JPanel createBottomPanel() {
        JButton runButton = new JButton("Рассчитать");
        JButton clearButton = new JButton("Очистить");

        runButton.addActionListener(e -> calculate());
        clearButton.addActionListener(e -> {
            inputArea.setText("");
            outputArea.setText("");
            statusLabel.setText("Поля очищены");
        });

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttons.add(clearButton);
        buttons.add(runButton);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(0, 10, 10, 10));
        panel.add(statusLabel, BorderLayout.CENTER);
        panel.add(buttons, BorderLayout.EAST);
        return panel;
    }

    private void calculate() {
        try {
            ArrayList<Segment> segments = parseSegments(inputArea.getText());
            ArrayList<Segment> result = Solution.solve(segments);

            outputArea.setText(formatResult(result));
            statusLabel.setText("Готово. Введено отрезков: " + segments.size()
                    + ", найдено интервалов: " + result.size());
        } catch (Exception ex) {
            outputArea.setText("");
            statusLabel.setText("Ошибка ввода");
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private ArrayList<Segment> parseSegments(String text) {
        ArrayList<Integer> numbers = new ArrayList<>();
        Matcher matcher = Pattern.compile("-?\\d+").matcher(text);

        while (matcher.find()) {
            numbers.add(Integer.parseInt(matcher.group()));
        }

        if (numbers.isEmpty()) {
            throw new IllegalArgumentException("Не найдено ни одного числа.");
        }

        if (numbers.size() % 2 != 0) {
            throw new IllegalArgumentException("Нужно чётное количество чисел: по два на каждый отрезок.");
        }

        ArrayList<Segment> segments = new ArrayList<>();
        for (int i = 0; i < numbers.size(); i += 2) {
            segments.add(new Segment(numbers.get(i), numbers.get(i + 1)));
        }

        return segments;
    }

    private String formatResult(ArrayList<Segment> result) {
        if (result.isEmpty()) {
            return "Результат пуст.";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.size(); i++) {
            sb.append(i + 1).append(". ").append(result.get(i)).append('\n');
        }
        return sb.toString();
    }
}