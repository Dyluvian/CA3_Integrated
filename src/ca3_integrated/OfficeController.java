package ca3_integrated;
/*
// Dylan Geraghty - CA3 (Integrated CA) - Programming: Object-Oriented Approach
// ------------------
// https://github.com/Dyluvian/CA3_Integrated
// ------------------
// CLASS: USER CONTROLLER FOR OFFICE ROLE (OFFICECONTROLLER)
// ------------------
// 
// ------------------
*/

import java.util.Scanner;

public class OfficeController implements UserController {

    private UserDatabase userDatabase;

    public OfficeController(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }
    
    @Override
    public void login() {
    }

    @Override
    public void showMenu(User user) {
        if (user instanceof Office) {
            showOfficeMenu((Office) user);
        }
    }

    public void showOfficeMenu(Office office) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("---\nInput 1 to do nothing for now.\nInput 2 to change your username and password.\nInput 3 to log out.\nInput 4 to close the software.\n---");
            choice = scanner.nextInt();
            switch (choice) {
// TO GENERATE A REPORT?
            case 1:
            System.out.println("---\nAcknowledged.\nInput 1 to generate a Course Report.\nInput 2 to generate a Lecturer Report.\nInput 3 to generate a Student Report..\n---");
                    int reportType = scanner.nextInt();
                    scanner.nextLine();
            System.out.println("---\nUnderstood.\nInput 1 to output the report in CSV format.\nInput 2 to output it in TXT format.\nInput 3 to print the output to the console.\n---");
                    int outputFormat = scanner.nextInt();
                    String format;
                    switch (outputFormat) {
                        case 1:
                            format = "csv";
                            break;
                        case 2:
                            format = "txt";
                            break;
                        case 3:
                            format = "console";
                            break;
                        default:
                            System.out.println("\nThat is not a valid format! Printing to console in the absence of better ideas.");
                            format = "console";
                    }
                    switch (reportType) {
                        case 1:
                            generateCourseReport(format);
                            break;
                        case 2:
                            generateLecturerReport(format);
                            break;
                        case 3:
                            generateStudentReport(format);
                            break;
                        default:
                            System.out.println("\nThat does not correspond to a valid report type!");
                    }
                    break;
// TO CHANGE YOUR USERNAME AND PASSWORD
                case 2:
                    int activeOfficeID = office.getUserID();
                    User activeOffice = userDatabase.getUser(activeOfficeID);
                    System.out.println("---\nAcknowledged.\nInput 1 to modify your username.\nInput 2 to modify your password.");
                    System.out.println("---");
                    int choiceCredentials = scanner.nextInt();
                    switch (choiceCredentials) {
                        case 1:
                            System.out.println("---\nNow, please input the new username you would like to change over to.\n---");
                            String newUsername = scanner.next();
                            if (userDatabase.usernameExists(newUsername)) {
                                System.out.println("---\nSuperlative! Your username has been updated. Renewals within renewals.");
                                break;
                            }
                            activeOffice.setUsername(newUsername);
                            System.out.println("---\nYour username has been updated successfully.\n---");
                            break;
                        case 2:
                            System.out.println("---\nNow, please input your new password.\n---");
                            activeOffice.setPassword(scanner.next());
                            System.out.println("---\nSplendid! Your password has been changed. Go forth and feel secure!");
                            break;
                        default:
                            System.out.println("---\nNonsense! That is not valid input. You must choose either your username (1), or password (2).\n---");
                    }
                    userDatabase.updateUser(activeOffice.getUserID(), activeOffice);
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
        UserController controller = new ConsoleUserController(new ConsoleLoginView(), userDatabase);
        controller.login();
    }
}
