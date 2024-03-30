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
            System.out.println("---\nInput 1 to generate a report.\nInput 2 to change your username and password.\nInput 3 to log out.\nInput 4 to close the software.\n---");
            choice = scanner.nextInt(); // next int inserted defines your choice
            switch (choice) {
// TO GENERATE A REPORT
            case 1: // if you input 1...
            System.out.println("---\nAcknowledged. Now, select which type of report.\nInput 1 to generate a Course Report.\nInput 2 to generate a Lecturer Report.\nInput 3 to generate a Student Report.\n---");
                    String reportType = scanner.nextLine(); // next input will represent the report TYPE (Course, Lecturer etc) to output
            System.out.println("---\nUnderstood. Now, choose your format.\nInput 1 to output the report in CSV format.\nInput 2 to output it in TXT format.\nInput 3 to print the output to the console.\n---");
                    scanner.nextLine(); // clear input buffer
                    String outputFormat = scanner.nextLine(); // the next input will represent the format
                    String format;
                    switch (outputFormat.toLowerCase()) { // lettercase matters little
                        case "csv": // if the user enters csv...
                        case "1": // or 1...
                            format = "csv"; // then csv it is
                            break;
                        case "txt": // if the user enters txt...
                        case "2": // or 2...
                            format = "txt"; // then txt it is
                            break;
                        case "console": // if the user enters console...
                        case "3":  // or 3...
                            format = "console"; // then console it is
                            break;
                        default: // if you input nonsense...
                            System.out.println("\nThat is not a valid format! Printing to console in the absence of better ideas.");
                            format = "console";
                    }
                    switch (reportType.toLowerCase()) {
                        case "course": // if you had input course for type...
                        case "1": // or 1...
                            generateCourseReport(format); // generate a course report
                            break;
                        case "lecturer": // if you had input lecturer for type...
                        case "2": // if you had input 2 for type...
                            generateLecturerReport(format); // generate a lecturer report
                            break;
                        case "student": // if you had input student for type...
                        case "3": // or 3...
                            generateStudentReport(format); // generate a student report
                            break;
                        default: // if you input nonsense...
                            System.out.println("\nThat does not correspond to a valid report type!");
                    }
                    break;
// TO CHANGE YOUR USERNAME AND PASSWORD
                case 2: // if you input 2...
                    int activeOfficeID = office.getUserID(); // note the ID of the active user
                    User activeOffice = userDatabase.getUser(activeOfficeID);
                    System.out.println("---\nAcknowledged.\nInput 1 to modify your username.\nInput 2 to modify your password.");
                    System.out.println("---");
                    int choiceCredentials = scanner.nextInt(); // granular choice of which credential to modify
                    switch (choiceCredentials) {
                        case 1: // if you input 1...
                            System.out.println("---\nNow, please input the new username you would like to change over to.\n---");
                            scanner.nextLine(); // clear input buffer
                            String newUsername = scanner.nextLine(); // the next input will become your username
                            if (userDatabase.usernameExists(newUsername)) { // unless it already exists...
                                System.out.println("---\nImpossible! That username is already present in the database. Please choose another."); // in which case, your ears will burn
                            } else {
                                System.out.println("---\nSuperlative! Your username has been updated. Renewals within renewals.");
                                break;
                            }
                        case 2: // if you input 2...
                            System.out.println("---\nNow, please input your new password.\n---");
                            scanner.nextLine(); // clear input buffer
                            activeOffice.setPassword(scanner.nextLine()); // the next input will become your password. Anything works
                            System.out.println("---\nSplendid! Your password has been changed. Go forth and feel secure!");
                            break;
                        default:
                            System.out.println("---\nNonsense! That is not valid input. You must choose either your username (1), or password (2).\n---");
                    }
                    userDatabase.updateUser(activeOffice.getUserID(), activeOffice); // if all is well, update the user
                    break;
// TO LOG OUT
                case 3: // if the user inputs 3...
                    logout(); // ...run the logout method
                    return;
// TO CLOSE THE SOFTWARE
                case 4: // if the user inputs 4...
                    System.out.println("---\nFast times at Innistown College, eh? Enjoy your day, your week, your month, and your year. Byeeeeee!"); // ...bid them goodbye...
// IN ANY OTHER EVENT...
                default: // in the event of any other input...
                    System.out.println("---\nThat is not valid input. Perhaps you would care to give that another try? I have all day.\n"); // give back some cheek
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

    private void generateLecturerReport(String format) {
        LecturerReport lecturerReport = new LecturerReport(getFormatter(format)); // create a new Lecturer Report...
        lecturerReport.generateReport(format); // and use the format to generate it
    }

    private void generateStudentReport(String format) {
        StudentReport studentReport = new StudentReport(getFormatter(format)); // create a new Student Report...
        studentReport.generateReport(format); // and use the format to generate it
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
