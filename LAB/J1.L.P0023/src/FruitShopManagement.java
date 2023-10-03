
import java.util.ArrayList;
import java.util.Hashtable;
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

    int createFruit(ArrayList<Fruit> listFruits, int id) {
        char choice;
        //keep working until user choose n
        do {
            String name = getNonBlankStr("Enter fruit's name: ");
            int price = getPrice("Enter price: ");
            int quantity = getQuantity("Enter quantity: ");
            String origin = getNonBlankStr("Enter origin: ");
            listFruits.add(new Fruit(id, name, price, quantity, origin));
            choice = inputChar("Do you want to continue (Y/N)? ");
            //increase id by 1 if user continue adding fruit
            if (choice == 'Y') {
                id++;
            }
        } while (choice == 'Y');
        displayListFruits(listFruits);
        return id;
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

    void viewOrders(Hashtable<Integer, Order> tableOrders) throws Exception {
        //throw exception if the orders are empty
        if (tableOrders.isEmpty()) {
            throw new Exception("There are no orders.");
        }
        Set<Integer> keySet = tableOrders.keySet();
        //loop though each key in keySet and display the order corresponding to that key in hashtable
        for (Integer key : keySet) {
            tableOrders.get(key).display();
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
        System.out.println("List of Fruit:");
        System.out.println("| ++ Item ++ | ++ Fruits ++ |"
                + " ++ Origin ++ | ++ Price ++ |");
        //Display all fruit inside the list of fruits
        for (Fruit f : listFruits) {
            System.out.printf("  %6.6s        %-10.10s     %-10.10s     %4d$\n",
                    f.getId(), f.getName(), f.getOrigin(), f.getPrice());
        }
    }

    void shopping(ArrayList<Fruit> listFruits,
            Hashtable<Integer, Order> tableOrders) throws Exception {
        //throw exception when list of fruit is empty
        if (listFruits.isEmpty()) {
            throw new Exception("List of fruits is empty");
        }
        Fruit fruit = new Fruit();
        ArrayList<Fruit> listItems = new ArrayList<>();
        int quantity;
        char choice;
        //continuing ordering if user choose N. 
        do {
            this.displayListFruits(listFruits);
            //continue asking user to choose an item from the list if user doesn't enter a valid id
            do {
                try {
                    fruit = selectFruit("Choose an item from the list above: ", listFruits);
                    System.out.println("You selected: " + fruit.getName());
                    break;
                } catch (NullPointerException e) {
                    System.out.println("Not found! Please choose another fruit");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } while (true);
            //check if the input quantity exceeds the in-stock quantity. 
            do {
                try {
                    quantity = getQuantity("Please input quantity: ");
                    //throw exception if in-stock quantity is not enough. Otherwise, subtract the quantity in stock.
                    if (fruit.getQuantity() < quantity) {
                        throw new Exception("There are only " + fruit.getQuantity()
                                + " left.");
                    } else {
                        //Find the fruit in the list and update its quantity
                        for (Fruit fruitInStock : listFruits) {
                            //update the quantity of the fruit in arraylist
                            if (fruitInStock.getId() == fruit.getId()) {
                                fruitInStock.setQuantity(fruitInStock.getQuantity() - quantity);
                                fruit = new Fruit(fruitInStock.getId(),
                                        fruitInStock.getName(), fruitInStock.getPrice(),
                                        quantity, fruitInStock.getOrigin());
                                //delete fruit in the list of fruit if it is out of stock
                                if (fruitInStock.getQuantity() == 0) {
                                    listFruits.remove(fruitInStock);
                                }
                                break;
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
            boolean outOfStock = true;
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
                System.out.println("All fruits are out of stock. Finish ordering.");
                break;
            }
            choice = inputChar("Do you want to order now (Y/N): ");
        } while (choice == 'N' || choice == 'n');
        this.displayListItems(listItems);
        String customerName = this.getNonBlankStr("Input your name: ");
        Order order = new Order(tableOrders.size() + 1, customerName, listItems);
        tableOrders.put(order.getId(), order);
        System.out.println("Shopping Completed!");
    }

    /**
     * Display list of items that user bought.
     *
     * @param listItems list of items that user bought
     * @param option 1 for viewing orders, otherwise for shopping
     */
    public void displayListItems(ArrayList<Fruit> listItems) {
        int totalPrice = 0;
        System.out.println("Product | Quantity | Price | Amount");
        //Display each fruit in list of item and sum-up total price
        for (int i = 0; i < listItems.size(); i++) {
            int amount = listItems.get(i).getPrice() * listItems.get(i).getQuantity();
            System.out.printf((i + 1) + ". %-9.9s%-3d     %3d$    %4d$\n",
                    listItems.get(i).getName(),
                    listItems.get(i).getQuantity(),
                    listItems.get(i).getPrice(),
                    amount);
            totalPrice += amount;
        }
        System.out.println("Total: " + totalPrice + "$");
        System.out.println("");
    }

    /**
     * Prompt user to select an item from the list of fruits using its id.
     *
     * @param msg message to user
     * @param listFruits list of fruit
     * @return the fruit that user choose
     * @throws Exception if user doesn't enter an existed id
     */
    private Fruit selectFruit(String msg, ArrayList<Fruit> listFruits) throws Exception {
        //loop until there are no exception caught
        do {
            try {
                String input = getNonBlankStr(msg);
                int id = Integer.parseInt(input);
                //Loop through the list of fruits to search the id
                for (Fruit fruit : listFruits) {
                    //if the id is found then return the fruit
                    if (fruit.getId() == id) {
                        return fruit;
                    }
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter an integer");
            }
        } while (true);
        return null;
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
    private void addItem(ArrayList<Fruit> listItems, Fruit fruit, int quantity) {
        //If user enter the same fruit again then sum up the quantity
        for (Fruit fruitInList : listItems) {
            //set the item quantity to be the sum of the current quantity with the new ordering quantity
            if (fruitInList.getId() == fruit.getId()) {
                fruitInList.setQuantity(fruitInList.getQuantity() + fruit.getQuantity());
                return;
            }
        }
        listItems.add(fruit);
    }
}
