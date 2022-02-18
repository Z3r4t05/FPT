/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AVL;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class AVL {

    private Node root;

    public AVL() {
    }

    /**
     * If return the height of node n, if n is null then n = -1
     *
     * @param n
     * @return
     */
    public int height(Node n) {
        return n == null ? -1 : n.height;
    }

    /**
     * update height of the tree
     *
     * @param n
     */
    public void updateHeight(Node n) {
        n.height = 1 + Math.max(height(n.left), height(n.right));
    }

    /**
     * public void updateHeight(Node n) { get the balance factor
     *
     * @param n
     * @return
     */
    public int getBalance(Node n) {
        return (n == null) ? 0 : height(n.right) - height(n.left);
    }

    /**
     * rotate right
     *
     * @param n
     * @return
     */
    public Node rotateRight(Node n) {
        /*  x < y < n
              n                  x
             /                    \
            x          ->          n
             \                    /
              y                  y
         */
        Node x = n.left;
        Node y = x.right;
        //rotate
        x.right = n;
        n.left = y;
        //update height;
        updateHeight(n);
        updateHeight(x);
        return x;// new root of subtree
    }

    /*
    preorder tim node dau tien co 2 con roi left rotate
    inorder tim node dau tien co height < 3, xoay trai node nay
    duyet prepostinbeadth xoa node dau tien co 2 con
    bst pperson key id
     */
    public Node rotateLeft(Node n) {
        /* n < y < x
           n        x
            \      /
             x -> n
            /      \ 
           y        y
         */
        Node x = n.right;
        Node y = x.left;
        //rotate
        x.left = n;
        n.right = y;
        //update height;
        updateHeight(n);
        updateHeight(x);
        return x;// new root of subtree
    }

    Node rebalance(Node z) {
        updateHeight(z);
        int balance = getBalance(z);
        //right sub tree has greater height
        if (balance > 1) {
            //single rotation
            if (height(z.right.right) > height(z.right.left)) {
                /*
                       z                           x
                     /   \                       /   \
                   t1     x                     z     y
                         /  \       ->         /\    / \
                      t2     y                t1 t2  t3 t4
                            / \
                           t3  t4
                 */
                z = rotateLeft(z);
            } else {
                /*
                       z                 z                x
                      / \               / \              /  \
                    t1   y       ->    t1  x       ->   z     y
                        /  \               /\          / \   / \
                       x   t4             t2 y        t1 t2 t3 t4
                      / \                    /\
                     t2  t3                 t3 t4
                 */
                z.right = rotateRight(z.right);
                z = rotateLeft(z);
            }
            //similar for left side
        } else if (balance < -1) {
            if (height(z.left.left) > height(z.left.right)) {
                z = rotateRight(z);
            } else {
                z.left = rotateLeft(z.left);
                z = rotateRight(z);
            }
        }
        return z;
    }

    Node insert(Node node, int key) {
        if (node == null) {
            return new Node(key);
        } else if (node.key > key) {
            node.left = insert(node.left, key);
        } else if (node.key < key) {
            node.right = insert(node.right, key);
        } else {
            throw new RuntimeException("duplicate");
        }
        return rebalance(node);
    }

    public boolean isEmpty() {
        return (root == null);
    }

    Node find(int key) {
        Node current = root;
        while (current != null) {
            if (current.key == key) {
                break;
            }
            current = current.key < key ? current.right : current.left;
        }
        return current;
    }

    Node deleteNode(Node root, int key) {
        // STEP 1: PERFORM STANDARD BST DELETE
        if (root == null) {
            return root;
        }

        // If the key to be deleted is smaller than
        // the root's key, then it lies in left subtree
        if (key < root.key) {
            root.left = deleteNode(root.left, key);
        } // If the key to be deleted is greater than the
        // root's key, then it lies in right subtree
        else if (key > root.key) {
            root.right = deleteNode(root.right, key);
        } // if key is same as root's key, then this is the node
        // to be deleted
        else {

            // node with only one child or no child
            if ((root.left == null) || (root.right == null)) {
                Node temp = null;
                if (temp == root.left) {
                    temp = root.right;
                } else {
                    temp = root.left;
                }

                // No child case
                if (temp == null) {
                    temp = root;
                    root = null;
                } else // One child case
                {
                    root = temp; // Copy the contents of
                }                                // the non-empty child
            } else {

                // node with two children: Get the inorder
                // successor (smallest in the right subtree)
                Node temp = this.mostLeftChild(root.right);

                // Copy the inorder successor's data to this node
                root.key = temp.key;

                // Delete the inorder successor
                root.right = deleteNode(root.right, temp.key);
            }
        }

        // If the tree had only one node then return
        if (root == null) {
            return root;
        }

        // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
        root.height = Math.max(height(root.left), height(root.right)) + 1;

        // STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether
        // this node became unbalanced)
        int balance = getBalance(root);

        // If this node becomes unbalanced, then there are 4 cases
        // Left Left Case
        if (balance > 1 && getBalance(root.left) >= 0) {
            return rotateRight(root);
        }

        // Left Right Case
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = rotateLeft(root.left);
            return rotateRight(root);
        }

        // Right Right Case
        if (balance < -1 && getBalance(root.right) <= 0) {
            return rotateLeft(root);
        }

        // Right Left Case
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rotateRight(root.right);
            return rotateLeft(root);
        }

        return root;
    }

    private Node mostLeftChild(Node node) {
        Node current = node;
        /* loop down to find the leftmost leaf */
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    public static void main(String[] args) {
        int[] arr = {7,8,4,0,1,-1,10,24};
        AVL avl = new AVL();
        for (int i : arr) {
            avl.root = avl.insert(avl.root, i);
        }

        System.out.print("\npost ");
        avl.postorder(avl.root);
        System.out.print("\npre ");
        avl.preorder(avl.root);
        System.out.print("\nin ");
        avl.inorder(avl.root);
        System.out.print("\nbreadth ");
        avl.breathth();

//        System.out.println("\n\ndeleting");
////        avl.deleteNode(avl.root, 32);
//        avl.root = avl.del(avl.root, 32);
//        System.out.print("\npost ");
//        avl.postorder(avl.root);
//        System.out.print("\npre ");
//        avl.preorder(avl.root);
//        System.out.print("\nin ");
//        avl.inorder(avl.root);
//        System.out.print("\nbreadth ");
//        avl.breathth();

    }

    public void breathth() {
        if (root == null) {
            return;
        }
        MyQueue q = new MyQueue();
        q.enqueue(root);
        Node p;
        while (!q.isEmpty()) {
            try {
                p = (Node) q.dequeue();
                System.out.print(p.key + " ");
                if (p.left != null) {
                    q.enqueue(p.left);
                }
                if (p.right != null) {
                    q.enqueue(p.right);
                }
            } catch (Exception ex) {
                Logger.getLogger(AVL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ArrayList<Integer> toarr(Node p) {
        ArrayList<Integer> arr = new ArrayList<>();
        add2arr(p, arr);
        return arr;
    }
    
    public void add2arr(Node root, ArrayList<Integer> arr) {
        if(root == null) return;
        add2arr(root.left, arr);
        arr.add(root.key);
        add2arr(root.right,arr);
        
    }
    public void preorder(Node p) {
        if (p == null) {
            return;
        }
        System.out.print(p.key + " ");
        preorder(p.left);
        preorder(p.right);
    }

    public void inorder(Node p) {
        if (p == null) {
            return;
        }
        inorder(p.left);
        System.out.print(p.key + " ");
        inorder(p.right);
    }

    public void postorder(Node p) {
        if (p == null) {
            return;
        }
        postorder(p.left);
        postorder(p.right);
        System.out.print(p.key + " ");
    }

    public Node del(Node root, int x) {
        if (isEmpty()) {
            return null;
        }
//        System.out.println(this.root.right.height);
//        System.out.println(this.root.left.height);
        Node p = root;
        Node parent = null;
        //traverse
        while (p != null) {
            if (p.key == x) {
                break;
            }
            parent = p;
            if (p.key > x) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        //p=null, x not found
        if (p == null) {
            return this.root;
        }
        //p==x
        //if p has no leaf or p is a leaf
        if (p.left == null && p.right == null) {
            if (parent == null) {
                this.root = null;
            } else if (parent.left == p) {
                parent.left = null;
            } else {
                parent.right = null;
            }

            //if p has only one child
        } else if (p.left != null && p.right == null) {
            if (parent == null) {
                root = p.left;
            } else if (parent.left == p) {
                parent.left = p.left;
            } else {
                parent.right = p.left;
            }
        } else if (p.left == null && p.right != null) {
            if (parent == null) {
                root = p.right;
            } else if (parent.left == p) {
                parent.left = p.right;
            } else {
                parent.right = p.right;
            }
            //if p has 2 childs
        } else {
            delByCopying(p);
        }
        System.out.println(p.key);
        ArrayList<Integer> arr =  toarr(this.root);
        AVL b = new AVL();
        for(Integer i : arr) {
            b.root = b.insert(b.root, i);
        }

        return b.root;
//        System.out.println(parent.key + " " + this.getBalance(parent));
//        System.out.println(parent.getParent(this.root).key +" " + this.getBalance((parent.getParent(this.root))));
//        System.out.println(this.root.right.left.left.key.);
//        System.out.println(this.root.left.height);
////        if(p!=root) {
////            
////            Node back = parent;
////            System.out.println(back.key);
////            while(back != null) {
////                this.rebalance(back);
////                System.out.println(back.key);
////                if(back.getParent(this.root)== null) {
////                    break;
////                }
////                back = back.getParent(this.root);                  
////                System.out.println(back.key + " " + this.getBalance(back));
////            }
////        }
//        
////        this.updateHeight(this.root);
//        //  System.out.println(this.getBalance(this.root.left));
////        System.out.println(this.getBalance(this.root.right));
////        System.out.println(this.getBalance(this.root));
////        this.updateHeight(this.root.left);
////        this.updateHeight(this.root.right);
////        
////        System.out.println(this.getBalance(this.root.left));
////        System.out.println(this.getBalance(this.root.right));
////        System.out.println(this.getBalance(this.root));

    

    }

    public void delByCopying(Node p) {
        Node rm = p.left;
        Node parentRm = p;
        while (rm.right != null) {
            parentRm = rm;
            rm = rm.right;
        }
        p.key = rm.key;
        if (parentRm == p) {
            /*
                delete
              /        \
       skip this        *
        /
        *    
             */
            p.left = rm.left;
        } else {

            parentRm.right = null;
        }
        /*
              p=parent  ->         p=rm
          p.l=rm    p.r        *           p.r
      *                    *     * 
        
   *    * 
        
                    p   ->      ->                 p= rm
      p.l =parent       p.r             p.l = parent      p.r
        
  p.l.l        p.l.r = rm        p.l.l    p.l.r=rm
         */
    }
}
