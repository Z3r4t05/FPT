
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ManageEastAsiaCountries m = new ManageEastAsiaCountries();
        ArrayList<EastAsiaCountries> list = new ArrayList<>();
        EastAsiaCountries newCountry = null;
        int choice;
        do {//Return to main screen after each after finishing the function

            //step 1. Display the menu
            Utility.displayMenu();
            //step 2. Ask user to select option
            choice = Utility.selectOption("Enter your choice : ", 1, 5);
            //step 3. Perform function based on the selected option
            switch (choice) {
                case 1://Add information of 11 countries
                    newCountry = m.addCountryInformation(list);
                    break;
                case 2://Display information of country user has just entered
                    try {
                        m.getRecentlyEnteredInformation(newCountry).display();
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 3://Search information of country by user-entered name
                    try {
                        Utility.displayArray(
                                m.searchInformationByName(list));
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 4://Display country by ascending order of name
                    try {
                        Utility.displayArray(
                            m.sortInformationByAscendingOrder(list));
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
