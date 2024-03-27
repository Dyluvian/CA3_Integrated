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
// TO MODIFY THE USERNAMES, PASSWORDS, AND ROLES OF OTHER USERS
                case 3:
                    System.out.println("---\nPlease enter the ID of the user whose credentials you wish to modify.\n---");
                    int userID = scanner.nextInt();
                    User user = userDatabase.getUser(userID);
                    if (user == null) {
                        System.out.println("---\nUnfortunately, no such user has been located! Alas, not even down the back of the sofa.");
                    } else {
                        if (userID == admin.getUserID()) {
                            System.out.println("---\nUnfortunately, you are not allowed to modify your own credentials! The high scriptures forbid it.");
                            break;
                        }
                        System.out.println("---\nAcknowledged.\nInput 1 to modify their username.\nInput 2 to modify their password.\nInput 3 to modify their role.\n---");
                        int choiceCredentials = scanner.nextInt();
                        switch (choiceCredentials) {
                            case 1:
                                System.out.println("---\nNow, please input the new username you wish to grant this user.\n---");
                                String newUsername = scanner.next();
                                if (userDatabase.usernameExists(newUsername)) {
                                    System.out.println("---\nUnfortunately, this username already exists in the database! Two heads are more than one! Please choose another one.");
                                    break;
                                }
                                user.setUsername(newUsername);
                                System.out.println("---\nTerrific! Their username has been updated. A change is as good as a rest.");
                                break;
                            case 2:
                                System.out.println("---\nNow, please input the user's new password.\n---");
                                user.setPassword(scanner.next());
                                System.out.println("---\nFantastic! Their password has been changed. Play nice with this information!");
                                break;
                            case 3:
                                boolean validRole = false;
                                System.out.println("---\nNow, please input the role of the new user. You may choose Admin, Office, or Lecturer.\n---");
                                while (!validRole) {
                                    String newRole = scanner.next();
                                    switch (newRole.toLowerCase()) {
                                        case "admin":
                                        case "1":
                                            user.setRole("Admin");
                                            validRole = true;
                                            System.out.println("---\nCallooh, callay! May the user revel in their new role, if possible.");
                                            break;
                                        case "office":
                                        case "2":
                                            user.setRole("Office");
                                            validRole = true;
                                            System.out.println("---\nCallooh, callay! May the user revel in their new role, if possible.");
                                            break;
                                        case "lecturer":
                                        case "3":
                                            user.setRole("Lecturer");
                                            validRole = true;
                                            System.out.println("---\nCallooh, callay! May the user revel in their new role, if possible.");
                                            break;
                                        default:
                                            System.out.println("---\nUnfortunately, that is not a valid role! Please choose Admin, Office, or Lecturer.\n---");
                                    }
                                }
                                break;
                            default:
                                System.out.println("---\nCome, now! We both know that is not a valid option. Please input one of the available choices.");
                        }
                        userDatabase.updateUser(userID, user);
                    }
                    break;
// TO DELETE USERS
                case 4:
                    System.out.println("---\nPlease input the user ID of the user you wish to delete.\n---");
                    int deleteUserID = scanner.nextInt();
                    User userToDelete = userDatabase.getUser(deleteUserID);
                    if (userToDelete == null) {
                        System.out.println("---\nImpossible! There is no such user. None where there's none wanted! Please choose an existing ID next time.");
                    } else {
                        if (deleteUserID == admin.getUserID()) {
                            System.out.println("---\nLaughable! That is your own user ID! You are not permitted to delete your own account. Ho ho ho.");
                        } else {
                            userDatabase.deleteUser(deleteUserID);
                            System.out.println("---\nStupendous! The user has been removed from the database.");
                        }
                    }
                    break;
// TO LOG OUT
                case 5:
                    logout();
                    return;
            }