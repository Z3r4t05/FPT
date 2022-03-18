
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
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
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        FruitShopManagement manager = new FruitShopManagement();
        ArrayList<Fruit> listFruits = new ArrayList<>();
        Hashtable<String, ArrayList<Item>> tableOrders = new Hashtable<>();
        int choice = 0;
        ArrayList<String> listOptions = new ArrayList<>(Arrays.asList(
                "Create fruit",
                "View orders",
                "Shopping (for buyer)",
                "Exit"));
        do {
            try {
                //step 1: Display menu
                manager.displayMenu(listOptions);
                //step 2: Ask user to enter input 
                choice = manager.getOption(1, 4);
                //step 3: Perform function based on user choice
                switch (choice) {
                    //create fruit
                    case 1:
                        manager.createFruit(listFruits);
                        break;
                    // view orders
                    case 2:
                        manager.viewOrders(tableOrders);
                        break;
                    //shopping (for buyer)
                    case 3:
                        manager.shopping(listFruits, tableOrders);
                        break;
                    //Exit
                    case 4:
                        System.exit(0);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage() + "\n");
            }
        } while (true);
    }

}
