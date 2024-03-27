package ca3_integrated;

/*
// Dylan Geraghty - CA3 (Integrated CA) - Programming: Object-Oriented Approach
// ------------------
// https://github.com/Dyluvian/CA3_Integrated
// ------------------
// INTERFACE: LOGIN VIEW
// ------------------
// The Login View is simply the user-facing interface for login functionality.
// It draws on the implementations in ConsoleLoginView.
// ------------------
*/

public interface LoginView {

    void displayLoginPrompt(); // Run the method to display the login prompt.

    String getUserInput(); // Getter method for user input.

    String getPasswordInput(); // Getter method for password input.
}
