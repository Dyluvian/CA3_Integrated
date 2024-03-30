package ca3_integrated;

/*
// Dylan Geraghty - CA3 (Integrated CA) - Programming: Object-Oriented Approach
// ------------------
// https://github.com/Dyluvian/CA3_Integrated
// ------------------
// CLASS: USER CONTROLLER FOR ADMIN ROLE (ADMINCONTROLLER)
// ------------------
// This class delves into the unique functionalities assigned to the Admin role, beyond the generic user coverage of the main User Controller.
// Specifically, it enables the Admin to view all users, add new users, modify the parameters of users, delete users, log out, and close the program.
// ------------------
*/

import java.util.Scanner; // import Scanner for accepting user input

public class AdminController implements UserController {
    
    private UserDatabase userDatabase; // initialise the database

    public AdminController(UserDatabase userDatabase) {
        this.userDatabase = userDatabase; // relate the AdminController to the database
    }
    
    @Override
    public void login() { // Standard login
    }

    @Override
    public void showMenu(User user) {
        if (user instanceof Admin) { // if the user is an Admin...
            showAdminMenu((Admin) user); // ...show the Admin menu
        }
    }
    
    private void showAdminMenu(Admin admin) {
        Scanner scanner = new Scanner(System.in); // kickstart the scanner
        int choice; // initialise the choice integer for switch
        do {
            System.out.println("---\nInput 1 to view current users.\nInput 2 to add new users.\nInput 3 to modify the usernames, passwords, and roles of other users.\nInput 4 to delete users.\nInput 5 to log out.\nInput 6 to close the software.\n---"); // instructions
            choice = scanner.nextInt(); // next int inserted defines your choice
            switch (choice) {
// TO VIEW CURRENT USERS
                case 1: // if you input 1...
                    System.out.println("---");
                    userDatabase.printAllUsers(); // run the method to print all users
                    break;
// TO ADD NEW USERS
                case 2: // if you input 2...
                    System.out.println("---\nPlease input the username of the user you wish to add.\n---"); // request a username
                    scanner.nextLine(); // clear input buffer
                    String username = scanner.nextLine(); // next input will become the username
                    if (userDatabase.usernameExists(username)) { // ...unless that user already exists
                        System.out.println("---\nUnfortunately, a user by this name already exists! We can barely cope with one of you as it is. Please input a different name."); // and nag the user about it
                        break;
                    }
                    System.out.println("---\nNow, please input the user's password.\n---"); // next, input your password
                    String password = scanner.nextLine(); // next input will become the password. Anything is valid
                    System.out.println("---\nFinally, please input the role of the new user. Currently, the valid options are Admin, Office, and Lecturer.\n---"); // finally, input the role
                    while (true) { // loop until we have valid input
                        String role = scanner.nextLine(); // next input will become the role
                        User newUser;
                        switch (role.toLowerCase()) { // lettercase is whatever
                            case "admin":
                            case "1": // numbers are also whatever
                                newUser = new Admin(0, username, password); // create an admin
                                break;
                            case "office":
                            case "2":
                                newUser = new Office(0, username, password); // or create an office... person
                                break;
                            case "lecturer":
                            case "3":
                                newUser = new Lecturer(0, username, password); // or create a lecturer
                                break;
                            default: // if any other input was detected...
                                System.out.println("---\nThat is not a valid role. Please select one of the valid options: Admin, Office, or Lecturer.\n---"); // nag the user...
                                continue; // and retry
                        }
                        userDatabase.addUser(newUser); // ultimately, add the new user
                        System.out.println("---\nExcellent! The new user has been added, and may now log in. Best of luck to them!"); // Congrats!
                        break;
                    }
                    break;
// TO MODIFY THE USERNAMES, PASSWORDS, AND ROLES OF OTHER USERS
                case 3: // if you input 3...
                    System.out.println("---\nPlease enter the ID of the user whose credentials you wish to modify.\n---"); // you will be asked for a user ID. This is an identifier for the user to modify
                    int userID = scanner.nextInt(); // the next int inserted will represent a user
                    User user = userDatabase.getUser(userID); // link the int/ID to the user
                    if (user == null) { // if you didn't use anyone's ID...
                        System.out.println("---\nUnfortunately, no such user has been located! Alas, not even down the back of the sofa."); // complaints come in
                    } else {
                        if (userID == admin.getUserID()) { // if you try to modify yourself...
                            System.out.println("---\nUnfortunately, you are not allowed to modify your own credentials! The high scriptures forbid it."); // you will not be allowed, because the brief doesn't allow it
                            break;
                        }
                        System.out.println("---\nAcknowledged.\nInput 1 to modify their username.\nInput 2 to modify their password.\nInput 3 to modify their role.\n---");
                        int choiceCredentials = scanner.nextInt(); // the next int will decide WHAT to modify
                        switch (choiceCredentials) {
                            case 1: // if you input 1...
                                System.out.println("---\nNow, please input the new username you wish to grant this user.\n---");
                                scanner.nextLine(); // clear input buffer
                                String newUsername = scanner.nextLine(); // the next input will become the new username
                                if (userDatabase.usernameExists(newUsername)) { // but if someone by that name already exists...
                                    System.out.println("---\nUnfortunately, this username already exists in the database! Two heads are more than one! Please choose another one."); // you will get nowhere
                                    break;
                                }
                                user.setUsername(newUsername); // otherwise, set that username for them
                                System.out.println("---\nTerrific! Their username has been updated. A change is as good as a rest.");
                                break;
                            case 2: // if you input 2...
                                System.out.println("---\nNow, please input the user's new password.\n---");
                                user.setPassword(scanner.next()); // the next input will become their password. Any input is valid
                                System.out.println("---\nFantastic! Their password has been changed. Play nice with this information!");
                                break;
                            case 3: // if you input 3...
                                boolean validRole = false; // we're going to keep checking for a valid role
                                System.out.println("---\nNow, please input the role of the new user. You may choose Admin, Office, or Lecturer.\n---");
                                while (!validRole) { // while NO valid role has been input...
                                    String newRole = scanner.next(); // the next input will represent the modified role
                                    switch (newRole.toLowerCase()) { // lettercase matters little
                                        case "admin":
                                        case "1":
                                            user.setRole("Admin"); // create the Admin
                                            validRole = true; // and end the loop
                                            System.out.println("---\nCallooh, callay! May the user revel in their new role, if possible.");
                                            break;
                                        case "office":
                                        case "2":
                                            user.setRole("Office"); // create the Office person
                                            validRole = true; // and end the loop
                                            System.out.println("---\nCallooh, callay! May the user revel in their new role, if possible.");
                                            break;
                                        case "lecturer":
                                        case "3":
                                            user.setRole("Lecturer"); // create the Lecturer
                                            validRole = true; // and end the loop
                                            System.out.println("---\nCallooh, callay! May the user revel in their new role, if possible.");
                                            break;
                                        default: // ...or create nobody and tell off the admin
                                            System.out.println("---\nUnfortunately, that is not a valid role! Please choose Admin, Office, or Lecturer.\n---");
                                    }
                                }
                                break;
                            default:
                                System.out.println("---\nCome, now! We both know that is not a valid option. Please input one of the available choices.");
                        }
                        userDatabase.updateUser(userID, user); // when all is finished with, update the user in question
                    }
                    break;
// TO DELETE USERS
                case 4: // if you input 4...
                    System.out.println("---\nPlease input the user ID of the user you wish to delete.\n---"); // give us the user ID of the individual. This is why IDs are made available to Admins
                    int deleteUserID = scanner.nextInt(); // the next int will represent the user to be deleted
                    User userToDelete = userDatabase.getUser(deleteUserID); // link the int/ID to the user
                    if (userToDelete == null) { // if you didn't manage to pinpoint anybody...
                        System.out.println("---\nImpossible! There is no such user. None where there's none wanted! Please choose an existing ID next time."); // you will be nagged
                    } else {
                        if (deleteUserID == admin.getUserID()) { // if you pinpointed yourself...
                            System.out.println("---\nLaughable! That is your own user ID! You are not permitted to delete your own account. Ho ho ho."); // you will be laughed at
                        } else {
                            userDatabase.deleteUser(deleteUserID); // in any other event, delete the user
                            System.out.println("---\nStupendous! The user has been removed from the database.");
                        }
                    }
                    break;
// TO LOG OUT
                case 5: // if you input 5...
                    logout(); // run the logout method
                    return;
                case 6: // if you input 6...
                    System.out.println("---\nFast times at Innistown College, eh? Enjoy your day, your week, your month, and your year. Byeeeeee!"); // you will get an over-the-top farewell...
                    System.exit(0); // and close it out
                default: // if you input nonsense...
                    System.out.println("---\nThat is not valid input. Perhaps you would care to give that another try? I have all day.\n"); // you will get nonsense back
            }
        } while (true);
    } 

    public void logout() {
        System.out.println("---\nYou have logged out. All too sad to see you go! On the bright side of life, there are many other users in the sea.");
        UserController controller = new ConsoleUserController(new ConsoleLoginView(), userDatabase); // get a new User Controller going
        controller.login(); // and return to the login screen
    }
}
