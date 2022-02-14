package stringbst;



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

    String info;
    Node left, right;

    Node(String x, Node p, Node q) {
        this.info = x;
        this.left = p;
        this.right = q;
    }

    Node(String x) {
        this(x, null, null);
    }
}
