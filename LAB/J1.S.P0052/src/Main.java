
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) {
        ManageEastAsiaCountries manager = new ManageEastAsiaCountries();
        ArrayList<EastAsiaCountries> listOfCountries = new ArrayList<>();
        EastAsiaCountries newCountry = null;
        int choice = 0;
        do {//Return to main screen after each after finishing the function
            try {
                //step 1. Display the menu
                manager.displayMenu();
                //step 2. Ask user to select option
                choice = manager.selectOption("Enter your choice : ", 1, 5);
                //step 3. Perform function based on the selected option
                switch (choice) {
                    case 1:
                        //Add information of 11 countries
                        newCountry = manager.addCountryInformation(listOfCountries);
                        break;
                    case 2: //Display information of country user has just entered
                        manager.getRecentlyEnteredInformation(newCountry).display();
                        break;
                    case 3: //Search information of country by user-entered name
                        manager.searchInformationByName(listOfCountries);
                        break;
                    case 4: //Display country by ascending order of name
                        manager.sortInformationByAscendingOrder(listOfCountries);
                        break;
                    case 5://Exit the program
                        System.exit(0);
                }             
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (choice != 5);
    }
}
