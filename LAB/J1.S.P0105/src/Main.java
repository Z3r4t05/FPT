
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
        ArrayList<Storekeeper> listStorekeeper = new ArrayList<>();
        ArrayList<Product> listProduct = new ArrayList<>();
        ManageProductAndStorekeeper manager = new ManageProductAndStorekeeper();
        int choice = 0;
        //Stop the program when user choose 6
        do {
            try {
                // step 1: display menu
                manager.displayMenu();
                // step 2: ask user to select options from menu 
                choice = manager.selectOption("Enter your choice: ", 1, 6);
                // step 3: perform function based on user's choice
                switch (choice) {
                    //add new storekeeper by name
                    case 1:
                        manager.addStorekeeper(listStorekeeper);
                        break;
                    //add new product 
                    case 2:
                        manager.addProduct(listProduct, listStorekeeper);
                        break;
                    //update product
                    case 3:
                        manager.updateProduct(listProduct, listStorekeeper);
                        break;
                    //search product
                    case 4:
                        manager.searchProduct(listProduct);
                        break;
                    //sort product
                    case 5:
                        manager.sortProduct(listProduct);
                        break;
                    //Exit    
                    case 6:
                        System.exit(0);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (choice != 6);
    }

}
