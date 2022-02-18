/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AVL;

/**
 *
 * @author ADMIN
 */
public class Node {

    int key, height;
    Node left, right;

    Node(int d) {
        key = d;
    }

    public int countChild() {
        if (this.left == null && this.right == null) {
            return 0;
        } else if ((this.left != null && this.right == null) || (this.left == null && this.right != null)) {
            return 1;
        } else {
            return 2;
        }
    }

    public Node getParent(Node root) {
        if (this == root) {
            return null;
        } else {
            while (root != null) {
                if (root.left == this || root.right == this) {
                    return root;
                } else if (this.key < root.key) {
                    root = root.left;
                } else {
                    root = root.right;
                }
            }
        }
        return null;
    }
}
