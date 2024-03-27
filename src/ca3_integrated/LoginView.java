package ca3_integrated;

/*
// Dylan Geraghty - CA3 (Integrated CA) - Programming: Object-Oriented Approach
// ------------------
// https://github.com/Dyluvian/CA3_Integrated
// ------------------
// INTERFACE: LOGIN VIEW
// ------------------
// 
// ------------------
*/

public interface LoginView {

    void displayLoginPrompt(); // Run the method to display the login prompt.

    String getUserInput();

    String getPasswordInput();
}
