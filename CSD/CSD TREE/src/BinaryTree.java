/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ADMIN
 */
public class BinaryTree {

    Node root;

    BinaryTree() {
        root = null;
    }

    public void insert(int x) {
        if (root == null) {
            root = new Node(x);
            return;
        }
        Node f, p;
        p = root;//iterator
        f = null;//parent
        while (p != null) {
            if (p.info == x) {
                System.err.println(" The key " + x + " "
                        + "already exists, no insertion");
                return;
            }
            f = p;
            if (x < p.info) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        if (x < f.info) {
            f.left = new Node(x);
        } else {
            f.right = new Node(x);
        }
    }

    public void visit(Node p) {

    }

    public void preOrder(Node p) {
        if (p == null) {
            return;
        }
        System.out.print(p.info + " ");
        preOrder(p.left);
        preOrder(p.right);
    }

    public void inOrder(Node p) {
        if (p == null) {
            return;
        }
        inOrder(p.left);
        System.out.print(p.info + " ");
        inOrder(p.right);
    }

    public void postOrder(Node p) {
        if (p == null) {
            return;
        }
        postOrder(p.left);
        postOrder(p.right);
        System.out.print(p.info + " ");
    }

    public void postOrder() {
        postOrder(root);
    }

    public void inOrder() {
        inOrder(root);
    }

    public void preOrder() {
        preOrder(root);
    }
    public void BFS(Node p) {
        
    }
    public Node search(Node p, int x) {
        if (p == null) {
            return null;
        }
        if (p.info == x) {
            return p;
        }
        if (x < p.info) {
            return search(p.left, x);
        } else {
            return search(p.right, x);
        }
    }

    public void deleteByMerging(int x) {
    }

    public void deleteByCopying(int x) {
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
        System.out.println("pre");
        tree.preOrder();
        System.out.println("in");
        tree.inOrder();
        System.out.println("ppost");
        tree.postOrder();
    }

}
