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