import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ToDoApp extends JFrame implements ActionListener {

    private DefaultListModel<String> listModel;
    private JList<String> taskList;
    private JTextField taskInput;
    private JButton addButton, removeButton, clearButton;

    private java.util.List<String> tasks = new ArrayList<>();

    public ToDoApp() {
        setTitle(" To-Do List App");
        setSize(400, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create UI Components
        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        taskInput = new JTextField();
        addButton = new JButton("Add Task");
        removeButton = new JButton("Remove Selected");
        clearButton = new JButton("Clear All");

        // Panel for Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3, 5, 5));
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(clearButton);

        // Add ScrollPane for List
        JScrollPane scrollPane = new JScrollPane(taskList);

        // Layout Setup
        setLayout(new BorderLayout(10, 10));
        add(new JLabel("Enter a Task:"), BorderLayout.NORTH);
        add(taskInput, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.EAST);

        // Event Handling
        addButton.addActionListener(this);
        removeButton.addActionListener(this);
        clearButton.addActionListener(this);

        // Add pressing Enter key feature
        taskInput.addActionListener(e -> addTask());

        // Make visible
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            addTask();
        } else if (e.getSource() == removeButton) {
            removeTask();
        } else if (e.getSource() == clearButton) {
            clearTasks();
        }
    }

    private void addTask() {
        String task = taskInput.getText().trim();
        if (task.isEmpty()) {
            JOptionPane.showMessageDialog(this, " Please enter a task!");
            return;
        }
        listModel.addElement(task);
        tasks.add(task);
        taskInput.setText("");
    }

    private void removeTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            listModel.remove(selectedIndex);
            tasks.remove(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(this, " Please select a task to remove!");
        }
    }

    private void clearTasks() {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to clear all tasks?", 
                                                    "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            listModel.clear();
            tasks.clear();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ToDoApp::new);
    }
}
