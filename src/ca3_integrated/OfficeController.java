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