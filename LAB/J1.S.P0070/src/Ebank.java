
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Set;
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
public class Ebank {

    ResourceBundle bundle;

    /**
     * set the ResourceBundle of this object based on Locale parameter
     *
     * @param locale the Locale to use properties
     */
    public void setLocale(Locale locale) {
        bundle = ResourceBundle.getBundle("Language", locale);
    }

    /**
     * check if account number has exactly 10 digits
     *
     * @param accountNumber input accountNumber
     * @return null if the account number is valid. Otherwise return the error.
     */
    public String checkAccountNumber(String accountNumber) {
        Pattern ACCOUNT_FORMAT = Pattern.compile(
                "^[0-9]" //start a line with digit token
                + "{10}$"); //repeat the previous token exactly 10 times before endline
        //if the input matches the pattern then return the message to user
        if (!ACCOUNT_FORMAT.matcher(accountNumber).matches()) {
            return bundle.getString("inputAcountError");
        } else {
            return null;
        }
    }

    /**
     * Check if password has exactly 8 to 31 alphanumeric characters
     *
     * @param password input password
     * @return null if password is valid. Otherwise return the error
     */
    public String checkPassword(String password) {
        Pattern PASSWORD_FORMAT = Pattern.compile(""
                + "^" //start of line
                + "(?=.*[A-Za-z])" //positive lookahead to find a alphabet character presents in the string
                + "(?=.*\\d)" //positive lookahead to find a digit character presentes in the string
                + "[A-Za-z\\d]{8,31}" //8 to 31 characters or digits
                + "$"); //end of line
        //if the input matches the pattern then return the message to user
        if (!PASSWORD_FORMAT.matcher(password).matches()) {
            return bundle.getString("inputPasswordError");
        }
        return null;
    }

    /**
     * Generate and print out a CAPTCHA of length 5 that contains at least an
     * uppercase character, a lowercase character and a digit.
     *
     * @return the CAPTCHA generated
     */
    public String generateCaptcha() {
        ArrayList<Character> possibleCharacterList = new ArrayList<>();
        //add all lowercase chars to the string data
        for (char c = 'a'; c <= 'z'; c++) {
            possibleCharacterList.add(c);
        }
        //add all uppercase chars to the string data
        for (char c = 'A'; c <= 'Z'; c++) {
            possibleCharacterList.add(c);
        }
        //aa all digits to the string data
        for (char c = '0'; c <= '9'; c++) {
            possibleCharacterList.add(c);
        }

        //loop until the capcha generated has both uppercase, lowercase and digit character
        do {
            String captcha = "";
            boolean hasLowerChar = false;
            boolean hasUpperChar = false;
            boolean hasDigits = false;
            //loop to create 5 character capcha
            for (int i = 0; i < 5; i++) {
                Random random = new Random();
                char randomChar = possibleCharacterList.get(random.nextInt(possibleCharacterList.size()));
                //Change the flag hasLowerChar, hasUpperChar or hasDigits based on the newChar added to CAPTCHA
                if (randomChar >= 'a' && randomChar <= 'z') {
                    hasLowerChar = true;
                } else if (randomChar >= 'A' && randomChar <= 'Z') {
                    hasUpperChar = true;
                } else {
                    hasDigits = true;
                }
                captcha += randomChar;
            }
            //If the CAPTCHA is valid then return the CAPTCHA
            if (hasLowerChar && hasUpperChar && hasDigits) {
                return captcha;
            }
        } while (true);
    }

    /**
     * Check if the input CAPTCHA is the same as the CAPTCHA generated
     *
     * @param captchaInput the input from user
     * @param captchaGenerate the generated CAPTCHA
     * @return null if the input is the same as the CAPTCHA generated. Otherwise
     * return the error message
     */
    public String checkCaptcha(String captchaInput, String captchaGenerate) {
        //return null if the input is the same as the CAPTCHA generated. Otherwise return the error message
        if (captchaInput.equals(captchaGenerate)) {
            return null;
        } else {
            return bundle.getString("CapchaError");
        }
    }

    /**
     * Display the menu with the option in the arrayList input
     *
     * @param listOptions the list of option
     */
    public void displayMenu(ArrayList<String> listOptions) {
        System.out.println("-------Login Program-------");
        int totalOption = listOptions.size();
        //print all options start with index from 1 to the end
        for (int i = 0; i < totalOption; i++) {
            System.out.println((i + 1) + ".  " + listOptions.get(i));
        }
    }

    /**
     * Perform login function. Prompt user to enter valid account number, then
     * password and a random CAPTCHA. Then check if the account exists in the
     * database.
     */
    public void login() {
        Inputter inputter = new Inputter();
        String accountNumber, password;
        ArrayList<Account> accountList = AccountDatabase.database();
        String numberMsg, passwordMsg, captchaMsg;
        //Loop until user enter a valid account number
        do {
            accountNumber = inputter.getString(bundle.getString("accountNumber") + " ");
            numberMsg = checkAccountNumber(accountNumber);
            //print out message error if numberMsg not null
            if (numberMsg != null) {
                System.out.println(numberMsg);
            }
        } while (numberMsg != null);
        //Loop until user enter a valid password
        do {
            password = inputter.getString(bundle.getString("Password"));
            passwordMsg = checkPassword(password);
            //print out message if passwordMsg is not null
            if (passwordMsg != null) {
                System.out.println(passwordMsg);
            }
        } while (passwordMsg != null);
        //Loop ubtil user enter a valid CAPTCHA
        do {
            String captchaGenerated = this.generateCaptcha();
            System.out.println(bundle.getString("Capcha") + " " + captchaGenerated);
            String captchaInput = inputter.getString(bundle.getString("inputCapcha") + " ");
            captchaMsg = this.checkCaptcha(captchaGenerated, captchaInput);
            //print out the message error if the captchaMsg is not null
            if (captchaMsg != null) {
                System.out.println(captchaMsg);
            }
        } while (captchaMsg != null);

        boolean validAccountNumber = this.checkAccount(accountNumber, accountList);
        boolean validPassword = this.checkPassword(accountNumber, password, accountList);
        //Check if both accountnumber and password is valid or not then print out the message
        if (validAccountNumber && validPassword) {
            System.out.println(bundle.getString("loginSuccess"));
        } else {
            System.out.println(bundle.getString("loginFail"));
        }
    }

    /**
     * Check if the account number exists in database
     *
     * @param accountNumber input account number
     * @param accountList the arrayList of account in database
     * @return false if the list is empty or not found account. Otherwise return
     * true
     */
    private boolean checkAccount(String accountNumber, ArrayList<Account> accountList) {
        //Return false if the list is empty. Otherwise true or false based on the result of searching account number in the list
        if (accountList.isEmpty()) {
            return false;
        } else {
            return accountList.stream().anyMatch((account) -> (account.getNumber().equals(accountNumber)));
        }
    }

    /**
     * Check if password of input account is correct.
     *
     * @param accountNumber account number to check password
     * @param password input password
     * @param accountList the list account in database
     * @return false if the list is empty or password is incorrect. Otherwise
     * true
     */
    private boolean checkPassword(String accountNumber, String password, ArrayList<Account> accountList) {
        //Return false if the list is empty. Otherwise return true or false based on the result of comparing input with a account in the list
        if (accountList.isEmpty()) {
            return false;
        } else {
            return accountList.stream().anyMatch((account) -> (account.getNumber().equals(accountNumber)
                    && account.getPassword().equals(password)));
        }
    }
}
