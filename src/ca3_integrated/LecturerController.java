package ca3_integrated;

/*
// Dylan Geraghty - CA3 (Integrated CA) - Programming: Object-Oriented Approach
// ------------------
// https://github.com/Dyluvian/CA3_Integrated
// ------------------
// CLASS: USER CONTROLLER FOR LECTURER ROLE (LECTURERCONTROLLER)
// ------------------
// This class delves into the unique functionalities assigned to the Lecturer role, beyond the generic user coverage of the main User Controller.
// Specifically, it enables the Lecturer to view a Lecturer Report, modify their own username and password, log out, and close the program.
// ------------------
 */
import java.util.InputMismatchException;
import java.util.Scanner; // import Scanner for accepting user input

public class LecturerController implements UserController {

    private UserDatabase userDatabase; // initialise the database

    public LecturerController(UserDatabase userDatabase) {
        this.userDatabase = userDatabase; // relate the AdminController to the database
    }

    @Override
    public void login() { // Standard login
    }

    @Override
    public void showMenu(User user) {
        if (user instanceof Lecturer) { // if the user is a Lecturer...
            showLecturerMenu((Lecturer) user); // ...show the Lecturer menu
        }
    }

    public void showLecturerMenu(Lecturer lecturer) {
        Scanner scanner = new Scanner(System.in); // kickstart the scanner
        int choice; // initialise the choice integer for switch
        do {
            try {
                System.out.println("---\nInput 1 to generate a Lecturer report.\nInput 2 to change your username and password.\nInput 3 to log out.\nInput 4 to close the software.\n---"); // instructions
                String choiceInput = scanner.nextLine().trim();
                if (choiceInput.isEmpty()) {
                    System.out.println("---\nThat is not valid input. Perhaps you would care to give that another try? I have all day.");
                    continue;
                }
                choice = Integer.parseInt(choiceInput);
                switch (choice) {
// TO GENERATE A LECTURER REPORT
                    case 1: // if you input 1...
                        System.out.println("---\nAcknowledged. Now, choose your format.\nInput 1 to output the report in CSV format.\nInput 2 to output it in TXT format.\nInput 3 to print the output to the console.\n---");
                        int outputFormat;
                        String outputFormatInput = scanner.nextLine(); // the next input will represent the output format
                        if (outputFormatInput.isEmpty()) { // if no format entered...
                            System.out.println("---\nNo format has been entered. Printing to console by default.");
                            outputFormat = 3; // default to console output
                        }
                        try {
                            outputFormat = Integer.parseInt(outputFormatInput);
                        } catch (NumberFormatException e) {
                            System.out.println("---\nInvalid output format. Printing to console by default.");
                            outputFormat = 3; // default to console output
                        }
                        String format;
                        switch (outputFormat) { // lettercase matters little
                            case 1: // if 1...
                                format = "csv"; // then csv it is
                                break;
                            case 2: // if 2...
                                format = "txt"; // then txt it is
                                break;
                            case 3: // if 3...
                                format = "console"; // then console it is
                                break;
                            default: // if you input nonsense...
                                System.out.println("\nThat is not a valid format! Printing to console in the absence of better ideas.");
                                format = "console";
                        }
                        scanner.nextLine(); // clear it out first
                        System.out.println("---\nIndubitably. For authentication purposes, please enter your lecturer security password.\nNote that I am not referring to your password HERE - the security passwords are assigned to each lecturer in the MySQL database.\n---");
                        String password = scanner.nextLine(); // request the lecturer password
                        if (password.isEmpty()) {
                            System.out.println("---\nNo password has been entered. Returning you to the menu.");
                            break;
                        }
                        generateLecturerReport(format, password); // generate a lecturer report
                        break;
// TO CHANGE YOUR USERNAME AND PASSWORD
                    case 2: // if you input 2...
                        int activeLecturerID = lecturer.getUserID(); // note the active lecturer's ID
                        User activeLecturer = userDatabase.getUser(activeLecturerID);
                        System.out.println("---\nAcknowledged.\nInput 1 to modify your username.\nInput 2 to modify your password."); // choose which action
                        System.out.println("---");
                        String choiceCredentialsInput = scanner.nextLine(); // granular choice of which credential to modify
                        if (choiceCredentialsInput.isEmpty()) { // check if no input is provided
                            System.out.println("---\nNonsense! That is not valid input. You must choose either your username (1), or password (2).");
                            break;
                        }
                        try {
                            int choiceCredentials = Integer.parseInt(choiceCredentialsInput); // parse input as an integer
                            switch (choiceCredentials) {
                                case 1: // if you input 1...
                                    System.out.println("---\nNow, please input the new username you would like to change over to.\n---");
                                    String newUsername = scanner.nextLine(); // the next input will become your username
                                    if (newUsername.isEmpty()) { // if no input is provided...
                                        System.out.println("---\nThat is not valid username input. Perhaps you would care to give that another try? I have all day."); // ...complain
                                        break;
                                    }
                                    if (userDatabase.usernameExists(newUsername)) { // unless it already exists...
                                        System.out.println("---\nImpossible! That username is already present in the database. Please choose another."); // in which case, your ears will burn
                                    } else {
                                        System.out.println("---\nSuperlative! Your username has been updated. Renewals within renewals.");
                                        activeLecturer.setUsername(newUsername); // if all is well, update the username
                                        break;
                                    }
                                case 2: // if you input 2...
                                    System.out.println("---\nNow, please input your new password.\n---");
                                    String newPassword = scanner.nextLine(); // the next input will become your password. Anything works
                                    if (newPassword.isEmpty()) { // ...except for no input
                                        System.out.println("---\nThat is not valid password input. Perhaps you would care to give that another try? I have all day.");
                                        break;
                                    }
                                    activeLecturer.setPassword(newPassword); // set the new password
                                    System.out.println("---\nSplendid! Your password has been changed. Go forth and feel secure!");
                                    break;
                                default: // if you input nonsense...
                                    System.out.println("---\nNonsense! That is not valid input. You must choose either your username (1), or password (2).");
                            }
                            userDatabase.updateUser(activeLecturer.getUserID(), activeLecturer); // finally, update the active office...
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("---\nNonsense! That is not valid input. You must choose either your username (1), or password (2).");
                            break;
                        }
// TO LOG OUT
                    case 3: // if the user inputs 3...
                        logout(); // ...run the logout method
                        return;
// TO CLOSE THE SOFTWARE
                    case 4: // if the user inputs 4...
                        System.out.println("---\nFast times at Innistown College, eh? Enjoy your day, your week, your month, and your year. Byeeeeee!"); // ...bid them goodbye...
// IN ANY OTHER EVENT...
                    default: // in the event of any other input...
                        System.out.println("---\nThat is not valid input. Perhaps you would care to give that another try? I have all day."); // give back some cheek
                }
            } catch (NumberFormatException e) {
                System.out.println("---\nThat is not valid input. Perhaps you would care to give that another try? I have all day.\n---");
            } catch (InputMismatchException e) { // catch anything else
                scanner.nextLine();
                System.out.println("---\nThat is not valid input. Perhaps you would care to give that another try? I have all day.");
            }
        } while (true); // run in perpetuity, until input is found
    }

    public void logout() {
        System.out.println("---\nYou have logged out. There is a famous Kurt Vonnegut quote: 'so it goes.' Go well!");
        UserController controller = new ConsoleUserController(new ConsoleLoginView(), userDatabase); // get a new User Controller going
        controller.login(); // and return to the login screen
    }

    private void generateLecturerReport(String format, String password) {
        String authType = "password"; // we are using passwords to verify the lecturers when they want their reports
        String authInfo = password;
        LecturerReport lecturerReport = new LecturerReport(getFormatter(format));
        lecturerReport.generateReport(format, authType, authInfo); // send the input to the generator
    }

    private ReportFormatter getFormatter(String format) {
        switch (format) { // get the right formatter based on the format inserted
            case "csv": // if csv is input...
                return new CSVReportFormatter(); // use the CSV formatter
            case "txt": // if txt is input...
                return new TextReportFormatter(); // use the TXT formatter
            case "console": // if console is input...
                return new ConsoleReportFormatter(); // use the console formatter
            default: // if nothing valid is input...
                throw new IllegalArgumentException("---\nUnfortunately, no valid formatter was found."); // ...lodge a complaint
        }
    }
}
