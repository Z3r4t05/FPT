
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
        int choice;
        //Loop until user choose exit
        do {            
            eBank.displayMenu(listOptions);
            choice = inputter.getOption("Please choice one option:  ", 1, 3);
            switch (choice) {
                //set vietnamese language
                case 1:
                    eBank.setLocale(new Locale("vi", "VN"));
                    eBank.login();
                    break;
                //set english language
                case 2:
                    eBank.setLocale(new Locale("en", "US"));
                    eBank.login();
                    break;
                //Exit
                case 3:
                    System.exit(0);
            }
        } while (true);
    }
    
}
