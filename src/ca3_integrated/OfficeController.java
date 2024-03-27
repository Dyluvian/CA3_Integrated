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
                    userDatabase.updateUser(activeLecturer.getUserID(), activeOffice);
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
