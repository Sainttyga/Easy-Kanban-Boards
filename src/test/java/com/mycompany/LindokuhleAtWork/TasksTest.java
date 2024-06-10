package com.mycompany.LindokuhleAtWork;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TasksTest {

    private List<String> developers;
    private List<String> taskNames;
    private List<Integer> taskDurations;
    private List<String> taskStatuses;

    /**
     * Sets up the initial data for the tests. This method is executed before
     * each test case to ensure a consistent test environment.
     */
    @BeforeEach
    void setUp() {
        developers = new ArrayList<>();
        taskNames = new ArrayList<>();
        taskDurations = new ArrayList<>();
        taskStatuses = new ArrayList<>();

        // Populate lists with test data
        developers.add("Mike Smith");
        taskNames.add("Create Login");
        taskDurations.add(5);
        taskStatuses.add("To Do");

        developers.add("Edward Harrison");
        taskNames.add("Create Add Features");
        taskDurations.add(8);
        taskStatuses.add("Doing");

        developers.add("Samantha Paulson");
        taskNames.add("Create Reports");
        taskDurations.add(2);
        taskStatuses.add("Done");

        developers.add("Glenda Oberholzer");
        taskNames.add("Add Arrays");
        taskDurations.add(11);
        taskStatuses.add("To Do");
    }

    /**
     * Tests that the developer list is correctly populated with the expected
     * test data.
     */
    @Test
    public void testDeveloperArrayCorrectlyPopulated() {
        List<String> expectedDevelopers = List.of("Mike Smith", "Edward Harrison", "Samantha Paulson", "Glenda Oberholzer");
        assertEquals(expectedDevelopers, developers, "Developer array should contain the expected test data");
    }

    /**
     * Tests that the developer and duration for the longest task are correctly
     * identified and returned.
     */
    @Test
    public void testDisplayDeveloperAndDurationForLongestTask() {
        int longestDurationIndex = 0;
        for (int i = 1; i < taskDurations.size(); i++) {
            if (taskDurations.get(i) > taskDurations.get(longestDurationIndex)) {
                longestDurationIndex = i;
            }
        }
        String expectedResult = "Glenda Oberholzer, 11";
        String actualResult = developers.get(longestDurationIndex) + ", " + taskDurations.get(longestDurationIndex);
        assertEquals(expectedResult, actualResult, "Should return the developer and duration for the task with the longest duration");
    }

    /**
     * Tests the search functionality for tasks by task name. It verifies that
     * the correct developer and task name are returned for a searched task.
     */
    @Test
    public void testSearchForTasks() {
        String searchTerm = "Create Login";
        int index = taskNames.indexOf(searchTerm);
        String expectedResult = "Mike Smith, Create Login";
        String actualResult = developers.get(index) + ", " + taskNames.get(index);
        assertEquals(expectedResult, actualResult, "Should return the developer and task name for the searched task");
    }

    /**
     * Tests the search functionality for tasks assigned to a specific
     * developer. It verifies that all tasks assigned to the given developer are
     * returned.
     */
    @Test
    public void testSearchAllTasksAssignedToDeveloper() {
        String developerName = "Samantha Paulson";
        List<String> expectedTasks = List.of("Create Reports");
        List<String> actualTasks = new ArrayList<>();
        for (int i = 0; i < developers.size(); i++) {
            if (developers.get(i).equals(developerName)) {
                actualTasks.add(taskNames.get(i));
            }
        }
        assertEquals(expectedTasks, actualTasks, "Should return all tasks assigned to the given developer");
    }

    /**
     * Tests the deletion functionality for tasks. It verifies that the
     * specified task is successfully deleted from the lists.
     */
    @Test
    public void testDeleteTaskFromArray() {
        String taskToDelete = "Create Reports";
        int index = taskNames.indexOf(taskToDelete);
        if (index != -1) {
            developers.remove(index);
            taskNames.remove(index);
            taskDurations.remove(index);
            taskStatuses.remove(index);
        }
        boolean taskStillExists = taskNames.contains(taskToDelete);
        assertEquals(false, taskStillExists, "Task should be successfully deleted from the array");
    }

    /**
     * Tests the report generation functionality. It verifies that a correctly
     * formatted report of all tasks is generated.
     */
    @Test
    public void testDisplayReport() {
        StringBuilder report = new StringBuilder();
        for (int i = 0; i < developers.size(); i++) {
            report.append("Task Name: ").append(taskNames.get(i))
                    .append("\nDeveloper: ").append(developers.get(i))
                    .append("\nDuration: ").append(taskDurations.get(i))
                    .append("\nStatus: ").append(taskStatuses.get(i))
                    .append("\n\n");
        }
        String expectedReport = """
                                Task Name: Create Login
                                Developer: Mike Smith
                                Duration: 5
                                Status: To Do
                                
                                Task Name: Create Add Features
                                Developer: Edward Harrison
                                Duration: 8
                                Status: Doing
                                
                                Task Name: Create Reports
                                Developer: Samantha Paulson
                                Duration: 2
                                Status: Done
                                
                                Task Name: Add Arrays
                                Developer: Glenda Oberholzer
                                Duration: 11
                                Status: To Do
                                
                                """;
        assertEquals(expectedReport, report.toString(), "Should return a correctly formatted report of all tasks");
    }
}
