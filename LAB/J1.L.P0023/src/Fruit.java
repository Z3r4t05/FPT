/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ADMIN
 */
public class Fruit {
    private int id;
    private String name;
    private int price;
    private int quantity;
    private String origin;

    public Fruit() {
    }

    public Fruit(int id, String name, int price, int quantity, String origin) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.origin = origin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOrigin() {
        return origin;
    }

    @Override
    public String toString() {
        return "Fruit{" + "id=" + id + ", name=" + name + ", price=" + price + ", quantity=" + quantity + ", origin=" + origin + '}';
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
    
}
