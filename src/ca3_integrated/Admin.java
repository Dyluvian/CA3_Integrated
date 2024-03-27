package ca3_integrated;

public class Admin extends Staff {

/*
// Dylan Geraghty - CA3 (Integrated CA) - Programming: Object-Oriented Approach
// ------------------
// https://github.com/Dyluvian/CA3_Integrated
// ------------------
// CLASS: ADMIN, SUBCLASS OF STAFF
// ------------------
//
// ------------------
*/

    public Admin(int userID, String username, String password) {
        super(userID, username, password, "Admin");
    }
}