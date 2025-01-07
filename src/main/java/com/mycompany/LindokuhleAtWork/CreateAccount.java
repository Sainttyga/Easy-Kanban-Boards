package com.mycompany.LindokuhleAtWork;

import javax.swing.JOptionPane;

/**
 *
 * @author Lindokuhle Zwane
 */
public class CreateAccount {

    // Private variables to store user information
    private String name;
    private String surname;
    private String username;
    private String password;

    // Constructor method initializes variables to empty strings
    public CreateAccount() {
        this.name = "";
        this.surname = "";
        this.username = "";
        this.password = "";
    }

    /**
     * Checks if the username meets the required format.
     * @return true if the username contains an underscore and is no more than 5 characters in length
     */
    public boolean checkUsername() {
        return username.length() <= 5 && username.contains("_");
    }

    /**
     * Checks if the password meets the required complexity rules.
     * @return true if the password contains at least 8 characters, a capital letter, a number, and a special character
     */
    public boolean checkPasswordComplexity() {
        // Regex pattern ensures password complexity
        return password.matches("^(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=!]).{8,}$");
    }

    /**
     * Registers a new user with the provided name, surname, username, and password.
     * @param name the user's name
     * @param surname the user's surname
     * @param username the desired username
     * @param password the desired password
     * @return a message indicating the success or failure of the registration process
     */
    public String registerUser(String name, String surname, String username, String password) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;

        // Validate username format
        while (!checkUsername()) {
            JOptionPane.showMessageDialog(null, "Invalid Username! Username is not correctly formatted,\nPlease ensure that your username contains an underscore(_) and is no more than 5 characters in length.", "Registration Error", JOptionPane.ERROR_MESSAGE);
            this.username = JOptionPane.showInputDialog(null, "Enter a username", "Registration", JOptionPane.QUESTION_MESSAGE);
            if (this.username == null) {
                return "Registration cancelled.";
            }
        }

        // Validate password complexity
        while (!checkPasswordComplexity()) {
            JOptionPane.showMessageDialog(null, "Invalid Password! Password is not correctly formatted,\nPlease ensure that the password contains at least \n8 characters, a capital letter, a number and a special character.", "Registration Error", JOptionPane.ERROR_MESSAGE);
            this.password = JOptionPane.showInputDialog(null, "Enter a password", "Registration", JOptionPane.QUESTION_MESSAGE);
            if (this.password == null) {
                return "Registration cancelled.";
            }
        }
        return "Registration successful!";
    }

    /**
     * Verifies if the provided login details match the stored details.
     * @param username the username to verify
     * @param password the password to verify
     * @return true if the username and password match the stored details
     */
    public boolean loginUser(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    /**
     * Returns a message indicating the success or failure of a login attempt.
     * @param username the username to verify
     * @param password the password to verify
     * @return a message indicating the login status
     */
    public String returnLoginStatus(String username, String password) {
        if (loginUser(username, password)) {
            return "Successful to Login! \nWelcome " + name + " " + surname + ", it is great to see you again.";
        } else {
            JOptionPane.showMessageDialog(null, "Failed to Login", "Login Error", JOptionPane.ERROR_MESSAGE);
        System.exit(0); // Terminate the application
        return null; // This line will not be reached
        }
    }
}