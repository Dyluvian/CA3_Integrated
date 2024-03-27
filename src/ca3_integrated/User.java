package ca3_integrated;

/*
// Dylan Geraghty - CA3 (Integrated CA) - Programming: Object-Oriented Approach
// ------------------
// https://github.com/Dyluvian/CA3_Integrated
// ------------------
// INTERFACE: USER
// ------------------
// This interface reprsents the user entity (as opposed to representing the actual staff member BEHIND the user; that is handled by the Staff class).
// It draws on the implementations relevant to that individual as laid out in Staff.
// ------------------
 */

public interface User {

    int getUserID(); // Declare the getUserID method, which... gets the user ID. An integer

    void decrementUserID(); // Method to decrement the user ID, used to ensure a linear ID number order when removing users.

    String getUsername(); // Similarly, gets the username. A string

    void setUsername(String username); // Method to set the username, taking a string.

    String getPassword(); // Gets the password, a string

    void setPassword(String password); // Method to set the password, taking a string.

    String getRole(); // Gets the user role, a string

    void setRole(String role); // Method to set the user role, taking a string.
}
