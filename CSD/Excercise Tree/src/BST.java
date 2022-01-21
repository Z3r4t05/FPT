/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ADMIN
 */
public class BST {

    private Node root;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    BST() {
        root = null;
    }

    /**
     * Check empty
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return (this.getRoot() == null);
    }

    /**
     * Clear tree
     */
    public void clear() {
        this.setRoot(null);
    }

    public Node search(int x) {
        Node root = this.getRoot();
        while(root!=null) {
            if(x > root.getInfo()) root = root.getRight();
            else if(x < root.getInfo()) root = root.getLeft();
            else return root;
        }
        return null;
    }
    
    public void insert(Node root, int x) {
        root = this
    }
}
