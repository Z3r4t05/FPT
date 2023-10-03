/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookmanagementsystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 * @author ADMIN
 */
public class BookList {

    

    private ArrayList<Book> t;

    public BookList() {

    }

    public BookList(ArrayList<Book> t) {
        this.t = t;
    }

    /**
     * Add a new book to the list. If books are already existed then update new 
     * quantity of the books.
     * @param t ArrayList of <code>Book</code>
     */
    public static void addBook(ArrayList<Book> t) {
        String code, title;
        int qua;
        double price;
        //Prompt user to continue after each iteration
        do {
            try {
                Pair<Book, String> p = searchBook(t);
                Book searchTarget = p.getT();
                code = p.getU();
                //If the book is not in the list then add the new book. Otherwise only update the quantity of the book
                if (searchTarget == null) {
                    Book book = new Book();
                    book.setCode(code);
                    book.setTitle(getNonBlankString("Enter title: "));
                    book.setQua(getQuantity("Enter quantity: "));
                    book.setPrice(getPrice("Enter double: "));
                    t.add(book);
                } else {
                    System.out.println("This book is already existed: " + searchTarget.toString());
                    qua = getQuantity("Enter quantity you want to add: ");
                    searchTarget.setQua(searchTarget.getQua() + qua);
                }
            } catch (Exception ex) {
                ex.getMessage();
            }
        } while (inputChar("Do you wanna continue? (Y/N): ") == 'Y');
    }

    /**
     * Prompt user to enter a non empty string
     * @param msg message to user
     * @return the string user input
     */
    public static String getNonBlankString(String msg) {
        Scanner sc = new Scanner(System.in);
        String input;
        System.out.print(msg);
        input = sc.nextLine();
        //Prompt user to enter again if the input is empty
        if (input.isEmpty()) {
            System.out.println("Empty input! \n");
            return getNonBlankString(msg);
        }
        return input;
    }

    /**\
     * Check if the code is already in the list provided
     * @param t ArrayList of <code>Book</code>
     * @param code given code to check duplicate
     * @return true if it's already in the list. Otherwise, false
     */
    private static boolean checkDuplicateCode(ArrayList<Book> t, String code) {
        return t.stream().anyMatch(b -> b.getCode().equals(code));
    }

