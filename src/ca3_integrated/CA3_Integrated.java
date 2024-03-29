package ca3_integrated;

/*
// Dylan Geraghty - CA3 (Integrated CA) - Programming: Object-Oriented Approach
// ------------------
// https://github.com/Dyluvian/CA3_Integrated
// ------------------
// CLASS: CA3_INTEGRATED (MAIN METHOD)
// ------------------
// Welcome to the Java portion of the integrated CA, version who knows what.
// This has been started, experimented with, restarted, prototyped, rebooted, torn up, flame grilled, and reborn a few times too many.
// I wanted to do my best to give abstraction and modularity a decent shake, but not easy to wrap the mind around everything this quickly.
// Still not fully sure of it but can only do my best. It required a lot of blocking out and quick restructuring against tight deadlines.
// Hopefully it is all functional after time of writing and ticks a few boxes at least.
// ------------------
// If you'd like to read something pleasant AFTER GRADING, very deliberately separated out, go to Cheers.java.
// ------------------
*/

public class CA3_Integrated {

public static void main(String[] args) {
        UserDatabase userDatabase = new UserDatabase(); // set up the new user database 
        LoginView loginView = new ConsoleLoginView(); // set up the new login view
        UserController userController = new ConsoleUserController(loginView, userDatabase); // set up the new user controller
        System.out.println("---\nWelcome to the Report Generation Framework for Innistown College, the most fictional college in Ireland.\nYou are liable to have veritable hours of fun adding, editing, and deleting your colleagues, logging in and out and in again, and snooping on others' GDPR-protected details.\nDo enjoy the experience and genuinely hope to see you again during the next trimester."); 
        userController.login(); // ask to login
    }
}