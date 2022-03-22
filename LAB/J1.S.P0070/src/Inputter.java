
import java.util.Scanner;
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ADMIN
 */
public class Inputter {
    /**
     * take input option and validate it
     * @param message message to user
     * @param min min value of input
     * @param max max value of input
     * @return the integer that user inputted
     */
    public int getOption(String message, int min, int max) {
        int input;
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print(message);
            String s = sc.nextLine();
            //throw exception if the input is empty
            if (s.isEmpty()) {
                throw new Exception("Empty input");
            }
            input = Integer.parseInt(s);
            //throw exception if the input is not in range from min to max
            if (input < min || input > max) {
                throw new Exception("Not in range [" + min + "-" + max + "]");
            }
            return input;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getOption(message, min, max);
        }
    }
    /**
     * Take a string from user
     * @param msg message to user
     * @return the non-empty string that user inputted
     */
    public String getString(String msg) {
        Scanner sc = new Scanner(System.in);
        String input;
        System.out.print(msg);
        input = sc.nextLine();
        //if input is empty then print error and ask user again
        if(input.isEmpty()) {
            System.err.println("Empty input");
            return getString(msg);
        }
        return input;
    }     
}
