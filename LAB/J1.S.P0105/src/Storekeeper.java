/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ADMIN
 */
public class Storekeeper {
    private int Id;
    private String name;

    public Storekeeper(int Id, String name) {
        this.Id = Id;
        this.name = name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Storekeeper{" + "Id=" + Id + ", name=" + name + '}';
    }
    
    
}
