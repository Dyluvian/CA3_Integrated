package ca3_integrated;

public class CA3_Integrated {

public static void main(String[] args) {
        UserDatabase userDatabase = new UserDatabase(); 
        LoginView loginView = new ConsoleLoginView(); 
        UserController userController = new ConsoleUserController(loginView, userDatabase); 
        System.out.println("---\nWelcome."); 
        userController.login(); 
    }
}