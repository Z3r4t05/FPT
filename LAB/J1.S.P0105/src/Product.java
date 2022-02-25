
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ADMIN
 */
class Product {
    private String id;
    private String name;
    private String location;
    private BigDecimal price;
    private LocalDate expiryDate;
    private LocalDate manufacturedDate;
    private String category;
    private String Storekeeper;
    private LocalDate ReceiptDate;

    public String getId() {
        return id;
    }

    public Product(String id, String name, String location, BigDecimal price, LocalDate expiryDate, LocalDate manufacturedDate, String category, String Storekeeper, LocalDate ReceiptDate) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.price = price;
        this.expiryDate = expiryDate;
        this.manufacturedDate = manufacturedDate;
        this.category = category;
        this.Storekeeper = Storekeeper;
        this.ReceiptDate = ReceiptDate;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", location=" + location + ", price=" + price + ", expiryDate=" + expiryDate + ", manufacturedDate=" + manufacturedDate + ", category=" + category + ", Storekeeper=" + Storekeeper + ", ReceiptDate=" + ReceiptDate + '}';
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public LocalDate getManufacturedDate() {
        return manufacturedDate;
    }

    public void setManufacturedDate(LocalDate manufacturedDate) {
        this.manufacturedDate = manufacturedDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStorekeeper() {
        return Storekeeper;
    }

    public void setStorekeeper(String Storekeeper) {
        this.Storekeeper = Storekeeper;
    }

    public LocalDate getReceiptDate() {
        return ReceiptDate;
    }

    public void setReceiptDate(LocalDate ReceiptDate) {
        this.ReceiptDate = ReceiptDate;
    }
 
}
