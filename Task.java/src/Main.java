//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import javax.swing.*;
import java.awt.*;
import java.util.*;

class ask {
    String title, category;
    Date dueDate;
    boolean isCompleted;

    void Task(String title, String category, Date dueDate) {
        this.title = title;
        this.category = category;
        this.dueDate = dueDate;
        this.isCompleted = false;
    }
}

public class TodoApp extends JFrame {
    private DefaultListModel<String> model = new DefaultListModel<>();
    private JList<String> taskList = new JList<>(model);
    private java.util.List<Task> tasks = new ArrayList<>();

    public TodoApp() {
        setTitle("To-Do List");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        JTextField taskField = new JTextField(20);
        JTextField categoryField = new JTextField(10);
        JButton addButton = new JButton("Add Task");
        JButton completeButton = new JButton("Mark Done");

        panel.add(new JLabel("Task:"));
        panel.add(taskField);
        panel.add(new JLabel("Category:"));
        panel.add(categoryField);
        panel.add(addButton);
        panel.add(completeButton);
        add(panel, BorderLayout.NORTH);

        add(new JScrollPane(taskList), BorderLayout.CENTER);

        addButton.addActionListener(e -> {
            String taskText = taskField.getText();
            String category = categoryField.getText();
            Task task = new Task(taskText, category, new Date()); // Sample due date
            tasks.add(task);
            model.addElement(taskText + " [" + category + "]");
            taskField.setText("");
        });

        completeButton.addActionListener(e -> {
            int index = taskList.getSelectedIndex();
            if (index != -1) {
                tasks.get(index).isCompleted = true;
                model.set(index, "[✔] " + tasks.get(index).title);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TodoApp::new);
    }
}
