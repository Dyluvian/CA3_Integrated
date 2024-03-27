package ca3_integrated;

/*
// Dylan Geraghty - CA3 (Integrated CA) - Programming: Object-Oriented Approach
// ------------------
// https://github.com/Dyluvian/CA3_Integrated
// ------------------
// CLASS: CONSOLE FOR LOGIN VIEW
// ------------------
// 
// ------------------
*/

import java.util.Scanner;

public class ConsoleLoginView implements LoginView {

    private Scanner scanner;

    public ConsoleLoginView() {
        scanner = new Scanner(System.in);
    }

    @Override
    public void displayLoginPrompt() {
        System.out.println("---\nTo log in and perform operations, please begin by inputting your username.\n---");
    }