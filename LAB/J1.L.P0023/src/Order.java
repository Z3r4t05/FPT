
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
public class Order {

    private int id;
    private String customerName;
    private ArrayList<Fruit> listItems;
    private int totalPrice;

    public Order(int id, String customerName, ArrayList<Fruit> listItems) {
        this.id = id;
        this.customerName = customerName;
        this.listItems = listItems;
        this.totalPrice = 0;
        //Loop to count the total price of the order
        for (Fruit fruit : listItems) {
            this.totalPrice += fruit.getPrice() * fruit.getQuantity();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public ArrayList<Fruit> getListItems() {
        return listItems;
    }

    public void setListItems(ArrayList<Fruit> listItems) {
        this.listItems = listItems;
    }

    public void display() {
        System.out.println("Customer: " + this.customerName);
        System.out.println("Product | Quantity | Price | Amount");
        //Display each fruit in list of item and sum-up total price
        for (int i = 0; i < this.listItems.size(); i++) {
            System.out.printf((i + 1) + ". %-9.9s%-3d     %3d$    %4d$\n",
                    listItems.get(i).getName(),
                    listItems.get(i).getQuantity(),
                    listItems.get(i).getPrice(),
                    listItems.get(i).getPrice() * listItems.get(i).getQuantity());
        }
        System.out.println("Total: " + this.totalPrice + "$");
        System.out.println("");
    }

    public static void main(String[] args) {
        System.out.println("Product | Quantity | Price | Amount");
        System.out.printf(2 + ". %-9.9s%-3d     %3d$    %4d$\n",
                "mango",
                50,
                1,
                2);
    }
}
