
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ADMIN
 */
public class ManageEastAsiaCountries {

    private ArrayList<EastAsiaCountries> countryList = new ArrayList<>();
    private EastAsiaCountries lastCountryInput;

    public static final Scanner sc = new Scanner(System.in);

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
        for (int i = 0; i < totalOption; i++) {
            System.out.println((i + 1) + ". " + listOptions.get(i));
        }
        System.out.println("=================================================="
                + "========================");
    }

    /**
     * Take an input from user (default from 1 to 5) and validate it
     *
     * @param message message to user
     * @return the input value
     */
    public int selectOption(String message) {
        try {
            return selectOption(message, 1, 5);
        } catch (Exception e) {
            System.err.println(e);
        }
        return 0;
    }

    /**
     * Take an input (an integer from min to max) and validate it
     *
     * @param message message to user
     * @param min minimum value
     * @param max maximum value
     * @return the input value
     */
    public int selectOption(String message, int min, int max) {
        int input;
        try {
            System.out.println(message);
            input = Integer.parseInt(sc.nextLine());
            if (input < min || input > max) {
                throw new Exception("Input out of range!");
            }
            return input;
        } catch (Exception e) {
            if (e instanceof NumberFormatException) {
                System.err.println("Not a valid number");
            } else {
                System.err.println(e.getMessage());
            }
            System.err.println("Please enter a valid integer [" + min + "-" + max + "]");
            return selectOption(message, min, max);
        }
    }

    public void addCountryInformation() {
//        Country c = new Country();
        EastAsiaCountries country = new EastAsiaCountries();
        while (true) {
            try {
                country.setCountryCode(Utility.findExistedCode(
                        countryList, Utility.inputCode(
                                "Enter code of country: ", Utility.COUNTRY_CODE)));
                break;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        while (true) {
            try {
                country.setCountryName(Utility.findExistedName(
                        countryList, Utility.inputName(
                                "Enter name of country: ", Utility.COUNTRY_NAME)));
                break;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        while (true) {
            try {
                country.setTotalArea(Utility.inputTotalArea(
                        "Enter total Area: "));
                break;

            } catch (Exception e) {
                if (e instanceof NumberFormatException) {
                    System.err.println("Invalid input. Area must be a positive float or integer!");
                } else {
                    System.err.println(e.getMessage());
                }
            }
        }
        while (true) {
            try {
                country.setCountryTerrain(Utility.inputTerrain(
                        "Enter terrain of country: "));
                break;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        addCountryInformation(country);
    }

    public void addCountryInformation(EastAsiaCountries country) {
        try {
            countryList.add(country);
            this.setLastCountryInput(country);
        } catch (Exception e) {
            if (e instanceof IndexOutOfBoundsException) {
                System.err.println("Index out of range");
            } else {
                System.err.println(e.getMessage());
            }
        }
    }

    public EastAsiaCountries getRecentlyEnteredInformation() throws Exception {
        EastAsiaCountries country = this.getLastCountryInput();
        if (country == null) {
            throw new Exception("You haven't entered anything");
        }
        System.out.printf("%-16s%-16s%-16s%-16s\n", "ID", "NAME", "Total Area", "Terrain");
        return country;
    }

    public EastAsiaCountries[] searchInformationByName()
            throws Exception {
        ArrayList<EastAsiaCountries> database = this.getCountryList();
        ArrayList<EastAsiaCountries> result = new ArrayList<>();
        if (database.isEmpty()) {
            throw new Exception("Empty country list!");
        }
        String input = Utility.getNonBlankStr("Enter the name you want to search for: ").toLowerCase();
        if (!Utility.isValid(input, Utility.COUNTRY_NAME)) {
            throw new Exception("Not a valid name!");
        }

        for (EastAsiaCountries c : database) {
            if (c.getCountryName().toLowerCase().contains(input)) {
                result.add(c);
            }
        }

        if (result.isEmpty()) {
            throw new Exception("Not found");
        }

        return result.toArray(new EastAsiaCountries[result.size()]);
    }

    public void displayList(EastAsiaCountries[] list) {
        System.out.printf("%-16s%-16s%-16s%-16s\n", "ID", "NAME", "Total Area", "Terrain");
        for (EastAsiaCountries list1 : list) {
            list1.display();
        }
    }

    public EastAsiaCountries[] sortInformationByAscendingOrder()
            throws Exception {
        if (this.getCountryList().isEmpty()) {
            throw new Exception("Empty list!");
        }
        EastAsiaCountries[] country = {};
        Collections.sort(this.getCountryList());
        return this.getCountryList().toArray(country);
    }
}
