package com.mycompany.LindokuhleAtWork;

import java.awt.HeadlessException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Lindokuhle Zwane
 */
public class KanbanBoards {

    // ArrayLists to store task details
    static final ArrayList<String> developers = new ArrayList<>();
    static final ArrayList<String> taskNames = new ArrayList<>();
    static final ArrayList<String> taskIDs = new ArrayList<>();
    static final ArrayList<Integer> taskDurations = new ArrayList<>();
    static final ArrayList<String> taskStatuses = new ArrayList<>();

    public static void main(String[] args) {
        CreateAccount objCreateAccount = new CreateAccount();
        runRegistrationAndLogin(objCreateAccount);
        displayMainMenu();
    }

    /**
     * Handles user registration and login process.
     *
     * @param objCreateAccount An instance of CreateAccount to handle user
     * registration.
     */
    private static void runRegistrationAndLogin(CreateAccount objCreateAccount) {
        // Register a new user
        String newName = promptInput("Enter your first name", "Registration");
        if (newName == null) {
            System.exit(0);
        }

        String newSurname = promptInput("Enter your last name", "Registration");
        if (newSurname == null) {
            System.exit(0);
        }

        String newUsername = promptInput("Enter a username", "Registration");
        if (newUsername == null) {
            System.exit(0);
        }

        String newPassword = CreateAccount.promptPasswordInput("Enter a password", "Registration");
        if (newPassword == null) {
            System.exit(0);
        }

        // Register the user and display the result
        String registrationResult = objCreateAccount.registerUser(newName, newSurname, newUsername, newPassword);
        showMessage(registrationResult, "Registration");
        if (!registrationResult.equals("Registration successful!")) {
            return;
        }

        // Login
        String inputUsername = promptInput("Enter your username", "Login");
        if (inputUsername == null) {
            System.exit(0);
        }

        String inputPassword = CreateAccount.promptPasswordInput("Enter your password", "Login");
        if (inputPassword == null) {
            System.exit(0);
        }

        // Check login status and display the result
        String loginStatus = objCreateAccount.returnLoginStatus(inputUsername, inputPassword);
        showMessage(loginStatus, "Login");
        if (!loginStatus.startsWith("Successful")) {
            return;
        }

        // Welcome message
        showMessage("Welcome to EasyKanban", "Welcome");
    }

    // Displays the main menu and handles user interaction based on menu options.
    private static void displayMainMenu() {
        boolean quit = false;
        while (!quit) {
            // Display main menu options
            String menuOption = promptInput("Select an option:\n1) Add tasks\n2) Show report\n3) Quit", "Menu");
            if (menuOption == null) {
                return;
            }

            switch (menuOption) {
                case "1" ->
                    addTasks(); // Add tasks
                case "2" ->
                    displayReport(); // Show report of all tasks
                case "3" ->
                    quit = true; // Quit the application
                default ->
                    showMessage("Invalid option. Please select 1, 2, or 3.", "Error");
            }
        }
    }

    // Displays a report menu with options to show different types of task reports.
    static void displayReport() {
        boolean backToMainMenu = false;
        while (!backToMainMenu) {
            // Display report menu options
            String reportOption = promptInput("Select an option:\n1) Display all tasks\n2) Display done tasks\n3) Display longest task\n4) Search tasks by name\n5) Search tasks by developer\n6) Delete task\n7) Go back", "Show Report");
            if (reportOption == null) {
                backToMainMenu = true;
                continue;
            }

            switch (reportOption) {
                case "1" ->
                    displayAllTasks(); // Show report of all tasks
                case "2" ->
                    displayDoneTasks(); // Show done task report
                case "3" ->
                    displayLongestTask(); // Show longest task report
                case "4" ->  {
                    String taskName = promptInput("Enter the task name to search for:", "Search Tasks");
                    if (taskName != null) {
                        String searchResult = searchTaskByName(taskName); // Search tasks by task name
                        showMessage(searchResult, "Search Results");
                    }
                }
                case "5" ->  {
                    String developer = promptInput("Enter the developer (name & surname) to search for:", "Search Tasks");
                    if (developer != null) {
                        String searchResult = searchTasksByDeveloper(developer); // Search tasks by developer name & surname
                        showMessage(searchResult, "Search Results");
                    }
                }
                case "6" -> {
                    String taskToDelete = promptInput("Enter the task name to delete:", "Delete Task");
                    if (taskToDelete != null) {
                        String deleteResult = deleteTask(taskToDelete);
                        showMessage(deleteResult, "Delete Task"); // Delete a task by name
                    }
                }
                case "7" ->
                    backToMainMenu = true; // Back to main menu
                default ->
                    showMessage("Invalid option. Please select 1, 2, 3, 4, 5, 6, or 7.", "Error");
            }
        }
    }

