package ca3_integrated;

import java.util.ArrayList;
import java.util.Iterator;
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

    public User getUser(int userID) {
        for (User user : users) {
            if (user.getUserID() == userID) {
                return user;
            }
        }
        return null;
    }

    public void printAllUsers() {
        Iterator<User> iterateUsers = users.iterator();
        while (iterateUsers.hasNext()) {
            User userToIterate = iterateUsers.next();
            System.out.println("User ID: " + userToIterate.getUserID() + ". Username: " + userToIterate.getUsername() + ". Password: " + userToIterate.getPassword() + ". Role: " + userToIterate.getRole());
        }
    }

    public void updateUser(int userID, User newUser) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserID() == userID) {
                users.set(i, newUser);
                return;
            }
        }
    }

    public void deleteUser(int userIDToDelete) {
        boolean userDeleted = false;
        Iterator<User> iterateUsers = users.iterator();
        while (iterateUsers.hasNext()) {
            User user = iterateUsers.next();
            if (user.getUserID() == userIDToDelete) {
                iterateUsers.remove();
                userDeleted = true;
            } else if (user.getUserID() > userIDToDelete && userDeleted) {
                ((Staff) user).decrementUserID();
            }
        }
    }

    public User verifyUser(String username, String password) {
        if (users == null) {
            return null;
        }
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}
