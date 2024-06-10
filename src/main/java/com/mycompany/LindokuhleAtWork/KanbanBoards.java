package com.mycompany.LindokuhleAtWork;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Lindokuhle Zwane
 */
public class KanbanBoards {

    // ArrayLists to store task details
    private static final ArrayList<String> developers = new ArrayList<>();
    private static final ArrayList<String> taskNames = new ArrayList<>();
    private static final ArrayList<String> taskIDs = new ArrayList<>();
    private static final ArrayList<Integer> taskDurations = new ArrayList<>();
    private static final ArrayList<String> taskStatuses = new ArrayList<>();

    public static void main(String[] args) {
        CreateAccount objCreateAccount = new CreateAccount();

        // Initialize task variables
        String allTaskDetails = "";
        int totalHours = 0;

        // Register a new user
        String newName = JOptionPane.showInputDialog(null, "Enter your first name", "Registration", JOptionPane.QUESTION_MESSAGE);
        if (newName == null) {
            return;
        }

        String newSurname = JOptionPane.showInputDialog(null, "Enter your last name", "Registration", JOptionPane.QUESTION_MESSAGE);
        if (newSurname == null) {
            return;
        }

        String newUsername = JOptionPane.showInputDialog(null, "Enter a username", "Registration", JOptionPane.QUESTION_MESSAGE);
        if (newUsername == null) {
            return;
        }

        String newPassword = JOptionPane.showInputDialog(null, "Enter a password", "Registration", JOptionPane.QUESTION_MESSAGE);
        if (newPassword == null) {
            return;
        }

        // Register the user and display the result
        String registrationResult = objCreateAccount.registerUser(newName, newSurname, newUsername, newPassword);
        JOptionPane.showMessageDialog(null, registrationResult, "Registration", JOptionPane.INFORMATION_MESSAGE);
        if (!registrationResult.equals("Registration successful!")) {
            return;
        }

        // Login
        String inputUsername = JOptionPane.showInputDialog(null, "Enter your username", "Login", JOptionPane.QUESTION_MESSAGE);
        if (inputUsername == null) {
            return;
        }

        String inputPassword = JOptionPane.showInputDialog(null, "Enter your password", "Login", JOptionPane.QUESTION_MESSAGE);
        if (inputPassword == null) {
            return;
        }

        // Check login status and display the result
        String loginStatus = objCreateAccount.returnLoginStatus(inputUsername, inputPassword);
        JOptionPane.showMessageDialog(null, loginStatus, "Login", JOptionPane.INFORMATION_MESSAGE);
        if (!loginStatus.startsWith("Successful")) {
            return;
        }

        // Welcome message
        JOptionPane.showMessageDialog(null, "Welcome to EasyKanban", "Welcome", JOptionPane.INFORMATION_MESSAGE);

        // Main loop
        boolean quit = false;
        while (!quit) {
            // Display menu options
            String menuOption = JOptionPane.showInputDialog(null, "Select an option:\n1) Add tasks\n2) Show report\n3) Search tasks\n4) Delete task\n5) Quit", "Menu", JOptionPane.QUESTION_MESSAGE);
            if (menuOption == null) {
                return;
            }

            switch (menuOption) {
                case "1" -> {
                    // Add tasks
                    int numberOfTasks = 0;
                    try {
                        numberOfTasks = Integer.parseInt(JOptionPane.showInputDialog(null, "How many tasks would you like to enter?", "Task Entry", JOptionPane.QUESTION_MESSAGE));
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                        continue;
                    }

                    for (int k = 0; k < numberOfTasks; k++) {
                        String taskName = JOptionPane.showInputDialog(null, "Enter the task name:", "Task Entry", JOptionPane.QUESTION_MESSAGE);
                        String taskDescription = JOptionPane.showInputDialog(null, "Enter the task description (50 characters max):", "Task Entry", JOptionPane.QUESTION_MESSAGE);
                        if (taskDescription.length() > 50) {
                            JOptionPane.showMessageDialog(null, "Please enter a task description of less than 50 characters", "Error", JOptionPane.ERROR_MESSAGE);
                            k--;
                            continue;
                        }

                        String developerDetails = JOptionPane.showInputDialog(null, "Enter the developer details:", "Task Entry", JOptionPane.QUESTION_MESSAGE);
                        int taskDuration = 0;
                        try {
                            taskDuration = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the task duration (in hours):", "Task Entry", JOptionPane.QUESTION_MESSAGE));
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Please enter a valid number of hours.", "Error", JOptionPane.ERROR_MESSAGE);
                            k--;
                            continue;
                        }

                        String taskStatus = (String) JOptionPane.showInputDialog(null, "Select the task status:", "Task Entry", JOptionPane.QUESTION_MESSAGE, null, new String[]{"To Do", "Done", "Doing"}, "To Do");

                        // Create new task and add to lists
                        Tasks newTask = new Tasks(taskName, taskDescription, developerDetails, taskDuration, taskStatus);
                        developers.add(developerDetails);
                        taskNames.add(taskName);
                        taskIDs.add(newTask.getTaskID());
                        taskDurations.add(taskDuration);
                        taskStatuses.add(taskStatus);
                        totalHours += taskDuration;
                        allTaskDetails += newTask.printTaskDetails() + "\n\n";
                        JOptionPane.showMessageDialog(null, "Task successfully captured\n" + newTask.printTaskDetails(), "Task Entry", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                case "2" ->
                    displayReport();  // Show report of all tasks
                case "3" ->
                    searchTasks();  // Search tasks by name or developer
                case "4" ->
                    deleteTask();  // Delete a task by name
                case "5" ->
                    quit = true;  // Quit the application
                default ->
                    JOptionPane.showMessageDialog(null, "Invalid option. Please select 1, 2, 3, 4, or 5.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Display total hours and task details summary
        JOptionPane.showMessageDialog(null, "Total task hours: " + totalHours + "\n\nTask Details:\n" + allTaskDetails, "Summary", JOptionPane.INFORMATION_MESSAGE);
    }

    // Displays a report of all tasks.
    private static void displayReport() {
        StringBuilder report = new StringBuilder();
        for (int i = 0; i < taskNames.size(); i++) {
            report.append("Task Name: ").append(taskNames.get(i)).append("\n")
                    .append("Developer: ").append(developers.get(i)).append("\n")
                    .append("Task ID: ").append(taskIDs.get(i)).append("\n")
                    .append("Duration: ").append(taskDurations.get(i)).append(" hours\n")
                    .append("Status: ").append(taskStatuses.get(i)).append("\n\n");
        }
        JOptionPane.showMessageDialog(null, report.toString(), "Task Report", JOptionPane.INFORMATION_MESSAGE);
    }

    // Displays options for searching tasks by name or developer.
    private static void searchTasks() {
        String searchOption = JOptionPane.showInputDialog(null, "Search by:\n1) Task Name\n2) Developer", "Search Tasks", JOptionPane.QUESTION_MESSAGE);
        if (searchOption == null) {
            return;
        }

        switch (searchOption) {
            case "1" -> {
                // Search by task name
                String taskName = JOptionPane.showInputDialog(null, "Enter the task name to search for:", "Search Tasks", JOptionPane.QUESTION_MESSAGE);
                if (taskName == null) {
                    return;
                }
                searchTaskByName(taskName);
            }
            case "2" -> {
                // Search by developer name
                String developer = JOptionPane.showInputDialog(null, "Enter the developer name to search for:", "Search Tasks", JOptionPane.QUESTION_MESSAGE);
                if (developer == null) {
                    return;
                }
                searchTasksByDeveloper(developer);
            }
            default ->
                JOptionPane.showMessageDialog(null, "Invalid option. Please select 1 or 2.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Searches for a task by its name and displays the result.

     * @param taskName the name of the task to search for
     */
    private static void searchTaskByName(String taskName) {
        for (int i = 0; i < taskNames.size(); i++) {
            if (taskNames.get(i).equalsIgnoreCase(taskName)) {
                JOptionPane.showMessageDialog(null, "Task Name: " + taskNames.get(i) + "\nDeveloper: " + developers.get(i) + "\nStatus: " + taskStatuses.get(i), "Search Result", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Task not found.", "Search Result", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Searches for tasks assigned to a specific developer and displays the
     * result.
     *
     * @param developer the name of the developer to search for
     */
    private static void searchTasksByDeveloper(String developer) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < developers.size(); i++) {
            if (developers.get(i).equalsIgnoreCase(developer)) {
                result.append("Task Name: ").append(taskNames.get(i)).append("\nStatus: ").append(taskStatuses.get(i)).append("\n\n");
            }
        }
        if (result.length() == 0) {
            JOptionPane.showMessageDialog(null, "No tasks found for the specified developer.", "Search Result", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, result.toString(), "Search Result", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Deletes a task by its name.
    private static void deleteTask() {
        String taskName = JOptionPane.showInputDialog(null, "Enter the task name to delete:", "Delete Task", JOptionPane.QUESTION_MESSAGE);
        if (taskName == null) {
            return;
        }

        for (int i = 0; i < taskNames.size(); i++) {
            if (taskNames.get(i).equalsIgnoreCase(taskName)) {
                developers.remove(i);
                taskNames.remove(i);
                taskIDs.remove(i);
                taskDurations.remove(i);
                taskStatuses.remove(i);
                JOptionPane.showMessageDialog(null, "Task deleted successfully.", "Delete Task", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Task not found.", "Delete Task", JOptionPane.INFORMATION_MESSAGE);
    }
}
