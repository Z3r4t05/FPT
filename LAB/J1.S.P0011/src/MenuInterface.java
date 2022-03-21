
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ADMIN
 */
public class MenuInterface {
    public static void displayMenu(int option) {
        switch(option) {
            case 0:
                System.out.println("Choose input base");
                System.out.println("1. Binary");
                System.out.println("2. Decimal");
                System.out.println("3. Hexadecimal");
                System.out.println("4. Exit");
                break;
            case 2:
                System.out.println("Choose output base");
                System.out.println("1. Decimal");
                System.out.println("2. Hexadecimal");
                break;
            case 10:
                System.out.println("Choose output base");
                System.out.println("1. Binary");
                System.out.println("2. Hexadecimal");
                break;
            case 16:
                System.out.println("Choose output base");
                System.out.println("1. Binary");
                System.out.println("2. Decimal");
                break;
        }
    }
    
    public static void displayResult(String result) {
        System.out.println("Result: " + result);
    }
    
    
}
