
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @loc 20
 * @author An Thanh Long
 */
public class Main {

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
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String date1, date2;
        //Step 1: Input the first date, format [dd/mm/yyyy]
        date1 = inputDate("Please enter the first date: ");
        //Step 2: Input the second date, format [dd/mm/yyyy]
        date2 = inputDate("Please enter the second date: ");
        //Step 3: Compare the dates and display the result
        compareAndDisplay(date1, date2);
    }

    /**
     * Get the date from user. Only return the date-string when the input 
     * is valid. The function isValid() is called during the process for 
     * date-validation.
     * 
     * @param msg the message for user
     * @return string date with format [dd/mm/yyyy]
     */
    private static String inputDate(String msg) {
        Scanner sc = new Scanner(System.in);
        String input;
        //Keep asking until the input received is valid
        do {
            System.out.print(msg);
            input = sc.nextLine();
        } while (!isValid(input));
        return input;
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
        } 
        else {
            return true;
        }
    }

    /**
     * Compare date 1 and date 2, then display the result.
     *
     * @param date1 The first formatted date
     * @param date2 The second formatted date
     */
    private static void compareAndDisplay(String date1, String date2) {
        String[] dateParts1 = date1.split("/");
        String[] dateParts2 = date2.split("/");
        int year1 = Integer.parseInt(dateParts1[2]);
        int year2 = Integer.parseInt(dateParts2[2]);
        int month1 = Integer.parseInt(dateParts1[1]);
        int month2 = Integer.parseInt(dateParts2[1]);
        int day1 = Integer.parseInt(dateParts1[0]);
        int day2 = Integer.parseInt(dateParts2[0]);
        System.out.println("");

        //Check if year1 is after year2 
        if (year1 > year2) {
            System.out.println("Date1 is after Date2");
        } else if (year1 < year2) {//Check if year1 if before year2
            System.out.println("Date1 is before Date2");
        } else {
            //If the years are equal, compare month1 with month2 
            if (month1 > month2) {
                System.out.println("Date1 is after Date2");
            } else if (month1 < month2) {//Check if month1 is before month 2
                System.out.println("Date1 is before Date2");
            } else {
                //If the months are equal, compare day1 with day2
                if (day1 > day2) {
                    System.out.println("Date1 is after Date2");
                } else if (day1 < day2) {//Check if day1 is before day 2
                    System.out.println("Date1 is before Date2");
                } else {
                    System.out.println("Date1 is equal to Date2");
                }
            }
        }
    }
}
