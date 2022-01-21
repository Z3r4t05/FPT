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

    int info;
    Node left, right;

    Node(int x, Node p, Node q) {
        info = x;
        left = p;
        right = q;
    }

    Node(int x) {
        this(x, null, null);
    }
}
