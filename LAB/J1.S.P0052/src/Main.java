
import java.util.logging.Level;
import java.util.logging.Logger;

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
                case 1: //add information of 11 countries
                    for (int i = 0; i < 1; i++) {
                        m.addCountryInformation();
                    }
                    break;
                case 2: //display information of country user has just entered
                    try {
                        m.getRecentlyEnteredInformation().display();
                    } catch (Exception ex) {
                        System.err.println(ex.getMessage());
                    }
                    break;
                case 3:
                    try {
                        //search information of country by user-entered name
                        m.displayList(m.searchInformationByName());
                    } catch (Exception ex) {
                        System.err.println(ex.getMessage());
                    }

                    break;
                case 4:
                    try {
                        //display country by ascen1ding order of name
                        m.displayList(m.sortInformationByAscendingOrder());
                    } catch (Exception ex) {
                        System.err.println(ex.getMessage());
                    }

                    break;
                case 5: //exit
                    System.exit(0);
            }
        }
    }
}
