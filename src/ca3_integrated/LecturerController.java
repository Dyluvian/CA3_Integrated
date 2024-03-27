package ca3_integrated;

/*
// Dylan Geraghty - CA3 (Integrated CA) - Programming: Object-Oriented Approach
// ------------------
// https://github.com/Dyluvian/CA3_Integrated
// ------------------
// CLASS: USER CONTROLLER FOR LECTURER ROLE (LECTURERCONTROLLER)
// ------------------
// 
// ------------------
*/

import java.util.Scanner;

public class LecturerController implements UserController {

    private UserDatabase userDatabase;

    public LecturerController(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }
    
    @Override
    public void login() {
    }
    
    @Override
    public void showMenu(User user) {
        if (user instanceof Lecturer) {
            showLecturerMenu((Lecturer) user);
        }
    }
    
    public void showLecturerMenu(Lecturer lecturer) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("---\nInput 1 to do nothing for now.\nInput 2 to change your username and password.\nInput 3 to log out.\nInput 4 to close the software.\n---");
            choice = scanner.nextInt();
            switch (choice) {