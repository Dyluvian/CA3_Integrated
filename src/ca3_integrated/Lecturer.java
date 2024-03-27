package ca3_integrated;

/*
// Dylan Geraghty - CA3 (Integrated CA) - Programming: Object-Oriented Approach
// ------------------
// https://github.com/Dyluvian/CA3_Integrated
// ------------------
// CLASS: LECTURER, SUBCLASS OF STAFF
// ------------------
//
// ------------------
*/

public class Lecturer extends Staff {

    public Lecturer(int userID, String username, String password) {
        super(userID, username, password, "Lecturer");
    }
}