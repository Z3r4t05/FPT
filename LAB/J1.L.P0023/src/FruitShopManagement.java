
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ADMIN
 */
class FruitShopManagement {

    /**
     * Display the list of option as a menu
     *
     * @param listOptions an arrayList of option to choose
     */
    public void displayMenu(ArrayList<String> listOptions) {
        System.out.println("FRUIT SHOP SYSTEM");
        int totalOption = listOptions.size();
        //print all options start with index from 1 to the end
        for (int i = 0; i < totalOption; i++) {
            System.out.println("        " + (i + 1) + ".  " + listOptions.get(i));
        }
    }

    /**
     * Take input string from user which is not blank
     *
     * @param msg message to user
     * @return the inputted string (which is re-capitalize)
     */
    private String getNonBlankStr(String msg) {
        Scanner sc = new Scanner(System.in);
        String result;
        System.out.println(msg);
        result = normalizeAndRecapitalize(sc.nextLine());
        //throw new exception for empty input
        if (result.isEmpty()) {
            System.out.print("Empty input!\n");
            return getNonBlankStr(msg);
        }
        return result;
    }

    /**
     * Normalize and recapitalize the string
     *
     * @param S string input
     * @return string after normalize and recapitalize
     */
    private String normalizeAndRecapitalize(String S) {
        //Check if the input is empty
        if (S.length() == 0) {
            return S;
        }
        StringTokenizer stk = new StringTokenizer(S, " ");
        String result = capitalizeFirstChar(stk.nextToken().toLowerCase());
        //Capitalize the first char of each token and merge them back into input
        while (stk.hasMoreElements()) {
            result += " " + capitalizeFirstChar(stk.nextToken().toLowerCase());
        }
        return result;
    }

    /**
     * Capitalize the first character of the string
     *
     * @param str
     * @return the string after capitalize the first char
     */
    private String capitalizeFirstChar(String str) {
        //If the string is null or empty return immediately.
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    void createFruit(ArrayList<Fruit> listFruits) {
        char choice;
        //keep working until user choose n
        do {
            String name = getNonBlankStr("Enter fruit's name: ");
            int price = getPrice("Enter price: ");
            int quantity = getQuantity("Enter quantity: ");
            String origin = getNonBlankStr("Enter origin: ");
            listFruits.add(new Fruit(Integer.toString(listFruits.size() + 1),
                    name, price, quantity, origin));
            choice = inputChar("Do you want to continue (Y/N)? ");
        } while (choice == 'Y');

        displayListFruits(listFruits);

    }

    /**
     * Prompt user to select an option in range min-max
     *
     * @param min min value of option
     * @param max max value of option
     * @return the value of user choice as an integer
     */
    public int getOption(int min, int max) {
        String message = "\n(Please choose 1 to create product,"
                + " 2 to view order, "
                + "3 for shopping, "
                + "4 to Exit program).";
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
            return getOption(min, max);
        }
    }

    void viewOrders(Hashtable<String, ArrayList<ArrayList<Item>>> tableOrders) throws Exception {
        //throw exception if the orders are empty
        if (tableOrders.isEmpty()) {
            throw new Exception("There are no orders.");
        }
        Set<String> keySet = tableOrders.keySet();
        int index = 1;
        //leep through key set and display all item in each orders in hashtable
        for (String key : keySet) {
            System.out.println("Customer: " + key);
            this.displayAllOrders(tableOrders.get(key));
            System.out.println("");
        }
    }

