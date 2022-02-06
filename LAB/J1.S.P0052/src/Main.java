
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public static void main(String[] args) {
        ManageEastAsiaCountries m = new ManageEastAsiaCountries();
        while (true) {
            //step 1. Display the menu
            m.displayMenu();
            //step 2. Ask user to select option
            int choice = m.selectOption("Enter your choice: ");
            //step 3. Perform function based on the selected option
            switch (choice) {
                case 1:
                    m.addCountryInformation();
                    break;
                case 2: 
                    try {
                        System.out.println(m.getRecentlyEnteredInformation());
                    } catch (Exception ex) {
                        System.err.println(ex.getMessage());
                    }
                    break;
                case 3:
                    m.displayList(m.searchInformationByName());
                    break;
                case 4:
                    m.displayList(m.sortInformationByAscendingOrder());
                    break;
                case 5:
                    System.exit(0);
            }
        }

    }

}
