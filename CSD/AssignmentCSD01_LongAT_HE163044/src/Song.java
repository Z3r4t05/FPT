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

    String name;
    String artist;
    int rated;

    public Song(String name, String artist, int rated) {
        this.name = name;
        this.artist = artist;
        this.rated = rated;
    }

    @Override
    public String toString() {
        return this.name +"|"+ this.artist +"|"+ this.rated;
    }

}
