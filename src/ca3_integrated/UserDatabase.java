package ca3_integrated;

import java.util.ArrayList;
import java.util.List;

public class UserDatabase {

    private List<User> users;

    public UserDatabase() {
        users = new ArrayList<>();
        users.add(new Admin(1, "admin", "java"));
    }
    
    public void addUser(User user) {
        users.add(user);
    }
    
}