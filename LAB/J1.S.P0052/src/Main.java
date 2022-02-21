
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        ManageEastAsiaCountries manager = new ManageEastAsiaCountries();
        ArrayList<EastAsiaCountries> listOfCountries = new ArrayList<>();
        EastAsiaCountries newCountry = null;
        int choice;
        do {//Return to main screen after each after finishing the function

            //step 1. Display the menu
            manager.displayMenu();
            //step 2. Ask user to select option
            choice = manager.selectOption("Enter your choice : ", 1, 5);
            //step 3. Perform function based on the selected option
            switch (choice) {
                case 1:{
                try {
                    //Add information of 11 countries
                    newCountry = manager.addCountryInformation(listOfCountries);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
                    break;
                case 2://Display information of country user has just entered
                    try {
                        manager.getRecentlyEnteredInformation(newCountry).display();
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 3://Search information of country by user-entered name
                    try {
                        manager.searchInformationByName(listOfCountries);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 4://Display country by ascending order of name
                    try {
                        manager.sortInformationByAscendingOrder(listOfCountries);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 5://Exit the program
                    System.exit(0);
            }
        } while (choice != 5);
    }
}
