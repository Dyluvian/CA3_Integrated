package ca3_integrated;

public class Admin extends Staff {

    public Admin(int userID, String username, String password) {
        super(userID, username, password, "Admin");
    }
}