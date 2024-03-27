package ca3_integrated;

/*
// Dylan Geraghty - CA3 (Integrated CA) - Programming: Object-Oriented Approach
// ------------------
// https://github.com/Dyluvian/CA3_Integrated
// ------------------
// CLASS: STAFF
// ------------------
// 
// ------------------
*/
    
public abstract class Staff implements User {
    
    private static int lastAssignedUserID = 0;

    private int userID;
    private String username;
    private String password;
    private String role;

    public Staff(int userID, String username, String password, String role) {
        this.userID = ++lastAssignedUserID;
        this.username = username;
        this.password = password;
        this.role = role;
    }
    
    @Override
    public int getUserID() {
        return userID;
    }
    
}
