package ca3_integrated;

/*
// Dylan Geraghty - CA3 (Integrated CA) - Programming: Object-Oriented Approach
// ------------------
// https://github.com/Dyluvian/CA3_Integrated
// ------------------
// CLASS: USER CONTROLLER FOR OFFICE ROLE (OFFICECONTROLLER)
// ------------------
// This class delves into the unique functionalities assigned to the Office role, beyond the generic user coverage of the main User Controller.
// Specifically, it enables the Office...r to generate reports, modify their own username and password, log out, and close the program.
// ------------------
 */
import java.util.InputMismatchException;
import java.util.Scanner;

public class OfficeController implements UserController { // import Scanner for accepting user input

    private UserDatabase userDatabase; // initialise the database

    public OfficeController(UserDatabase userDatabase) {
        this.userDatabase = userDatabase; // relate the OfficeController to the database
    }

    @Override
    public void login() { // Standard login
    }

    @Override
    public void showMenu(User user) {
        if (user instanceof Office) { // if the user is an Office... guy... or... not so guy...
            showOfficeMenu((Office) user); // ...show the Office menu
        }
    }

    public void showOfficeMenu(Office office) {
        Scanner scanner = new Scanner(System.in); // kickstart the scanner
        int choice; // initialise the choice integer for switch
        do {
            try {
                System.out.println("---\nInput 1 to generate a report.\nInput 2 to change your username and password.\nInput 3 to log out.\nInput 4 to close the software.\n---");
                String choiceInput = scanner.nextLine().trim();
                if (choiceInput.isEmpty()) {
                    System.out.println("---\nThat is not valid input. Perhaps you would care to give that another try? I have all day.");
                    continue;
                }
                choice = Integer.parseInt(choiceInput);
                switch (choice) {
// TO GENERATE A REPORT
                    case 1: // if you input 1...
                        System.out.println("---\nAcknowledged. Now, select which type of report.\nInput 1 to generate a Course Report.\nInput 2 to generate a Lecturer Report.\nInput 3 to generate a Student Report.\n---");
                        String reportTypeInput = scanner.nextLine(); // next input will represent the report TYPE (Course, Lecturer etc) to output
                        if (reportTypeInput.isEmpty()) {
                            System.out.println("---\nThat was not valid input, unfortunately.");
                            break;
                        }
                        int reportType;
                        try {
                            reportType = Integer.parseInt(reportTypeInput);
                        } catch (NumberFormatException e) {
                            System.out.println("---\nThat was not valid input, unfortunately.");
                            break;
                        }
                        System.out.println("---\nUnderstood. Now, choose your format.\nInput 1 to output the report in CSV format.\nInput 2 to output it in TXT format.\nInput 3 to print the output to the console.\n---");
                        int outputFormat;
                        String outputFormatInput = scanner.nextLine(); // the next input will represent the format
                        if (outputFormatInput.isEmpty()) {
                            System.out.println("---\nNo format has been entered. Printing to console by default.\n---"); // no format is not valid
                            outputFormat = 3; // default to console output
                        } else {
                            try {
                                outputFormat = Integer.parseInt(outputFormatInput);
                            } catch (NumberFormatException e) {
                                System.out.println("---\nThat is not a valid format. Printing to console by default.\n---");
                                outputFormat = 3; // default to console output
                            }
                        }
                        String format;
                        switch (outputFormat) { // letter case matters little
                            case 1: // or 1...
                                format = "csv"; // then csv it is
                                break;
                            case 2: // or 2...
                                format = "txt"; // then txt it is
                                break;
                            case 3: // or 3...
                                format = "console"; // then console it is
                                break;
                            default: // if you input nonsense...
                                System.out.println("\nThat is not a valid format! Printing to console in the absence of better ideas.");
                                format = "console";
                        }
                        switch (reportType) {
                            case 1: // or 1...
                                generateCourseReport(format); // generate a course report
                                break;
                            case 2: // if you had input 2 for type...
                                System.out.println("---\nNow, please enter the lecturer ID of the lecturer in question:\n---");
                                String lecturerIDInput = scanner.nextLine(); // request the lecturer ID
                                if (lecturerIDInput.isEmpty()) {
                                    System.out.println("---\nNo lecturer ID has been entered. Returning you to the menu.");
                                    break;
                                }
                                int lecturerID;
                                try {
                                    lecturerID = Integer.parseInt(lecturerIDInput);
                                } catch (NumberFormatException e) {
                                    System.out.println("---\nThat is not valid input for the lecturer ID. Returning you to the menu.");
                                    break;
                                }
                                generateLecturerReport(format, lecturerID); // generate a lecturer report
                                break;
                            case 3: // or 3...
                                System.out.println("---\nNow, please enter the student ID of the student in question:\n---");
                                String studentIDInput = scanner.nextLine(); // request the student ID
                                if (studentIDInput.isEmpty()) {
                                    System.out.println("---\nNo student ID has been entered. Returning you to the menu.");
                                    break;
                                }
                                int studentID;
                                try {
                                    studentID = Integer.parseInt(studentIDInput);
                                } catch (NumberFormatException e) {
                                    System.out.println("---\nThat is not valid input for the student ID. Returning you to the menu.");
                                    break;
                                }
                                generateStudentReport(format, studentID); // generate a student report
                                break;
                            default: // if you input nonsense...
                                System.out.println("---\nThat does not correspond to a valid report type!");
                        }
                        break;
// TO CHANGE YOUR USERNAME AND PASSWORD
                    case 2: // if you input 2...
                        int activeOfficeID = office.getUserID(); // note the active office person's ID
                        User activeOffice = userDatabase.getUser(activeOfficeID);
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
                                        activeOffice.setUsername(newUsername); // if all is well, update the username
                                        break;
                                    }
                                case 2: // if you input 2...
                                    System.out.println("---\nNow, please input your new password.\n---");
                                    String newPassword = scanner.nextLine(); // the next input will become your password. Anything works
                                    if (newPassword.isEmpty()) { // ...except for no input
                                        System.out.println("---\nThat is not valid password input. Perhaps you would care to give that another try? I have all day.");
                                        break;
                                    }
                                    activeOffice.setPassword(newPassword); // set the new password
                                    System.out.println("---\nSplendid! Your password has been changed. Go forth and feel secure!");
                                    break;
                                default: // if you input nonsense...
                                    System.out.println("---\nNonsense! That is not valid input. You must choose either your username (1), or password (2).");
                            }
                            userDatabase.updateUser(activeOffice.getUserID(), activeOffice); // finally, update the active office...
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
            } catch (InputMismatchException e) { // catch anything else
                scanner.nextLine();
                System.out.println("---\nThat is not valid input. Perhaps you would care to give that another try? I have all day.");
            }
        } while (true); // run in perpetuity, until input is found
    }

    public void logout() {
        System.out.println("---\nYou have logged out. Luckily, Innistown College has invested in automatic doors, which will not hit you on the way out.");
        UserController controller = new ConsoleUserController(new ConsoleLoginView(), userDatabase); // get a new User Controller going
        controller.login(); // and return to the login screen
    }

    private void generateCourseReport(String format) {
        CourseReport courseReport = new CourseReport(getFormatter(format)); // create a new Course Report...
        courseReport.generateReport(format); // and use the format to generate it
    }

    private void generateLecturerReport(String format, int lecturerID) {
        String authType = "lecturerID"; // we are using lecturer IDs here for the Office user
        String authInfo = String.valueOf(lecturerID); // convert the ID to a string
        LecturerReport lecturerReport = new LecturerReport(getFormatter(format));
        lecturerReport.generateReport(format, authType, authInfo); // and send it to be generated
    }

    private void generateStudentReport(String format, int studentID) {
        StudentReport studentReport = new StudentReport(getFormatter(format)); // create a new Student Report...
        studentReport.generateReport(format, studentID); // and use the format and student ID to generate it
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
