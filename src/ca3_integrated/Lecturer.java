package ca3_integrated;

/*
// Dylan Geraghty - CA3 (Integrated CA) - Programming: Object-Oriented Approach
// ------------------
// https://github.com/Dyluvian/CA3_Integrated
// ------------------
// CLASS: LECTURER, SUBCLASS OF STAFF
// ------------------
// This class represents the Lecturer role, referring back to the Staff superclass which it extends.
// Nothing unique has been prescribed for the Lecturer or other roles in the brief for this project, save the menu they access.
// ------------------
*/

public class Lecturer extends Staff {

// The Lecturer constructor.
    public Lecturer(int userID, String username, String password) { // Lecturer accepts a numerical user ID, and username and password strings
        super(userID, username, password, "Lecturer"); // Return the variable ID, username, and password to Staff, along with the fixed role, Lecturer
    }
}