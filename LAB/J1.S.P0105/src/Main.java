
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ADMIN
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<String> listStorekeeper = new ArrayList<>();
        ArrayList<Product> listProduct = new ArrayList<>();
        Manager manager = new Manager(); // step 1: display menu
        manager.displayMenu();
        // step 2: ask user to select options from menu {
        int choice = manager.select();
        // step 3: perform function based on user's choice
        switch (choice) {
            //add new storekeeper by name
            case 1:
                manager.addStorekeeper(listStorekeeper);
                break;
            //add new product 
            case 2:
                manager.addProduct(listProduct);
                break;
            //update product
            case 3:
                manager.updateProduct(listProduct);
                break;
            //search product
            case 4:
                manager.searchProduct(listProduct);
                break;
            //sort product
            case 5:
                manager.sortProduct(listProduct);
                break;
                

        }
    }

}