    private int getPrice(String msg) {
        Scanner sc = new Scanner(System.in);
        int price;
        //stop when there are no expetion caught
        do {
            try {
                System.out.println(msg);
                price = Integer.parseInt(sc.nextLine());
                //throw exception if the price is negative
                if (price < 0) {
                    throw new Exception("Price must be positive");
                }
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
        return price;
    }

    private int getQuantity(String msg) {
        Scanner sc = new Scanner(System.in);
        int quantity;
        //stop when there are no exception
        do {
            try {
                System.out.println(msg);
                quantity = Integer.parseInt(sc.nextLine());
                //throw exception if quantity is negative
                if (quantity < 0) {
                    throw new Exception("Quantity must be positive");
                }
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
        return quantity;
    }

    private void displayListFruits(ArrayList<Fruit> listFruits) {
        System.out.println("| ++ Item ++ | ++ Fruits ++ |"
                + " ++ Origin ++ | ++ Price ++ |");
        //Display all fruit inside the list of fruits
        for (Fruit f : listFruits) {
            System.out.printf("  %6.6s        %-10.10s     %-10.10s     %4d$\n",
                    f.getId(), f.getName(), f.getOrigin(), f.getPrice());
        }
    }

    void shopping(ArrayList<Fruit> listFruits,
            Hashtable<String, ArrayList<ArrayList<Item>>> tableOrders) throws Exception {
        //throw exception when list of fruit is empty
        if (listFruits.isEmpty()) {
            throw new Exception("list fruits is empty");
        }
        boolean outOfStock = true;
        //Check all fruit in list if there is an fruit that isn't out of stock
        for (Fruit f : listFruits) {
            //if the quantity > 0 then change flag to false
            if (f.getQuantity() > 0) {
                outOfStock = false;
                break;
            }
        }
        //throw excpetion if all fruit are out of stock
        if (outOfStock == true) {
            throw new Exception("All of fruits are out of stock.");
        }
        Fruit fruit = new Fruit();
        ArrayList<Item> listItems = new ArrayList<>();
        int quantity;
        char choice;
        //continuing ordering if user choose N after enter quantity
        do {
            System.out.println("List of Fruit:");
            this.displayListFruits(listFruits);
            //continue asking user to choose an item from the list if user doesn't enter a valid id
            do {
                try {
                    fruit = selectItem("Choose an item from the list above: ", listFruits);
                    System.out.println("You selected: " + fruit.getName());
                    //throw exception if the fruit is out of stock
                    if (fruit.getQuantity() == 0) {
                        throw new Exception("Out of stock");
                    }
                    break;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } while (true);
            //check if the input quantity exceeds the in-stock quantity. 
            do {
                try {
                    quantity = this.getQuantity("Please input quantity: ");
                    //throw exception if in-stock quantity is not enough. Otherwise, subtract the quantity in stock.
                    if (fruit.getQuantity() < quantity) {
                        //tell user if the fruit is out of stock or the quantity left
                        throw new Exception("There are only " + fruit.getQuantity()
                                + " left.");

                    } else {
                        //Find the fruit in the list and update its quantity
                        for (Fruit f : listFruits) {
                            //update the quantity of the fruit in arraylist
                            if (f.getId().equals(fruit.getId())) {
                                f.setQuantity(f.getQuantity() - quantity);
                            }
                        }
                    }
                    break;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } while (true);
            addItem(listItems, fruit, quantity);
            System.out.println("Item added!!");
            choice = inputChar("Do you want to order now (Y/N): ");
            outOfStock = true;
            //Check all fruit in list if there is an fruit that isn't out of stock
            for (Fruit f : listFruits) {
                //if the quantity > 0 then change flag to false
                if (f.getQuantity() > 0) {
                    outOfStock = false;
                    break;
                }
            }
            //If everything is out of stock, break the loop and finish order
            if (outOfStock) {
                System.out.println("All fruits are out of stock");
                choice = 'Y';
            }
        } while (choice == 'N' || choice == 'n');
        this.displayListItems(listItems, 1);
        String customerName = this.getNonBlankStr("Input your name: ");
        addOrder(tableOrders, listItems, customerName);
        System.out.println("Shopping Completed!");
    }

    public void displayAllOrders(ArrayList<ArrayList<Item>> listOrders) {
        System.out.println("Product | Quantity | Price | Amount");
        //loop through all orders of a customer to print out each order
        for (ArrayList<Item> arr : listOrders) {
            int count = 1;
            int totalPrice = 0;
            //loop to display each item in order and count the total
            for (Item i : arr) {
                System.out.printf(count + ". %-7.7s  %3d       %3d$    %4d$\n",
                        i.getFruit().getName(),
                        i.getQuantity(),
                        i.getFruit().getPrice(),
                        i.getAmount());
                count++;
                totalPrice += i.getAmount();
            }
            System.out.println("Total: " + totalPrice + "$");
            System.out.println("---------------------------------------");
        }
        //display all each item in itemlist and sum up the total price
    }

    /**
     * Display list of items that user bought.
     *
     * @param listItems list of items that user bought
     * @param option 1 for viewing orders, otherwise for shopping
     */
    public void displayListItems(ArrayList<Item> listItems, int option) {
        System.out.println("Product | Quantity | Price | Amount");
        int totalPrice = 0;
        //option 1 means displaying the list order. otherwise it is for display shopping
        if (option == 1) {
            //Display each item and sum-up the amount
            for (Item i : listItems) {
                System.out.printf("%-7.7s  %3d        %3d$    %4d$\n",
                        i.getFruit().getName(),
                        i.getQuantity(),
                        i.getFruit().getPrice(),
                        i.getAmount());
                totalPrice += i.getAmount();
            }
        } else {
            int count = 1;
            //Display each item and sum-up the amount
            for (Item i : listItems) {
                System.out.printf(count + ". %-7.7s  %3d       %3d$    %4d$\n",
                        i.getFruit().getName(),
                        i.getQuantity(),
                        i.getFruit().getPrice(),
                        i.getAmount());
                count++;
                totalPrice += i.getAmount();
            }
        }
        //display all each item in itemlist and sum up the total price
        System.out.println("Total: " + totalPrice + "$");
    }

    /**
     * Prompt user to select an item from the list of fruits using its id.
     *
     * @param msg message to user
     * @param listFruits list of fruit
     * @return the fruit that user choose
     * @throws Exception if user doesn't enter an existed id
     */
    private Fruit selectItem(String msg, ArrayList<Fruit> listFruits) throws Exception {
        String id = getNonBlankStr(msg);
        //Loop through the list of fruits to search the id
        for (Fruit fruit : listFruits) {
            //if the id is found then return the fruit
            if (fruit.getId().equals(id)) {
                return fruit;
            }
        }
        throw new Exception("please choose an id from the list");
    }

    /**
     * Prompt user to enter a char (y/n/Y/N)
     *
     * @param msg message to user
     * @return input char
     */
    private char inputChar(String msg) {
        char c;
        Scanner sc = new Scanner(System.in);
        //continuing asking user to enter a character y or n
        do {
            try {
                c = this.getNonBlankStr(msg).charAt(0);
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
     * add the new item to the ordering list. if the fruit is duplicate then
     * sum-up the quantity. otherwise add a new item to the list
     *
     * @param listItems list of items
     * @param fruit fruit to buy
     * @param quantity quantity to buy
     */
    private void addItem(ArrayList<Item> listItems, Fruit fruit, int quantity) {
        //If user enter the same fruit again then sum up the quantity
        for (Item i : listItems) {
            //set the item quantity to be the sum of the current quantity with the ordering quantity
            if (i.getFruit().equals(fruit)) {
                Item updateItem = new Item(fruit, i.getQuantity() + quantity);
                listItems.set(listItems.indexOf(i), updateItem);
                return;
            }
        }
        Item item = new Item(fruit, quantity);
        listItems.add(item);
    }

    private void addOrder(Hashtable<String, ArrayList<ArrayList<Item>>> tableOrders,
            ArrayList<Item> listItems, String customerName) {
        //if there is no customer name then add the list to arraylist of key customerName
        if (tableOrders.isEmpty()) {
            tableOrders.put(customerName, new ArrayList(Arrays.asList(listItems)));
        } else {
            //if there exists a customerName in the list, then add the order to the list of order and update
            if (tableOrders.containsKey(customerName)) {
                ArrayList<ArrayList<Item>> newListOrders = tableOrders.get(customerName);
                newListOrders.add(listItems);
                tableOrders.put(customerName, newListOrders);
            } else {
                tableOrders.put(customerName, new ArrayList(Arrays.asList(listItems)));
            }
        }
    }
//    /**
//     * add new order to the hashTable. if the customer is duplicate then merge
//     * the list of order into one
//     *
//     * @param tableOrders hashTable of orders
//     * @param listItems list of items in the order
//     * @param customerName name of customer
//     */
//    private void addOrder(Hashtable<String, ArrayList<ArrayList<Item>>> tableOrders,
//            ArrayList<Item> listItems, String customerName) {
//        //if the list is empty then we simply add a new order
//        if (tableOrders.isEmpty()) {
//            tableOrders.put(customerName, listItems);
//        } else {
//            Set<String> keySet = tableOrders.keySet();
//            //if customer's name is duplicated then we merge the list
//            if (tableOrders.containsKey(customerName)) {
//                ArrayList<Item> mergeTarget = tableOrders.get(customerName);
//                ArrayList<Item> toRemove = new ArrayList<>();
//                Set<Item> set = new LinkedHashSet<>(mergeTarget);
//                set.addAll(listItems);
//                ArrayList<Item> list = new ArrayList<>(set);
//                //loop though the list of customer to find all items that has the same fruit's id
//                for (int i = 0; i < list.size() - 1; i++) {
//                    //loop through all items after item i in list to find duplicate fruit's id
//                    for (int j = i+1; j < list.size(); j++) {
//                        //if 2 item has the same fruit then add one of them to set toRemove
//                        if(list.get(i).getFruit().getId().
//                                equals(list.get(j).getFruit().getId())) {
//                            //remove the one which has smaller quantity
//                            if(list.get(i).getQuantity()<list.get(j).getQuantity()) {
//                                toRemove.add(list.get(i));
//                            }
//                            //otherwise remove the other one
//                            if(list.get(i).getQuantity()>=list.get(j).getQuantity()) {
//                                toRemove.add(list.get(j));
//                            }
//                        }
//                    }             
//                }
//                Set<Item> removeSet = new LinkedHashSet<>(toRemove);
//                
//                System.out.println("set");
//                for(Item i : list) {
//                    System.out.println(i.getFruit() + " " + i.getQuantity());
//                }
//                System.out.println("toremove");
//                for(Item i : removeSet) {
//                    System.out.println(i.getFruit() + " " + i.getQuantity());
//                }
//                list.removeAll(toRemove);
//                System.out.println("set after remove");
//                for(Item i : list) {
//                    System.out.println(i.getFruit() + " " + i.getQuantity());
//                }
//                System.out.println("updating");
//
//                //compare each item in the list with the item in the remove set to add quantity
//                for (Item i : list) {
//                    //loop though all item in the remove set to find the item that has the same id as item i
//                    for (Item j : removeSet) {
//                        //if 2 item has the same fruit's id then sum-up the quantity and remove item j from remove set and update amount
//                        if( i.getFruit().getId().equals(j.getFruit().getId())) {
//                            i.setQuantity(i.getQuantity()+j.getQuantity());
//                            removeSet.remove(j);
//                            i.updateAmount();
//                            break;
//                        }
//                    }
//                }
//                System.out.println("set");
//                for(Item i : list) {
//                    System.out.println(i.getFruit() + " " + i.getQuantity());
//                }
//                tableOrders.put(customerName, list);
//            } else {
//                tableOrders.put(customerName, listItems);              
//            }
//        }
//    }
}
