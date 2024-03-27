package ca3_integrated;

public class CA3_Integrated {

public static void main(String[] args) {
        UserDatabase userDatabase = new UserDatabase(); 
        LoginView loginView = new ConsoleLoginView(); 
        UserController userController = new ConsoleUserController(loginView, userDatabase); 
        System.out.println("---\nWelcome to the Report Generation Framework for Innistown College, the most fictional college in Ireland.\nYou are liable to have veritable hours of fun adding, editing, and deleting your colleagues, logging in and out and in again, and snooping on others' GDPR-protected details.\nDo enjoy the experience and genuinely hope to see you again during the next trimester."); 
        userController.login(); 
    }
}