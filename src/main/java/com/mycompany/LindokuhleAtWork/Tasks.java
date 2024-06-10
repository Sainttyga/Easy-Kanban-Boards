package com.mycompany.LindokuhleAtWork;

/**
 *
 * @author Lindokuhle Zwane
 */
public class Tasks {

    // Private fields to store task details
    private final String taskName;
    private final int taskNumber;
    private final String taskDescription;
    private final String developerDetails;
    private final int taskDuration;
    private final String taskID;
    private final String taskStatus;
    private static int taskCounter = 0; // Static counter to generate unique task numbers

    /**
     * Constructor to initialize a new task with the given details.
     * @param taskName Name of the task
     * @param taskDescription Description of the task (max 50 characters)
     * @param developerDetails Developer assigned to the task
     * @param taskDuration Duration of the task in hours
     * @param taskStatus Status of the task (e.g., "To Do", "Doing", "Done")
     */
    public Tasks(String taskName, String taskDescription, String developerDetails, int taskDuration, String taskStatus) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.developerDetails = developerDetails;
        this.taskDuration = taskDuration;
        this.taskStatus = taskStatus;
        this.taskNumber = taskCounter++; // Assign unique task number and increment counter
        this.taskID = createTaskID(); // Generate task ID
    }

    /**
     * Resets the task counter to zero. Useful for testing or resetting state.
     */
    public static void resetTaskCounter() {
        taskCounter = 0;
    }

    /**
     * Checks if the task description is within the allowed length (50 characters or less).
     * @return true if the description is valid, false otherwise
     */
    public boolean checkTaskDescription() {
        return taskDescription.length() <= 50;
    }

    /**
     * Generates a unique task ID based on task name, task number, and developer details.
     * @return Generated task ID
     */
    private String createTaskID() {
        String taskNamePart = taskName.length() >= 2 ? taskName.substring(0, 2).toUpperCase() : taskName.toUpperCase();
        String developerPart = developerDetails.length() >= 3 ? developerDetails.substring(developerDetails.length() - 3).toUpperCase() : developerDetails.toUpperCase();
        return taskNamePart + ":" + taskNumber + ":" + developerPart;
    }

    /**
     * Gets the unique task ID.
     * @return Task ID
     */
    public String getTaskID() {
        return taskID;
    }

    /**
     * Prints the details of the task in a formatted string.
     * @return Formatted task details
     */
    public String printTaskDetails() {
        return "Task Status: " + taskStatus + "\n"
                + "Developer Details: " + developerDetails + "\n"
                + "Task Number: " + taskNumber + "\n"
                + "Task Name: " + taskName + "\n"
                + "Task Description: " + taskDescription + "\n"
                + "Task ID: " + taskID + "\n"
                + "Duration: " + taskDuration + " hours";
    }

    /**
     * Returns the duration of the task.
     * @return Task duration in hours
     */
    public int returnTotalHours() {
        return taskDuration;
    }

    // Getter methods to access private fields

    /**
     * Gets the developer details.
     * @return Developer details
     */
    public String getDeveloperDetails() {
        return developerDetails;
    }

    /**
     * Gets the name of the task.
     * @return Task name
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Gets the duration of the task.
     * @return Task duration in hours
     */
    public int getTaskDuration() {
        return taskDuration;
    }

    /**
     * Gets the status of the task.
     * @return Task status
     */
    public String getTaskStatus() {
        return taskStatus;
    }
}
