
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ADMIN
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Inputter inputter = new Inputter();
        Ebank eBank = new Ebank();
        ArrayList<String> listOptions = new ArrayList(Arrays.asList(
                "Vietnamese", "English", "Exit")); 
//        ResourceBundle b = ResourceBundle.getBundle("language", new Locale("vi"));
//        Enumeration k = b.getKeys();
//        while(k.hasMoreElements()) {
//            String key = (String)k.nextElement();
//            String val = b.getString(key);
//            System.out.println(key + " " + val);
//        }
        int choice;
        do {            
            eBank.displayMenu(listOptions);
            choice = inputter.getOption("Please choice one option", 1, 3);
            switch (choice) {
                case 1:
                    eBank.setLocale(new Locale("vi", "VN"));
                    eBank.login();
                    break;
                case 2:
                    eBank.setLocale(new Locale("en", "US"));
                    eBank.login();
                    break;
                case 3:
                    System.exit(0);
            }
        } while (true);
    }
    
}