    /**
     * Prompt user to enter quantity as an positive Integer
     * @param msg message to user
     * @return the quantity that user entered
     */
    private static int getQuantity(String msg) {
        //Loop if there is an exception
        do {
            try {
                String input = getNonBlankString(msg);
                int i = Integer.parseInt(input);
                //Throw exception if input smaller than 1
                if (i <= 0) {
                    throw new Exception("Quantity must be positive");
                }
                return i;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } while (true);
    }

    /**
     * Prompt user to enter price as a positive Double
     * @param msg message to user
     * @return the price user entered
     */
    private static double getPrice(String msg) {
        //Loop if there is an exception
        do {
            try {
                String input = getNonBlankString(msg);
                double i = Double.parseDouble(input);
                //Throw exception if input not positive
                if (i <= 0) {
                    throw new Exception("Price must be positive");
                }
                return i;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    /**
     * Print search result
     * @param t ArrayList of <code>Book</code>
     * @throws Exception if the list is empty
     */
    public static void printSearch(ArrayList<Book> t) throws Exception {
        Book b = BookList.searchBook(t).getT();
        //Print the book information if it is not a null object
        if (b != null) {
            System.out.println(b);
        } else {
            System.out.println("Not found");
        }
    }

    /**
     * Search book in the list with  the given code
     * @param t ArrayList of <code>Book</code>
     * @return a pair value of book and the code used to search that user entered
     * @throws Exception if the list is empty
     */
    public static Pair<Book, String> searchBook(ArrayList<Book> t) throws Exception {
        //Throw exception if empty list
        if (t.isEmpty()) {
            throw new Exception("Empty list");
        }
        String searchCode = getNonBlankString("Enter code: ");
        //Find any book that match the search code. If none exists then return null
        Book book = t.stream().filter(b -> b.getCode().equals(searchCode)).findAny().orElse(null);
        return new Pair(book, searchCode);
    }

    /**
     * Search many books in the list with the given code
     * @param t ArrayList of <code>Book</code>
     * @param searchSubstring part of code to search
     * @return all book that contains a part of the search code
     * @throws Exception if the list is empty
     */
    public static ArrayList<Book> searchBook(ArrayList<Book> t, boolean searchSubstring) throws Exception {
        //Throw exception if the list is empty
        if (t.isEmpty()) {
            throw new Exception("Empty list");
        }
        String code = getNonBlankString("Enter code: ");
        //Return any book that contains the search code string
        return t.stream()
                .filter(b -> b.getCode().contains(code))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Display all the books in list
     * @param t ArrayList of <code>Book</code>
     * @throws Exception if list is empty
     */
    public static void displayBookList(ArrayList<Book> t) throws Exception {
        //Throw exception if the list is empty
        if (t.isEmpty()) {
            throw new Exception("Empty list");
        }
        //Print all the book in the list
        t.stream().forEach(book -> System.out.println(book));
    }
    
    /**
     * Update price with the given code 
     * @param t ArrayList of <code>Book</code>
     * @throws Exception if the list is empty
     */
    public static void updatePrice(ArrayList<Book> t) throws Exception {
        //Throw exception if the list is empty
        if (t.isEmpty()) {
            throw new Exception("Empty list");
        }
        Book b = searchBook(t).getT();
        //Update the book's price if it's in the list
        if (b == null) {
            System.out.println("Not found");
        } else {
            b.setPrice(getPrice("Enter price: "));
            System.out.println("Update successfully");
        }
    }

    /**
     * Find the first max price book in the list
     * @param t ArrayList of <code>Book</code>
     * @return a pair of value: (Max Price, Index of the first book that has max value)
     * @throws Exception if the list is empty
     */
    public static Pair<Double, Integer> findFirstMaxPrice(ArrayList<Book> t)
            throws Exception {
        //Check if the file is empty
        if (t.isEmpty()) {
            throw new Exception("Empty list");
        }
        //Find the max price in the list
        double maxPrice = Collections.max(t, Comparator
                .comparing(Book::getPrice))
                .getPrice();
        //Find the position of the first max price book
        int pos = IntStream.range(0, t.size())
                .filter(i -> t.get(i).getPrice() == maxPrice)
                .findFirst().getAsInt();
        return new Pair(maxPrice, pos);
    }

    /**
     * Sort the list of book by code in ascending order. Note that the code
     * of books are <code>String</code>. 
     *
     * @param t ArrayList of <code>Book</code>
     * @throws Exception is the list is empty
     */
    public static void sortListByCode(ArrayList<Book> t) throws Exception {
        //Check if the file is empty
        if (t.isEmpty()) {
            throw new Exception("Empty list");
        }
        Collections.sort(t, Comparator.comparing(Book::getCode));
        System.out.println("Sort successfully");
    }

    /**
     * Remove a book from list with given code
     *
     * @param t ArrayList of <code>Book</code>
     * @return <code>true</code> if the deletion is successful.
     * <code>false</code> otherwise
     * @throws Exception if the list is empty
     */
    public static boolean removeBook(ArrayList<Book> t) throws Exception {
        //Check if the list is empty
        if (t.isEmpty()) {
            throw new Exception("Empty list");
        }
        boolean deleteSuccess = t.remove(searchBook(t).getT());
        //Print out the result if the deletion is successful
        if (deleteSuccess) {
            System.out.println("Delete Successfully");
        } else {
            System.out.println("Not found");
        }
        return deleteSuccess;
    }

    /**
     * Load the booklist from data file
     *
     * @param filename path to the data file
     * @param t ArrayList of <code>Book</code>
     * @return <code>true</code> if load from file succeed. Otherwise,
     * <code> false</code>
     */
    public static boolean loadFromFile(String filename, ArrayList<Book> t) {
        File f = new File(filename);
        //Check if file exists
        if (!f.exists()) {
            System.out.println("here");
            return false;
        } else {
            t.removeAll(t);
            try (FileReader fr = new FileReader(f);
                    BufferedReader bf = new BufferedReader(fr)) {
                String line;
                //Keep scan the file until the first null occurence
                while ((line = bf.readLine()) != null) {
                    line = line.trim();
                    //Only scan file that isn't empty
                    if (line.length() > 0) {
                        StringTokenizer stk = new StringTokenizer(line, ",");
                        String code = stk.nextToken().trim();
                        String title = stk.nextToken().trim();
                        int qua = Integer.parseInt(stk.nextToken().trim());
                        double price = Double.parseDouble(stk.nextToken().trim());
                        //Throw exception if data has duplicates code.
                        if (t.stream().filter(b -> b.getCode().equals(code)).findFirst().isPresent()) {
                            throw new Exception("There are duplicate codes in the data");   
                        }
                        t.add(new Book(code,title,qua,price));
                    }
                }
                bf.close();
                fr.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("Load successfully");
            return true;
        }
    }

    /**
     * Display the list of option as a menu
     *
     * @param listOptions an arrayList of option to choose
     */
    public void displayMenu(ArrayList<String> listOptions) {
        System.out.println("BOOK MANAGEMENT SYSTEM");
        int totalOption = listOptions.size();
        //print all options start with index from 1 to the end
        for (int i = 0; i < totalOption; i++) {
            System.out.println("        " + (i + 1) + ".  " + listOptions.get(i));
        }
    }

    /**
     * Prompt user to select an option in range min-max
     *
     * @param min min value of option
     * @param max max value of option
     * @param message message
     * @return the option that user choose
     */
    public int getOption(int min, int max, String message) {
        int input;
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println(message);
            String s = sc.nextLine();
            //throw exception if the input is empty
            if (s.isEmpty()) {
                throw new Exception("Empty input");
            }
            input = Integer.parseInt(s);
            //throw exception if the input is not in range from min to max
            if (input < min || input > max) {
                throw new Exception("Not in range [" + min + "-" + max + "]");
            }
            return input;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getOption(min, max, message);
        }
    }

    /**
     * Prompt user to enter a char (Y/N)
     *
     * @param msg message to user
     * @return input char
     */
    private static char inputChar(String msg) {
        char c;
        Scanner sc = new Scanner(System.in);
        //continuing asking user to enter a character y or n
        do {
            try {
                c = getNonBlankString(msg).toUpperCase().charAt(0);
                //throw exception if the choice isn't y or n
                if (c != 'Y' && c != 'N') {
                    throw new Exception("Please enter Y or N");
                }
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
        return c;
    }
    
    /**
     * Print out the result of the <code>findFirstMaxPrice()</code> method
     * @param t ArrayList of <code>Book</code>
     * @throws Exception if the list is empty
     */
    public static void FirstMaxPrice(ArrayList<Book> t) throws Exception {
        Pair<Double, Integer> p = findFirstMaxPrice(t);
        System.out.println("Max Price: " + p.getT());
        System.out.println("Index: " + p.getU());
    }
    
    /**
     * Ask user to enter any key to continue
     */
    public static void PressEnterKey() {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Press \"ENTER\" key to continue...");
        try {
            input.readLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Inner class that supports method to return more than a pair of values.
     *
     * @param <T> First generic type
     * @param <U> Second generic type
     */
    public static class Pair<T, U> {

        public final T t;
        public final U u;

        public Pair(T t, U u) {
            this.t = t;
            this.u = u;
        }

        public T getT() {
            return t;
        }

        public U getU() {
            return u;
        }
    }
}
