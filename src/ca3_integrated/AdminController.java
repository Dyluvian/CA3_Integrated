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
// TO ADD NEW USERS
                case 2:
                    System.out.println("---\nPlease input the username of the user you wish to add.\n---");
                    String username = scanner.next();
                    if (userDatabase.usernameExists(username)) {
                        System.out.println("---\nUnfortunately, a user by this name already exists! We can barely cope with one of you as it is. Please input a different name.");
                        break;
                    }
                    System.out.println("---\nNow, please input the user's password.\n---");
                    String password = scanner.next();
                    System.out.println("---\nFinally, please input the role of the new user. Currently, the valid options are Admin, Office, and Lecturer.\n---");
                    while (true) {
                        String role = scanner.next();
                        User newUser;
                        switch (role.toLowerCase()) {
                            case "admin":
                            case "1":
                                newUser = new Admin(0, username, password);
                                break;
                            case "office":
                            case "2":
                                newUser = new Office(0, username, password);
                                break;
                            case "lecturer":
                            case "3":
                                newUser = new Lecturer(0, username, password);
                                break;
                            default:
                                System.out.println("---\nThat is not a valid role. Please select one of the valid options: Admin, Office, or Lecturer.\n---");
                                continue;
                        }
                        userDatabase.addUser(newUser);
                        System.out.println("---\nExcellent! The new user has been added, and may now log in. Best of luck to them!");
                        break;
                    }
                    break;
            }