    /**
     * Displays a prompt dialog and returns user input as a string.
     *
     * @param message The message to display in the dialog.
     * @param title The title of the dialog window.
     * @return The user input as a string, or null if canceled.
     */
    private static String promptInput(String message, String title) {
        try {
            return JOptionPane.showInputDialog(null, message, title, JOptionPane.QUESTION_MESSAGE);
        } catch (HeadlessException e) {
            showMessage("Error: Unable to display input dialog.", "Error");
            return null;
        }
    }

    /**
     * Displays a message dialog with the given message and title.
     *
     * @param message The message to display.
     * @param title The title of the message dialog window.
     */
    private static void showMessage(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    //  Handles adding multiple tasks based on user input.
    private static void addTasks() {
        int numberOfTasks = promptForInteger("How many tasks would you like to enter?", "Task Entry");
        if (numberOfTasks == -1) {
            return;
        }

        for (int k = 0; k < numberOfTasks; k++) {
            String taskName = promptInput("Enter the task name:", "Task Entry");
            String taskDescription = promptInput("Enter the task description (50 characters max):", "Task Entry");
            if (taskDescription.length() > 50) {
                showMessage("Please enter a task description of less than 50 characters", "Error");
                k--;
                continue;
            }

            String developerDetails = promptInput("Enter the developer details(name & surname):", "Task Entry");
            int taskDuration = promptForInteger("Enter the task duration (in hours):", "Task Entry");
            if (taskDuration == -1) {
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
            showMessage("Task successfully captured\n" + newTask.printTaskDetails(), "Task Entry");
        }
    }

    /**
     * Prompts the user for an integer input.
     *
     * @param message The message to display for the input prompt.
     * @param title The title of the input prompt dialog.
     * @return The integer entered by the user, or -1 if input is invalid.
     */
    private static int promptForInteger(String message, String title) {
        try {
            return Integer.parseInt(promptInput(message, title));
        } catch (NumberFormatException e) {
            showMessage("Please enter a valid number.", "Error");
            return -1;
        }
    }

    // Displays a report of all tasks, including details such as name, developer, ID, duration, and status.
    private static void displayAllTasks() {
        if (taskNames.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No tasks available.", "All Task", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        StringBuilder report = new StringBuilder();
        for (int i = 0; i < taskNames.size(); i++) {
            report.append("Task Name: ").append(taskNames.get(i)).append("\n")
                    .append("Developer: ").append(developers.get(i)).append("\n")
                    .append("Task ID: ").append(taskIDs.get(i)).append("\n")
                    .append("Duration: ").append(taskDurations.get(i)).append(" hours\n")
                    .append("Status: ").append(taskStatuses.get(i)).append("\n\n");
        }
        JOptionPane.showMessageDialog(null, report.toString(), "All Tasks", JOptionPane.INFORMATION_MESSAGE);
    }

    // Displays a report of tasks marked as "Done", including details such as name, developer, ID, duration, and status.
    private static void displayDoneTasks() {
        if (taskNames.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No tasks available.", "Done Task", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        StringBuilder report = new StringBuilder();
        for (int i = 0; i < taskNames.size(); i++) {
            if (taskStatuses.get(i).equalsIgnoreCase("Done")) {
                report.append("Task Name: ").append(taskNames.get(i)).append("\n")
                        .append("Developer: ").append(developers.get(i)).append("\n")
                        .append("Task ID: ").append(taskIDs.get(i)).append("\n")
                        .append("Duration: ").append(taskDurations.get(i)).append(" hours\n")
                        .append("Status: ").append(taskStatuses.get(i)).append("\n\n");
            }
        }
        JOptionPane.showMessageDialog(null, report.toString(), "Done Tasks", JOptionPane.INFORMATION_MESSAGE);
    }

    // Displays details of the task with the longest duration.
    private static void displayLongestTask() {
        if (taskDurations.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No tasks available.", "Longest Task", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int maxDurationIndex = 0;
        for (int i = 1; i < taskDurations.size(); i++) {
            if (taskDurations.get(i) > taskDurations.get(maxDurationIndex)) {
                maxDurationIndex = i;
            }
        }

        String longestTaskDetails = "Task Name: " + taskNames.get(maxDurationIndex) + "\n"
                + "Developer: " + developers.get(maxDurationIndex) + "\n"
                + "Task ID: " + taskIDs.get(maxDurationIndex) + "\n"
                + "Duration: " + taskDurations.get(maxDurationIndex) + " hours\n"
                + "Status: " + taskStatuses.get(maxDurationIndex) + "\n";

        JOptionPane.showMessageDialog(null, longestTaskDetails, "Longest Task", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Searches for a task by its name and displays the developer and task name
     * if found.
     *
     * @param taskName The name of the task to search for.
     * @return A string containing the developer and task name if found, or a
     * message indicating task not found.
     */
    public static String searchTaskByName(String taskName) {
        for (int i = 0; i < taskNames.size(); i++) {
            if (taskNames.get(i).equalsIgnoreCase(taskName)) {
                return developers.get(i) + ", " + taskNames.get(i) + ", " + taskStatuses.get(i); // Return developer and task name
            }
        }
        return "Task not found"; // Return a message if task not found
    }

    /**
     * Searches for tasks assigned to a specific developer and displays their
     * details.
     *
     * @param developer The name of the developer to search for.
     * @return A string containing details of tasks assigned to the developer,
     * or a message indicating no tasks found.
     */
    public static String searchTasksByDeveloper(String developer) {
        StringBuilder report = new StringBuilder();
        boolean foundTasks = false;
        for (int i = 0; i < developers.size(); i++) {
            if (developers.get(i).equalsIgnoreCase(developer)) {
                report.append("Task Name: ").append(taskNames.get(i)).append("\n")
                        .append("Task ID: ").append(taskIDs.get(i)).append("\n")
                        .append("Duration: ").append(taskDurations.get(i)).append(" hours\n")
                        .append("Status: ").append(taskStatuses.get(i)).append("\n\n");
                foundTasks = true;
            }
        }
        if (foundTasks) {
            return report.toString(); // Return tasks assigned to the developer
        } else {
            return "No tasks assigned to " + developer; // Return a message if no tasks found
        }
    }

    /**
     * Deletes a task based on its name from all task-related ArrayLists.
     *
     * @param taskToDelete The name of the task to delete.
     * @return A message indicating whether the task was successfully deleted or
     * not found.
     */
    public static String deleteTask(String taskToDelete) {
        boolean deleted = false;
        for (int i = 0; i < taskIDs.size(); i++) {
            if (taskNames.get(i).equalsIgnoreCase(taskToDelete)) {
                taskNames.remove(i);
                developers.remove(i);
                taskIDs.remove(i);
                taskDurations.remove(i);
                taskStatuses.remove(i);
                deleted = true;
                break;
            }
        }
        if (deleted) {
            return "Entry '" + taskToDelete + "' successfully deleted";
        } else {
            return "Task '" + taskToDelete + "' not found";
        }
    }

    /**
     * Returns a string representation of the developer and duration of the task
     * with the longest duration.
     *
     * @return A string containing the developer and duration of the longest
     * task.
     */
    public static String displayLongestTaskDeveloperAndDuration() {
        // Example data, replace with your actual implementation based on taskDurations
        if (taskDurations.isEmpty()) {
            return "No tasks available";
        }

        int longestIndex = 0;
        for (int i = 1; i < taskDurations.size(); i++) {
            if (taskDurations.get(i) > taskDurations.get(longestIndex)) {
                longestIndex = i;
            }
        }

        String longestTaskDeveloper = developers.get(longestIndex);
        int longestTaskDuration = taskDurations.get(longestIndex);

        return longestTaskDeveloper + ", " + longestTaskDuration;

    }

}
