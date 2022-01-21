/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ADMIN
 */
public class Node {

    Song info;
    Node next;

    Node(Song s) {
        this.info = new Song(s.name, s.artist, s.rated);
        this.next = null;
    }

    Node(String name, String artist, int rated) {
        this.info = new Song(name, artist, rated);
        this.next = null;
    }

    public boolean hasNext() {
        return next != null;
    }
}
