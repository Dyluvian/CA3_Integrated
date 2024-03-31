package ca3_integrated;

/*
// Dylan Geraghty - CA3 (Integrated CA) - Programming: Object-Oriented Approach
// ------------------
// https://github.com/Dyluvian/CA3_Integrated
// ------------------
// CLASS: CONSOLE FOR LOGIN VIEW (CONSOLELOGINVIEW)
// ------------------
// The ConsoleLoginView class is the implementation for the Login View interface, containing the methods that class relies on to log users in.
// ------------------
*/

import java.util.Scanner; // Scan for input

public class ConsoleLoginView implements LoginView {

    private Scanner scanner; // Multipurpose scanner for username and password, no need for a more granular name.

    public ConsoleLoginView() {
        scanner = new Scanner(System.in); // Start the scanner
    }

    @Override
    public void displayLoginPrompt() {
        System.out.println("---\nTo log in and perform operations, please begin by inputting your username.\n---"); // Does nothing but display a login text prompt.
    }

    @Override
    public String getUserInput() {
        while (true) {
            String userInput = scanner.nextLine(); // Take whatever the user enters as their username input.
            if (!userInput.isEmpty()) { // as long as it's not empty
                return userInput;
            } else {
                System.out.println("---\nThat is not valid username input. Perhaps you would care to give that another try? I have all day.\n---"); // if it is, complain
            }
        }
    }

    @Override
    public String getPasswordInput() {
        System.out.print("---\nNow, please input your password.\n---\n"); // Ask for the user's password...
        while (true) {
            String passwordInput = scanner.nextLine(); // ...and take whatever they next enter
            if (!passwordInput.isEmpty()) { // as long as it's not empty
                return passwordInput;
            } else {
                System.out.println("---\nThat is not valid password input. Perhaps you would care to give that another try? I have all day.\n---"); // if it is, complain
            }
        }
    }
}
