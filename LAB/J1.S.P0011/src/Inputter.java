
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

    public int getBaseIn(int option) {
        switch (option) {
            case 1:
                return 2;
            case 2:
                return 10;
            case 3:
                return 16;
            case 4:
                System.exit(0);
        }
        return 0;
    }

    public int getBaseOut(int baseIn) {
        switch (baseIn) {
            case 2:
                MenuInterface.displayMenu(2);
                int choice = this.getOption("Please choose base of output\n", 1, 2);
                switch (choice) {
                    case 1:
                        return 10;
                    case 2:
                        return 16;
                }
            case 10:
                MenuInterface.displayMenu(10);
                choice = this.getOption("Please choose base of output\n", 1, 2);
                switch (choice) {
                    case 1:
                        return 2;
                    case 2:
                        return 16;
                }
            case 16:
                MenuInterface.displayMenu(16);
                choice = this.getOption("Please choose base of output\n", 1, 2);
                switch (choice) {
                    case 1:
                        return 2;
                    case 2:
                        return 10;
                }
        }
        return 0;
    }

    public String getInputValue(String message, int baseIn) {
        Pattern BIN_DIGITS = Pattern.compile("^[0-1]+$");
        Pattern DEC_DIGITS = Pattern.compile("^[0-9]+$");
        Pattern HEX_DIGITS = Pattern.compile("^[a-fA-F0-9]+$");
        String input;
        Scanner sc = new Scanner(System.in);
        do {
            try {
                System.out.println(message);
                input = sc.nextLine();
                if (input.isEmpty()) {
                    throw new Exception("Empty input");
                }
                switch (baseIn) {
                    case 2:
                        if (BIN_DIGITS.matcher(input).matches()) {
                            return input;
                        } else {
                            throw new Exception("Wrong binary format");
                        }
                    case 10:
                        if (DEC_DIGITS.matcher(input).matches()) {
                            return input;
                        } else {
                            throw new Exception("Wrong decimal format");
                        }
                    case 16:
                        if (HEX_DIGITS.matcher(input).matches()) {
                            return input.toUpperCase();
                        } else {
                            throw new Exception("Wrong hexadecimal format");
                        }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }
}
