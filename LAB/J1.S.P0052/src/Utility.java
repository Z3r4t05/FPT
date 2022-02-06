

import java.util.ArrayList;
import java.util.NoSuchElementException;
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
    public static final Pattern COUNTRY_CODE = Pattern.compile("^\\s*[A-Z]{2,3}\\s*$");
    public static final Pattern COUNTRY_NAME = Pattern.compile("^\\s*([A-Za-z]+\\s?)*\\s*$");
    public static final Pattern TERRAIN = Pattern.compile("^\\s*([A-Za-z]+\\s?)*\\s*$");
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void printGreen(String s) {
        System.out.println(ANSI_GREEN + s + ANSI_RESET);
    }
    
    public static String normalize(String S) {
        if (S.length() == 0) {
            return S;
        }
        StringTokenizer stk = new StringTokenizer(S, " ");
        String result = stk.nextToken();
        while (stk.hasMoreElements()) {
            result += " " + stk.nextToken();
        }
        return result;
    }

    public static String getNonBlankStr(String msg) throws Exception {
        // get a non blank string
        String result;
        do {
            System.out.print(msg);
            result = normalize(sc.nextLine()); //normalizing
            if (result.isEmpty()) {
                throw new Exception("Empty input!");
            }
        } while (result.length() == 0);
        return result;
    }

    public static boolean isValid(String input, Pattern p) {
        return p.matcher(input).matches();
    }

    public static String inputCode(String message, Pattern p) throws Exception {
        String code;
        System.out.print(message);
        code = getNonBlankStr(message);
        if (!isValid(code, p)) {
            throw new Exception("Not a valid code. It must consist of "
                    + "exactly 2 "
                    + "or 3 uppercase alphabet characters");
        }
        return Utility.normalize(code);
    }

    public static String inputName(String msg, Pattern p) throws Exception {
        String name;
        System.out.print(msg);
        name = getNonBlankStr(msg);
        if (!isValid(name, p)) {
            throw new Exception("Not a valid name. Name must consist of at "
                    + "least 2 alphabet characters");
        }
        return Utility.normalize(name);
    }

    public static int inputTotalArea(String msg) throws Exception {
        int area;
        System.out.print(msg);
        String input = getNonBlankStr(msg);
        area = Integer.parseInt(input);
        if (area <= 0) {
            throw new Exception("Area must be greater than 0");
        }
        return area;
    }
    
    public static String findExistedCode(ArrayList<EastAsiaCountries> list,
            String code) throws Exception {
        for (EastAsiaCountries c : list) {
            if (c != null && c.getCountryCode().equals(code)) {
                throw new Exception("Code is already existed in the list");
            }
        }
        return code;
    }

    public static String findExistedName(ArrayList<EastAsiaCountries> list,
            String name) throws Exception {
        for (EastAsiaCountries c : list) {
            if (c != null && c.getCountryName().equals(name)) {
                throw new Exception("Name is already existed in the list");
            }
        }
        return name;
    }
    
    public static String inputTerrain(String msg) throws Exception {
        String terrain;
        System.out.print(msg);
        terrain = sc.nextLine();
        if (terrain.isEmpty()) {
            throw new Exception("Empty input");
        } else if (!Utility.isValid(terrain, Utility.TERRAIN)) {
            throw new Exception("Not a valid terrain");
        }
        return terrain;
    }

}
