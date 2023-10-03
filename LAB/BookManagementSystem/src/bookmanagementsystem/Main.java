/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookmanagementsystem;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author ADMIN
 */
public class Main {

    public static void main(String[] args) {
        ArrayList<Book> t = new ArrayList<>();
        BookList list = new BookList();
        ArrayList<String> listOptions = new ArrayList<>(Arrays.asList(
                "Input & add book(s) to the end",
                "Display all books",
                "Search a book",
                "Update the book's price",
                "Find (first) max price value",
                "Sort the list ascendingly by code",
                "Remove a book",
                "Load from file (replace current list)",
                "Exit"
        ));
        
        //Loop until user choose to exit the program
        do {
            try {
                String code;
                list.displayMenu(listOptions);
                //Call method base on choosed option
                switch(list.getOption(1, listOptions.size(), "Choose an option: ")) {
                    case 1:
                        BookList.addBook(t);
                        break;
                    case 2:
                        BookList.displayBookList(t);
                        break;
                    case 3:
                        BookList.printSearch(t);
                        break;
                    case 4:
                        BookList.updatePrice(t);
                        break;
                    case 5:
                        BookList.FirstMaxPrice(t);
                        break;
                    case 6:
                        BookList.sortListByCode(t);
                        break;
                    case 7:
                        BookList.removeBook(t);
                        break;
                    case 8:
                        BookList.loadFromFile("src\\bookmanagementsystem\\booklist.txt", t);
                        break;
                    case 9:
                        System.exit(0);
                }
                BookList.PressEnterKey();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                BookList.PressEnterKey();
            }
        } while (true);
    }
}
