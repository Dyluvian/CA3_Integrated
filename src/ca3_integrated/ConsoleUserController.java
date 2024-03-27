package ca3_integrated;

/*
// Dylan Geraghty - CA3 (Integrated CA) - Programming: Object-Oriented Approach
// ------------------
// https://github.com/Dyluvian/CA3_Integrated
// ------------------
// CLASS: CONSOLE FOR USER CONTROLLER (CONSOLEUSERCONTROLLER)
// ------------------
// The ConsoleUserController class is the implementation for the User Controller interface, containing the logic of core login and menu methods.
// ------------------
 */
public class ConsoleUserController implements UserController {

    private LoginView loginView; // This object represents the login interface, which will run the login method, output the login prompt, and run logout.
    private UserDatabase userDatabase; // This object represents the database of users affiliated with the college.

// The main constructor, which accepts a Login View and a User Database as parameters (i.e. THE Login View and Database, as only one of each is required).
    public ConsoleUserController(LoginView loginView, UserDatabase userDatabase) {
        this.loginView = loginView; // Initialise a Login View object. The variable name is nondescript, as only one Login View is necessary.
        this.userDatabase = userDatabase; // Initialise a User Database object. The variable name is nondescript, as only one user database will exist for the college.
    }

// Core method for logging the user in, fed back to the User Controller. Please note that this is not only extensible to new roles; it also accommodates the LACK of a role.
    @Override
    public void login() {
        boolean loggedIn = false; // Initially, no user is logged in
        while (!loggedIn) { // As long as no user is currently logged in...
            loginView.displayLoginPrompt(); // ...display the prompt for users to log in
            String username = loginView.getUserInput(); // Username shall be the username obtained via Login View
            String password = loginView.getPasswordInput(); // Password will be similar
            User user = userDatabase.verifyUser(username, password); // Check the user's credentials against the User Database
            if (user != null) { // If any valid user is detected (i.e. the user is not null)...
                System.out.println("---\nValidated! Welcome, " + user.getUsername() + "."); // ...welcome aboard
                UserController controller; // The generic User Controller, to be made less generic
                switch (user.getRole()) { // use switch case to assign the appropriate menu to the respective user role
                    case "Admin": // in the event that the user possesses the Admin role...
                        controller = new AdminController(userDatabase); // ...invoke an Admin Controller for the user, where the Admin menu is available...
                        System.out.println("You hold the Admin role."); // ...and inform them of their status
                        break;
                    case "Office": // in the event that the user possesses the Office role...
                        controller = new OfficeController(userDatabase); // invoke an Office Controller for users of the Office role, where the Office menu is available
                        System.out.println("You hold the Office role."); // ...and inform them of their status
                        break;
                    case "Lecturer": // in the event that the user possesses the Lecturer role...
                        controller = new LecturerController(userDatabase); // invoke an Office Controller for users of the Admin role, where the Office menu is available
                        System.out.println("You hold the Lecturer role."); // ...and inform them of their status
                        break;
                    default: // in any other event...
                        System.out.println("You do not appear to hold a valid role."); // advise the user that they possess no known role
                        continue;
                }
                controller.showMenu(user); // Show the respective menu
                loggedIn = true; // finally, set login status to true to break this loop
            } else {
                System.out.println("Invalid username or password is invalid. Please try again."); // If the user could not be validated, better luck next time
            }
        }
    }

    @Override
    public void showMenu(User user) {
        if (user instanceof Admin) { // If the user is an Admin...
            AdminController adminController = new AdminController(userDatabase); // ...create a new Admin Controller...
            adminController.showMenu((Admin) user); // ...and show its menu to the Admin
        } else if (user instanceof Office) { // If the user is an Office user...
            OfficeController officeController = new OfficeController(userDatabase); // ...create a new Office Controller...
            officeController.showMenu((Office) user); // ...and show its menu to the Office person
        } else if (user instanceof Lecturer) { // If the user is a Lecturer...
            LecturerController lecturerController = new LecturerController(userDatabase); // ...create a new Lecturer Controller...
            lecturerController.showMenu((Lecturer) user); // ...and show its menu to the Lecturer
        } // This is extensible for future roles
    }

    @Override
    public void logout() {
        login(); // After the individual logs out, just prompt them to log in again!
    }
}