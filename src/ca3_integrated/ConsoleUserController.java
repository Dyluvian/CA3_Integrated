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
    public void logout() {
        System.out.println("INSERT LOGOUT TEXT");
        login();
    }
}