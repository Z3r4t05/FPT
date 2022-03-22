
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;
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

    public void useEnglishLanguage() {
        setLocale(new Locale("vi", "VN"));
    }

    public void useVietnameseLanguage() {
        setLocale(new Locale("en", "US"));
    }

    public void setLocale(Locale locale) {
        bundle = ResourceBundle.getBundle("language", locale);
    }

    public String checkAccountNumber(String accountNumber) {
        Pattern ACCOUNT_FORMAT = Pattern.compile(
                "^[0-9]" //start a line with digit token
                + "{10}$"); //repeat the previous token exactly 10 times before endline
        if (!ACCOUNT_FORMAT.matcher(accountNumber).matches()) {
            return bundle.getString("inputAccountError");
        }
        return null;
    }

    public String checkPassword(String password) {
        Pattern PASSWORD_FORMAT = Pattern.compile(""
                + "^" //start of line
                + "(?=.*[A-Za-z])" //positive lookahead to find a alphabet character presents in the string
                + "(?=.*\\d)" //positive lookahead to find a digit character presentes in the string
                + "[A-Za-z\\d]{8,31}" //8 to 31 characters or digits
                + "$"); //end of line
        if (!PASSWORD_FORMAT.matcher(password).matches()) {
            return bundle.getString("inputPasswordError");
        }
        return null;
    }

    public String generateCaptcha() {
        String data = "";
        for (char c = 'a'; c <= 'z'; c++) {
            data += c;
        }
        for (char c = 'A'; c <= 'Z'; c++) {
            data += c;
        }
        for (char c = '0'; c <= '9'; c++) {
            data += c;
        }
        do {
            String captcha = "";
            boolean hasLowerChar = false;
            boolean hasUpperChar = false;
            boolean hasDigits = false;
            //loop to create 5 character capcha
            for (int i = 0; i < 5; i++) {
                Random random = new Random();
                char newChar = data.charAt(random.nextInt(data.length()));
                if (newChar >= 'a' && newChar <= 'z') {
                    hasLowerChar = true;
                } else if (newChar >= 'A' && newChar <= 'Z') {
                    hasUpperChar = true;
                } else {
                    hasDigits = true;
                }
                captcha += newChar;
            }
            if (hasLowerChar && hasUpperChar && hasDigits) {
                return captcha;
            }
        } while (true);
    }

    public String checkCaptcha(String captchaInput, String captchaGenerate) {
        if (captchaInput.equals(captchaGenerate)) {
            return null;
        } else {
            return bundle.getString("CapchaError");
        }
    }

    public void displayMenu(ArrayList<String> listOptions) {
        System.out.println("-------Login Program-------");
        int totalOption = listOptions.size();
        //print all options start with index from 1 to the end
        for (int i = 0; i < totalOption; i++) {
            System.out.println((i + 1) + ".  " + listOptions.get(i));
        }
    }

    void login() {
        Inputter inputter = new Inputter();
        String accountNumber, password;
        ArrayList<Account> accountList = AccountDatabase.database();
        String numberMsg, passwordMsg, captchaMsg;
        do {
            accountNumber = inputter.getString(bundle.getString("accountNumber"));
            numberMsg = checkAccountNumber(accountNumber);
            System.out.println(numberMsg);
        } while (numberMsg != null);

        do {
            password = inputter.getString(bundle.getString("Password"));
            passwordMsg = checkPassword(password);
            System.out.println(passwordMsg);
        } while (passwordMsg != null);

        do {
            String captchaGenerated = this.generateCaptcha();
            String captchaInput = inputter.getString(bundle.getString("Capcha"));
            captchaMsg = this.checkCaptcha(captchaGenerated, captchaInput);
            System.out.println(captchaMsg);
        } while (captchaMsg != null);

        boolean validAccountNumber = this.checkAccount(accountNumber, accountList);
        boolean validPassword = this.checkPassword(accountNumber, password, accountList);
        if (validAccountNumber && validPassword) {
            System.out.println(bundle.getString("loginSuccess"));
        } else {
            System.out.println(bundle.getString("loginFail"));
            System.out.println("");
            this.login();
        }
    }

    private boolean checkAccount(String accountNumber, ArrayList<Account> accountList) {
        if (accountList.isEmpty()) {
            return false;
        } else {
            return accountList.stream().anyMatch((account) -> (account.getNumber().equals(accountNumber)));
        }
    }

    private boolean checkPassword(String accountNumber, String password, ArrayList<Account> accountList) {
        if (accountList.isEmpty()) {
            return false;
        } else {
            return accountList.stream().anyMatch((account) -> (account.getNumber().equals(accountNumber)
                    && account.getPassword().equals(password)));
        }
    }
}
