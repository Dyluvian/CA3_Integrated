package ca3_integrated;

/*
// Dylan Geraghty - CA3 (Integrated CA) - Programming: Object-Oriented Approach
// ------------------
// https://github.com/Dyluvian/CA3_Integrated
// ------------------
// CLASS: CONSOLE FOR USER CONTROLLER
// ------------------
// 
// ------------------
*/

public class ConsoleUserController implements UserController {
    private LoginView loginView;
    private UserDatabase userDatabase;

    public ConsoleUserController(LoginView loginView, UserDatabase userDatabase) {
        this.loginView = loginView;
        this.userDatabase = userDatabase;
    }
}




@Override
public void showMenu(User user) {
    if (user instanceof Admin) {
        AdminController adminController = new AdminController(userDatabase);
        adminController.showMenu((Admin) user);
    } else if (user instanceof Office) {
        OfficeController officeController = new OfficeController(userDatabase);
        officeController.showMenu((Office) user);
    } else if (user instanceof Lecturer) {
        LecturerController lecturerController = new LecturerController(userDatabase);
        lecturerController.showMenu((Lecturer) user);
    } else {
        System.out.println("???????????????????");
    }
}

    @Override
    public void logout() {
        System.out.println("INSERT LOGOUT TEXT");
        login();
    }
}