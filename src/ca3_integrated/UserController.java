package ca3_integrated;

/*
// Dylan Geraghty - CA3 (Integrated CA) - Programming: Object-Oriented Approach
// ------------------
// https://github.com/Dyluvian/CA3_Integrated
// ------------------
// INTERFACE: USER CONTROLLER
// ------------------
// This interface is the user-facing presentation of the login, logout, and role-oriented menu functionalities.
// It draws on the implementations defined in the User Controller Console (ConsoleUserController).
// ------------------
*/

public interface UserController {
    void login(); // Run the login method
    void showMenu(User user); // Show the menu corresponding to the user in question
    void logout(); // Run the logout method
}