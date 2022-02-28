
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.StringTokenizer;
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
public class Utility {

    private static final Scanner sc = new Scanner(System.in);
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RESET = "\u001B[0m";
    /**
     * Pattern using to check if the date is existed
     */
    private static final Pattern DATE_EXISTED = Pattern.compile(
            //start of line
            "^"
            //29th February
            + "((29/(02|2)/"
            //leap years that divisible by 400
            + "((([13579][26]|[2468][048]|0?[48])00)"
            //or leap years that are divisible by 4 but not divisible by 100
            + "|([\\d]{0,2}?([13579][26]|[2468][048]|0?[48]))))"
            //Day from 1st to 28th
            + "|((0?[\\d]|1[\\d]|2[0-8])"
            //February 
            + "/(02|2)/"
            //non-leap year from 1 to 9999
            + "([\\d]{1,4}))"
            //Day from 1st to 31st
            + "|((0?[\\d]|[12][\\d]|3[01])/"
            //Months with 31 days        
            + "(0?[13578]|1[02])/"
            //Any year from 1 to 9999       
            + "([\\d]{1,4}))"
            //Day from 1st to 30th
            + "|((0?[\\d]|[12][\\d]|30)/"
            //Months with 30 days
            + "(0?[469]|11)/"
            //Any year from 1 to 9999
            + "([\\d]{1,4})))"
            //End of line
            + "$");

    /**
     * Pattern using to check if the year is zero
     */
    private static final Pattern YEAR_ZERO = Pattern.compile(
            //Start of line
            "^"
            //Any 1 or 2 digits
            + "[\\d]{1,2}"
            //"/" follow by any 1 or 2 digits
            + "/[\\d]{1,2}"
            //"/" follow by 1 to 4 ZERO digits        
            + "/[0]{1,4}"
            //end of line
            + "$");

    /**
     * Pattern using to check if the input is in the correct format
     */
    private static final Pattern DATE_FORMAT = Pattern.compile(
            //Start of line
            "^"
            //Any 1 or 2 digits
            + "[\\d]{1,2}"
            //"/" follow by any 1 or 2 digits
            + "/[\\d]{1,2}"
            //"/" follow by any 1 to 4 digits        
            + "/[\\d]{1,4}"
            //end of line
            + "$");
    /**
     * Pattern to check name format
     */
    private static final Pattern NAME_FORMAT = Pattern.compile(
            "^" //start of line
            + "[a-zA-Z\\s]+"); //1 or more of alphabet char or space

    /**
     * Print string in green
     *
     * @param s string input
     */
    public static void printGreen(String s) {
        System.out.println(ANSI_GREEN + s + ANSI_RESET);
    }

    /**
     * Normalize and recapitalize the string
     *
     * @param S string input
     * @return string after normalize and recapitalize
     */
    public static String normalizeAndRecapitalize(String S) {
        //Check if the input is empty
        if (S.length() == 0) {
            return S;
        }
        StringTokenizer stk = new StringTokenizer(S, " ");
        String result = capitalizeFirstChar(stk.nextToken().toLowerCase());
        //Capitalize the first char of each token and merge them back into input
        while (stk.hasMoreElements()) {
            result += " " + capitalizeFirstChar(stk.nextToken().toLowerCase());
        }
        return result;
    }

    /**
     * Take input string from user which is not blank
     *
     * @param msg message to user
     * @return the inputted string (which is re-capitalize)
     */
    public static String getNonBlankStr(String msg) {
        String result;
        System.out.println(msg);
        result = normalizeAndRecapitalize(sc.nextLine());
        //throw new exception for empty input
        if (result.isEmpty()) {
            System.out.print("Empty input!\n");
            return getNonBlankStr(msg);
        }
        return result;
    }

    /**
     * Check if a string match the pattern or not
     *
     * @param input string to check
     * @param p pattern to check
     * @return True if input match the pattern. False otherwise
     */
    public static boolean isValid(String input, Pattern p) {
        return p.matcher(input).matches();
    }

    /**
     * Take input code of country from user
     *
     * @param message message to user
     * @param p pattern to check
     * @return the inputted code
     * @throws Exception if the code doesn't match the pattern
     */
    public static String inputCode(String message, Pattern p) throws Exception {
        String code;
        code = getNonBlankStr(message).toUpperCase();
        //throw new exception if the code doesn't match the pattern
        if (!isValid(code, p)) {
            throw new Exception("Not a valid code. It must consist of "
                    + "exactly 2 "
                    + "or 3 alphabet characters");
        }
        return code;
    }

    /**
     * Take input name of country from user
     *
     * @param msg message to user
     * @return the string user inputted
     * @throws Exception if the name doesn't match the pattern
     */
    public static String inputStorekeeper(String msg) throws Exception {
        String input;
        input = getNonBlankStr(msg);
        //throw new exception if the name doesn't match the pattern
        if (!isValid(input, NAME_FORMAT)) {
            throw new Exception("Not a valid name!");
        }
        return input;
    }

    /**
     * Capitalize the first character of the string
     *
     * @param str
     * @return the string after capitalize the first char
     */
    public static String capitalizeFirstChar(String str) {
        //If the string is null or empty return immediately.
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static BigDecimal inputPrice(String msg) throws Exception {
        System.out.println(msg);
        BigDecimal num = new BigDecimal(sc.nextLine());
        return num;
    }

    public static LocalDate inputDate(String msg) {
        String input;
        //Keep asking until the input received is valid
        do {
            System.out.print(msg);
            input = sc.nextLine();
        } while (!isValid(input));
        String[] dateParts = input.split("/");
        int day = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int year = Integer.parseInt(dateParts[2]);
        return LocalDate.of(year,month,day);
    }
    public static LocalDate loadDate(String date) {
        String[] dateParts = date.split("/");
        int day = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int year = Integer.parseInt(dateParts[2]);
        return LocalDate.of(year,month,day);
    }
    public static void main(String[] args) {
        LocalDate date = Utility.inputDate("DFSDF: ");
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        System.out.println(fmt.format(date));
    }
    /**
     * Check if the date input is correct in format and check if it's existed.
     *
     * @param input the input date
     * @return true if the input date is valid, false otherwise.
     */
    private static boolean isValid(String input) {
        boolean correctFormatted = DATE_FORMAT.matcher(input).matches();
        boolean isExisted = DATE_EXISTED.matcher(input).matches();
        boolean isYearZero = YEAR_ZERO.matcher(input).matches();
        if (input.isEmpty()) {//Check if the input is empty
            System.err.println("Empty input!");
            return false;
        } else if (!correctFormatted) {//Check if the input is wrong in format
            System.err.println("Wrong format! Please enter a date with format "
                    + "[dd/mm/yyyy]");
            return false;
        } else if (!isExisted) {//Check if the date doesn't exist
            System.err.println("No such day in calendar!");
            return false;
        } else if (isYearZero) {//Check if the year is zero
            System.err.println("Year 0 doesn't exist!");
            return false;
        } else {
            return true;
        }
    }

}
