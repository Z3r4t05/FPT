/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ADMIN
 */
import java.util.LinkedList;

public class MyQueue {

    LinkedList<Song> t;

    MyQueue() {
        t = new LinkedList<>();
    }

    void clear() {
        t.clear();
    }

    boolean isEmpty() {
        return (t.isEmpty());
    }

    void enqueue(Song p) {
        t.addLast(p);
    }

    Song dequeue() {
        if (isEmpty()) {
            return (null);
        }
        return (t.removeFirst());
    }

    Song front() {
        if (isEmpty()) {
            return (null);
        }
        return (t.getFirst());
    }
}
