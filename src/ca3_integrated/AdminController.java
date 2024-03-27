package ca3_integrated;

/*
// Dylan Geraghty - CA3 (Integrated CA) - Programming: Object-Oriented Approach
// ------------------
// https://github.com/Dyluvian/CA3_Integrated
// ------------------
// CLASS: USER CONTROLLER FOR ADMIN ROLE (ADMINCONTROLLER)
// ------------------
// 
// ------------------
*/

import java.util.Scanner;

public class AdminController {
    
    private UserDatabase userDatabase;

    public AdminController(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }
    
    @Override
    public void login() {
    }
    
    @Override
    public void showMenu(User user) {
        if (user instanceof Admin) {
            showAdminMenu((Admin) user);
        }
    }
    
    private void showAdminMenu(Admin admin) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("---\nInput 1 to view current users.\nInput 2 to add new users.\nInput 3 to modify the usernames, passwords, and roles of other users.\nInput 4 to delete users.\nInput 5 to log out.\nInput 6 to close the software.\n---");
            choice = scanner.nextInt();
            switch (choice) {
// TO VIEW CURRENT USERS
                case 1:
                    System.out.println("---");
                    userDatabase.printAllUsers();
                    break;
}
