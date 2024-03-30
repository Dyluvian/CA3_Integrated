package ca3_integrated;

/*
// Dylan Geraghty - CA3 (Integrated CA) - Programming: Object-Oriented Approach
// ------------------
// https://github.com/Dyluvian/CA3_Integrated
// ------------------
// CLASS: USER DATABASE
// ------------------
// The User Database class handles most of the nitty gritty related to the *set* of users: addition, deletion, listing, modification/updating, verification, etc.
// ------------------
*/

import java.util.ArrayList; // for holding the users
import java.util.Iterator; // for iterating through the users, when printing names
import java.util.List; // for listing the users

public class UserDatabase {

    private List<User> users; // list of users

    public UserDatabase() {
        users = new ArrayList<>(); // array list for users
        users.add(new Admin(1, "admin", "java")); // The initial admin prescribed in the brief
    }

    public void addUser(User user) {
        users.add(user); // to add a user
    }

    public User getUser(int userID) {
        for (User user : users) { // regarding the list of users...
            if (user.getUserID() == userID) { // if a user's ID is a match...
                return user; // return that user
            }
        }
        return null; // otherwise nothing
    }

    public void printAllUsers() {
        Iterator<User> iterateUsers = users.iterator(); // get an iterator going for the users
        while (iterateUsers.hasNext()) { // while there is another user to iterate through...
            User userToIterate = iterateUsers.next(); // iterate through them
            System.out.println("User ID: " + userToIterate.getUserID() + ". Username: " + userToIterate.getUsername() + ". Password: " + userToIterate.getPassword() + ". Role: " + userToIterate.getRole()); // and print this information
        }
    }

    public void updateUser(int userID, User newUser) {
        for (int i = 0; i < users.size(); i++) { // run through the list of users
            if (users.get(i).getUserID() == userID) { // if a user's ID matches the user to be modified...
                users.set(i, newUser); // ...assign the new data to that user
                return;
            }
        }
    }

    public void deleteUser(int userIDToDelete) {
        boolean userDeleted = false; // if the user has not yet been deleted...
        Iterator<User> iterateUsers = users.iterator(); // iterate through the list of users
        while (iterateUsers.hasNext()) { // while there is another user in the list...
            User user = iterateUsers.next(); // continue to iterate to the next user
            if (user.getUserID() == userIDToDelete) { // if a user's ID matches the ID submitted...
                iterateUsers.remove(); // remove 'em
                userDeleted = true; // set the deletion to true to break the loop
            } else if (user.getUserID() > userIDToDelete && userDeleted) { // otherwise, if a user's ID is greater than the deletable user's and they have been deleted...
                ((Staff) user).decrementUserID(); // decrement their ID
            }
        }
        if (userDeleted) { // if a user is deleted...
            Staff.lastAssignedUserID--; // decrement the last assigned user ID, so that there will be no numerical gaps in the IDs
        }
    }

    public User verifyUser(String username, String password) { // verification for login
        if (users == null) { // in the unlikely(impossible?) event there are no users...
            return null; // return nothing
        }
        for (User user : users) { // check the users
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) { // if user's username and password match...
                return user; // the user is valid
            }
        }
        return null; // in any other case, nothing
    }
    
    public boolean usernameExists(String username) { // yes or no, does the user exist (used to avoid overlap when modifying)
        for (User user : users) { // check the users
            if (user.getUsername().equals(username)) { // if their usernames equal the username at hand... 
                return true; // yes
            }
        }
        return false; // otherwise no
    }
}
