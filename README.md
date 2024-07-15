# EasyKanban

## Overview

**EasyKanban** is a simple Java-based Kanban board application that allows users to register, log in, and manage tasks. Users can add tasks, display task reports, search for tasks, and delete tasks. The application uses a graphical user interface (GUI) to interact with the user, provided by `JOptionPane`.

## Features

- **User Registration and Login**: Users can register with a first name, last name, username, and password, and then log in using their credentials.
- **Task Management**: Users can add tasks with details such as task name, description, developer details, duration, and status.
- **Task Reporting**: Users can display a report of all tasks, showing details such as task name, developer, task ID, duration, and status.
- **Task Search**: Users can search for tasks by task name or developer.
- **Task Deletion**: Users can delete tasks by specifying the task name.

## Classes

### `KanbanBoards`

This is the main class of the application. It contains the following functionality:

- **Main Method**: Manages user registration, login, and the main menu for task management.
- **Task Management**: Methods to add tasks, display reports, search tasks, and delete tasks.

### `Tasks`

Represents a task in the Kanban board. It contains the following:

- **Task Details**: Attributes such as task name, description, developer details, duration, status, and task ID.
- **Task ID Generation**: Generates a unique ID for each task based on the task name, task number, and developer details.
- **Task Details Display**: Methods to print task details and validate the task description length.

### `CreateAccount`

Handles user account creation and login verification. It includes:

- **User Registration**: Methods to register new users.
- **Login Verification**: Methods to validate user login credentials.

### `TasksTest`

JUnit test class to validate the functionality of the `Tasks` class and related task operations. It includes tests for:

- Verifying the correct population of developer lists.
- Displaying the developer and duration for the longest task.
- Searching tasks by name.
- Searching tasks by developer.
- Deleting tasks.
- Generating task reports.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- A Java IDE such as or NetBeans (Java with Maven -> Java Application)

### Installation

1. **Clone the repository**:
    ```bash
    https://github.com/Sainttyga/Easy-Kanban-Boards.git
    ```

2. **Open the project in your IDE**:
    - Import the project as a Maven or Gradle project if applicable.

### Running the Application

1. **Compile and Run**:
    - Run the `KanbanBoards` class. This will start the application and display the registration screen.

2. **Interact with the GUI**:
    - Follow the prompts to register, log in, and manage tasks.

### Running Tests

1. **Execute JUnit Tests**:
    - Run the `TasksTest` class to execute all the unit tests. Your IDE should provide a way to run JUnit tests from the test class directly.

## Usage

1. **Register**: Enter your first name, last name, desired username, and password.
2. **Login**: Enter your username and password.
3. **Main Menu**: Choose from the following options:
    - `Add tasks`: Enter task details to add new tasks.
    - `Show report`: Display a report of all tasks.
    - `Search tasks`: Search for tasks by name or developer.
    - `Delete task`: Delete a task by specifying the task name.
    - `Quit`: Exit the application.

  ### Example Input

When prompted for registration, enter the following details:
- First Name: `John`
- Last Name: `Doe`
- Username: `john_doe`
- Password: `Password123!`

For task management:
- Task Number: '2'
- Task Name: `Login Feature`
- Task Description: `Create Login to authenticate users`
- Developer Details: `Robyn Harrison`
- Task Duration: `8hrs`
- Task Status: `To Do`

Task 2
- For task management:
- Task Name: `Add Task Feature`
- Task Description: `Create Add Task feature to add task users`
- Developer Details: `Mike Smith`
- Task Duration: `10hrs`
- Task Status: `Doing`

- Total Hours: 18Hrs

## Contributing

Contributions are welcome! If you find any issues or have suggestions for improvements, please submit an issue or create a pull request.

## Acknowledgements

- This project uses `JOptionPane` for GUI interactions.
- JUnit 5 is used for unit testing.
