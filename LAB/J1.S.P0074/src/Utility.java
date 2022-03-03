
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
}
