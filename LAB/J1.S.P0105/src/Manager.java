
import java.util.ArrayList;
import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ADMIN
 */
class Manager {

    void displayMenu() {
        ArrayList<String> listOptions = new ArrayList<>(Arrays.asList(
                "Add storekeeper",
                "Add product",
                "Update product",
                "Search product",
                "Sort product",
                "Exit"
        ));
        System.out.println("");
        System.out.println("                Menu");
        System.out.println("=======================================");
        int totalOption = listOptions.size();
        //print all options start with index from 1 to the end
        for (int i = 0; i < totalOption; i++) {
            System.out.println((i + 1) + ". " + listOptions.get(i));
        }
        System.out.println("=======================================");
    }

    int select() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void addStorekeeper(ArrayList<String> listStorekeeper) {
    }

    void addProduct(ArrayList<Product> listProduct) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void updateProduct(ArrayList<Product> listProduct) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void searchProduct(ArrayList<Product> listProduct) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void sortProduct(ArrayList<Product> listProduct) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
