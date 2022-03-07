
import java.util.ArrayList;
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

    public static final Scanner sc = new Scanner(System.in);
    public static final Pattern COUNTRY_CODE = Pattern.compile(
            "^" //Start of line
            + "\\s*" //0 or many space token
            + "[A-Z]{2,3}" //2 or 3 Uppercase alphabet char
            + "\\s*" //0 or many space token
            + "$");        //end of line
    public static final Pattern COUNTRY_NAME = Pattern.compile(
            "^" //Start of line
            + "\\s*" //0 or many space
            + "([A-Za-z]+\\s?)*" //0 or many word that separate by 0 or 1 space
            + "\\s*" //0 or many space
            + "$");              //end of line
    public static final Pattern TERRAIN = Pattern.compile(
            "^" //Start of line
            + "\\s*" //0 or many space
            + "([A-Za-z]+\\s?)*" //0 or many word that separate by 0 or 1 space
            + "\\s*" //0 or many space
            + "$");              //end of line
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

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
     * @param p pattern to check
     * @return the string user inputted
     * @throws Exception if the name doesn't match the pattern
     */
    public static String inputName(String msg, Pattern p) throws Exception {
        String name;
        name = getNonBlankStr(msg);
        //throw new exception if the name doesn't match the pattern
        if (!isValid(name, p)) {
            throw new Exception("Not a valid name!");
        }
        return name;
    }

    /**
     * Take input area of a country from user
     *
     * @param msg message to user
     * @return the area inputted
     * @throws Exception if the area is out of range
     */
    public static float inputTotalArea(String msg) throws Exception {
        float area;
        String input = getNonBlankStr(msg) + "f";
        //Catch exception if user enter a non-float input
        try {
            area = Float.parseFloat(input);
        } catch (NumberFormatException e) {
            throw new Exception("Area must be an integer or a float number");
        }
        //Throw new exception if the area is out of range
        if (area <= 0) {
            throw new Exception("Area must be greater than 0");
        } else if (area >= 17098242) {
            throw new Exception("Largest country in the world is only 17098242"
                    + " km2");
        }
        return area;
    }

    /**
     * Check if the code is already existed in the list
     *
     * @param list the array of East Asia countries
     * @param code code of the country you want to search for
     * @return the inputted code
     * @throws Exception if the code is already existed
     */
    public static String findExistedCode(ArrayList<EastAsiaCountries> list,
            String code) throws Exception {
        code = code.toLowerCase();
        //Loop through the list to check if the code is existed
        for (EastAsiaCountries c : list) {
            //Throw new exception if it's equal to the code of any country
            if (c != null && c.getCountryCode().toLowerCase().equals(code)) {
                throw new Exception("Code is already existed in the list");
            }
        }
        return code.toUpperCase();
    }

    /**
     * Check if the name is already existed in the list
     *
     * @param list the array of East Asia countries
     * @param name name of the country you want to search for
     * @return the inputted name
     * @throws Exception if the name is already existed
     */
    public static String findExistedName(ArrayList<EastAsiaCountries> list,
            String name) throws Exception {
        //Loop through the list to check if the name is existed
        for (EastAsiaCountries c : list) {
            //If it's equal to one country in the list, throw new exception
            if (c != null && c.getCountryName().toLowerCase().equals(name.toLowerCase())) {
                throw new Exception("Name is already existed in the list");
            }
        }
        return name;
    }

    /**
     * Ask user to input the terrain of a EA country
     *
     * @param msg
     * @return the input string
     * @throws Exception if the input doesn't match the pattern
     */
    public static String inputTerrain(String msg) throws Exception {
        String terrain;
        terrain = getNonBlankStr(msg);
        //Throw exception if the input doesn't match the terrain pattern
        if (!Utility.isValid(terrain, Utility.TERRAIN)) {
            throw new Exception("Not a valid terrain");
        }
        return terrain;
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

    /**
     * Display a array of East Asia Countries as a table
     *
     * @param arr array of EA countries
     */
    public static void displayTableOfCountries(EastAsiaCountries[] arr) {
        System.out.printf("%-16s%-16s%-16s%-16s\n",
                "ID", "Name", "Total Area", "Terrain");
        /**
         * display all countries from the array of country
         */
        for (EastAsiaCountries c : arr) {
            c.display();
        }
    }

}
