package ca3_integrated;

public class Admin extends Staff {

/*
// Dylan Geraghty - CA3 (Integrated CA) - Programming: Object-Oriented Approach
// ------------------
// https://github.com/Dyluvian/CA3_Integrated
// ------------------
// CLASS: ADMIN, SUBCLASS OF STAFF
// ------------------
// This class represents the Admin role, referring back to the Staff superclass which it extends.
// Nothing unique has been prescribed for the Admin or other roles in the brief for this project, save the menu they access.
// ------------------
*/

// The Admin constructor.
    public Admin(int userID, String username, String password) { // Admin accepts a numerical user ID, and username and password strings
        super(userID, username, password, "Admin"); // Return the variable ID, username, and password to Staff, along with the fixed role, Admin
    }
}