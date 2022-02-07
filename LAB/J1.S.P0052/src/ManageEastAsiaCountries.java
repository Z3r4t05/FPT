
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author ADMIN
 */
public class ManageEastAsiaCountries {

    private ArrayList<EastAsiaCountries> countryList = new ArrayList<>();
    private EastAsiaCountries lastCountryInput;
    public static final Scanner sc = new Scanner(System.in);

    public ManageEastAsiaCountries() {
    }

    public ArrayList<EastAsiaCountries> getCountryList() {
        return countryList;
    }

    public void setCountryList(ArrayList<EastAsiaCountries> countryList) {
        this.countryList = countryList;
    }

    public EastAsiaCountries getLastCountryInput() {
        return lastCountryInput;
    }

    public void setLastCountryInput(EastAsiaCountries lastCountryInput) {
        this.lastCountryInput = lastCountryInput;
    }

    /**
     * Display the menu to the screen
     */
    public void displayMenu() {
        ArrayList<String> listOptions = new ArrayList<>(Arrays.asList(
                "Input the information of 11 countries in East Asia",
                "Display the information of country you've just input",
                "Search the information of country by user-entered name",
                "Display the information of countries sorted name in ascending "
                + "order",
                "Exit"
        ));
        System.out.println("");
        System.out.println("                               Menu");
        System.out.println("==================================================="
                + "=======================");
        int totalOption = listOptions.size();
        //print all options start with index from 1 to the end
        for (int i = 0; i < totalOption; i++) {
            System.out.println((i + 1) + ". " + listOptions.get(i));
        }
        System.out.println("=================================================="
                + "========================");
    }

    /**
     * Take an input (an integer from min to max)
     *
     * @param message message to user
     * @param min minimum value
     * @param max maximum value
     * @return the input value
     */
    public int selectOption(String message, int min, int max) {
        String input;
        input = Utility.getNonBlankStr(message);
        try {
            //throw exception if input is empty
            if (input.isEmpty()) {
                throw new Exception("Empty input");
            }
            int num;
            try {
                num = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Not a valid number");
            }
            //Throw exception if the input is out of range
            if (num < min || num > max) {
                throw new Exception("Please enter an integer in range [1-5]");
            }         
            return num;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return selectOption(message, min, max);
        }
    }

    /**
     * Ask user to enter information of country then add it to the list
     */
    public void addCountryInformation() {
        EastAsiaCountries country = new EastAsiaCountries();
        //Keep asking for code if the program catches an exception
        while (true) {
            try {
                country.setCountryCode(Utility.findExistedCode(
                        countryList, Utility.inputCode(
                                "Enter code of country: ",
                                Utility.COUNTRY_CODE)));
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        //Keep asking for name if the program catches an exception
        while (true) {
            try {
                country.setCountryName(Utility.findExistedName(
                        countryList, Utility.inputName(
                                "Enter name of country: ",
                                Utility.COUNTRY_NAME)));
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        //Keep asking for area if the program catches an exception
        while (true) {
            try {
                country.setTotalArea(Utility.inputTotalArea(
                        "Enter total Area: "));
                break;
            } catch (Exception e) {
                if (e instanceof NumberFormatException) {
                    System.out.println("Invalid input. Area must be "
                            + "a positive float or integer!");
                } else {
                    System.out.println(e.getMessage());
                }
            }
        }
        //Keep asking for terrain if program catches an exception
        while (true) {
            try {
                country.setCountryTerrain(Utility.inputTerrain(
                        "Enter terrain of country: "));
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        addCountryInformation(country);
    }

    /**
     * add country to the list
     *
     * @param country to be added
     */
    public void addCountryInformation(EastAsiaCountries country) {
        try {
            countryList.add(country);
            this.setLastCountryInput(country);
        } catch (Exception e) {
            //Change the message of the exception
            if (e instanceof IndexOutOfBoundsException) {
                System.out.println("Index out of range");
            } else {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Display information of countries you have just inputted.
     *
     * @return the country that you've recently inputted
     * @throws Exception if you haven't entered anything
     */
    public EastAsiaCountries getRecentlyEnteredInformation() throws Exception {
        EastAsiaCountries country = this.getLastCountryInput();
        //Check if user has entered input at least 1 time or not
        if (country == null) {
            throw new Exception("You haven't entered anything");
        }
        System.out.printf("%-16s%-16s%-16s%-16s\n",
                "ID", "NAME", "Total Area", "Terrain");
        return country;
    }

    /**
     * Search information of countries by user-entered name
     *
     * @return array of countries that matches a part of the name
     * @throws Exception if the list is empty or not found any country or the
     * input name is invalid
     */
    public EastAsiaCountries[] searchInformationByName()
            throws Exception {
        ArrayList<EastAsiaCountries> data = this.getCountryList();
        ArrayList<EastAsiaCountries> result = new ArrayList<>();
        //If the data is empty throw new exception
        if (data.isEmpty()) {
            throw new Exception("Empty country list!");
        }
        String input = Utility
                .getNonBlankStr("Enter the name you want to search for: ")
                .toLowerCase();
        //If the input is not valid then throw new exception
        if (!Utility.isValid(input, Utility.COUNTRY_NAME)) {
            throw new Exception("Not a valid name!");
        }
        //Searching for name in data that matches the input ignoring case
        for (EastAsiaCountries c : data) {
            //add the country in data that matches the input to the result list
            if (c.getCountryName().toLowerCase().contains(input)) {
                result.add(c);
            }
        }

        //If it doesn't found any country then throw new exception
        if (result.isEmpty()) {
            throw new Exception("Not found");
        }

        return result.toArray(new EastAsiaCountries[result.size()]);
    }

    /**
     * Sort information of countries by ascending order of names
     *
     * @return the sorted array of countries
     * @throws Exception if the array is empty
     */
    public EastAsiaCountries[] sortInformationByAscendingOrder()
            throws Exception {
        //throw exception if the list is empty
        if (this.getCountryList().isEmpty()) {
            throw new Exception("Empty list!");
        }
        Collections.sort(this.getCountryList());
        return this.getCountryList()
                .toArray(new EastAsiaCountries[this.getCountryList().size()]);
    }
}
