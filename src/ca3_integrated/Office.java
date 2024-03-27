package ca3_integrated;

/*
// Dylan Geraghty - CA3 (Integrated CA) - Programming: Object-Oriented Approach
// ------------------
// https://github.com/Dyluvian/CA3_Integrated
// ------------------
// CLASS: OFFICE, SUBCLASS OF STAFF
// ------------------
// This class represents the Office role, referring back to the Staff superclass which it extends.
// Nothing unique has been prescribed for the Office(r?) or other roles in the brief for this project, save the menu they access.
// ------------------
*/

public class Office extends Staff {

// The Office constructor.
    public Office(int userID, String username, String password) { // Office accepts a numerical user ID, and username and password strings
        super(userID, username, password, "Office"); // Return the variable ID, username, and password to Staff, along with the fixed role, Office
    }
}