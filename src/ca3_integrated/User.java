package ca3_integrated;

/*
// Dylan Geraghty - CA3 (Integrated CA) - Programming: Object-Oriented Approach
// ------------------
// https://github.com/Dyluvian/CA3_Integrated
// ------------------
// INTERFACE: USER
// ------------------
// 
// ------------------
*/

public interface User {
    
    int getUserID();
    
    void decrementUserID();

    String getUsername();
    
    void setUsername(String username);
    
    String getPassword();
    
    void setPassword(String password);

    String getRole();
    
    void setRole(String role);
}
