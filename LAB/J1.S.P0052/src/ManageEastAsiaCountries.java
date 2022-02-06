
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
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
public class ManageEastAsiaCountries {

    private ArrayList<EastAsiaCountries> countryList = new ArrayList<>();
    private EastAsiaCountries lastCountryInput = null;

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
            System.out.print(message);
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
            System.err.println("Please enter a valid integer [1-5]");
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
                    System.err.println("Invalid input. Area must be a positive integer!");
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
            this.setLastCountryInput(lastCountryInput);
            Utility.printGreen("Added Successfully!");
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
        return country;
    }

    public EastAsiaCountries[] searchInformationByName()
            throws Exception {
        ArrayList<EastAsiaCountries> database = this.getCountryList();
        ArrayList<EastAsiaCountries> searching = new ArrayList<>();
        String input = Utility.getNonBlankStr("Search name of country: ");

        if (!Utility.isValid(input, Utility.COUNTRY_NAME)) {
            throw new Exception("Not a valid name!");
        }

        for (EastAsiaCountries c : database) {
            if (c.getCountryName().contains(input)) {
                searching.add(c);
            }
        }

        if (searching.isEmpty()) {
            throw new Exception("Not found");
        }

        EastAsiaCountries[] result = {};
        searching.toArray(result);
        return result;
    }

    public void displayList(EastAsiaCountries[] list) {
        for (EastAsiaCountries list1 : list) {
            System.out.println(list1.getCountryName());
        }
    }

    public EastAsiaCountries[] sortInformationByAscendingOrder()
            throws Exception {
        if (this.getCountryList() == null) {
            throw new Exception("Empty list!");
        }
        EastAsiaCountries[] country = {};
        Collections.sort(this.getCountryList());
        return this.getCountryList().toArray(country);
    }

    public static void main(String[] args) throws Exception {
        ManageEastAsiaCountries mn = new ManageEastAsiaCountries();
        for (int i = 0; i < 2; i++) {
            mn.addCountryInformation();
        }
        try {
            mn.displayList(mn.sortInformationByAscendingOrder());
        } catch (Exception ex) {
            Logger.getLogger(ManageEastAsiaCountries.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
