package ca3_integrated;

/*
// Dylan Geraghty - CA3 (Integrated CA) - Programming: Object-Oriented Approach
// ------------------
// https://github.com/Dyluvian/CA3_Integrated
// ------------------
// CLASS: USER CONTROLLER FOR LECTURER ROLE (LECTURERCONTROLLER)
// ------------------
// 
// ------------------
*/

import java.util.Scanner;

public class LecturerController implements UserController {

    private UserDatabase userDatabase;

    public LecturerController(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }
    
    @Override
    public void login() {
    }
    