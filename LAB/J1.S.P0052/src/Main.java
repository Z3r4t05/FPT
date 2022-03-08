
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 * @author ADMIN
 */
public class Main {

    public static void main(String[] args) {
        ManageEastAsiaCountries manager = new ManageEastAsiaCountries();
        ArrayList<EastAsiaCountries> listOfCountries = new ArrayList<>();
        EastAsiaCountries newCountry = null;
        ArrayList<String> listOptions = new ArrayList<>(Arrays.asList(
                "Input the information of 11 countries in East Asia",
                "Display the information of country you've just input",
                "Search the information of country by user-entered name",
                "Display the information of countries sorted name in ascending "
                + "order",
                "Exit"
        ));
        int choice = 0;
        do {//Return to main screen after performing each option
            try {
                //step 1. Display the menu
                manager.displayMenu(listOptions);
                //step 2. Ask user to select option
                choice = manager.selectOption("Enter your choice : ", 1, 5);
                //step 3. Perform function based on the selected option
                switch (choice) {
                    //Add information of 11 countries
                    case 1:
                        newCountry = manager.addCountryInformation(listOfCountries);
                        break;
                    //Display information of country user has just entered
                    case 2: 
                        manager.getRecentlyEnteredInformation(newCountry).display();
                        break;
                    //Search information of country by user-entered name
                    case 3: 
                        manager.searchInformationByName(listOfCountries);
                        break;
                    //Display country by ascending order of name
                    case 4: 
                        manager.sortInformationByAscendingOrder(listOfCountries);
                        break;
                    //Exit the program
                    case 5:
                        System.exit(0);
                }             
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (choice != 5);
    }
}
