package ca3_integrated;

/*
// Dylan Geraghty - CA3 (Integrated CA) - Programming: Object-Oriented Approach
// ------------------
// https://github.com/Dyluvian/CA3_Integrated
// ------------------
// CLASS: STAFF
// ------------------
// Staff is the abstract class behind the User represented by an interface: staff are the actual individuals holding parameters.
// The Admin, Office, and Lecturer are all subclasses of the dominant Staff class. All relevant parameters for those classes exist here.
// ------------------
 */

public abstract class Staff implements User {

    public static int lastAssignedUserID = 0; // set this to zero so that when the first Admin is instantiated, it becomes user ID 1

    private int userID; // setting up variables/paramaters
    private String username;
    private String password;
    private String role;

    public Staff(int userID, String username, String password, String role) {
        this.userID = ++lastAssignedUserID; // next user ID is incremented when a new user is created
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @Override
    public int getUserID() { // getter for user ID
        return userID;
    }

    @Override
    public void decrementUserID() { // to decrement the user ID, whenever a user is deleted
        userID--;
    }

    @Override
    public String getUsername() { // getter for username
        return username;
    }

    @Override
    public void setUsername(String username) { // setter for username
        this.username = username;
    }

    @Override
    public String getPassword() { // getter for password
        return password;
    }

    @Override
    public void setPassword(String password) { // setter for password
        this.password = password;
    }

    @Override
    public String getRole() { // getter for role
        return role;
    }
    
    @Override    
    public void setRole(String role) { // setter for role
        this.role = role;
    }
}
