/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ADMIN
 */
public class Song {
    String id;
    String name;
    double rating;
    Song right;
    Song left;

    public Song(String id, String name, double rating) {
        this.id = id;
        this.name = name;
        this.rating = rating;
    }



    @Override
    public String toString() {
        return "Song{" + "id=" + id + ", name=" + name + ", rating=" + rating ;
    }
}
