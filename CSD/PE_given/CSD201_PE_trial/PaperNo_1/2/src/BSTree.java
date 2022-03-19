/* This program contains 2 parts: (1) and (2)
   YOUR TASK IS TO COMPLETE THE PART  (2)  ONLY
 */
//(1)==============================================================
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class BSTree {

    Node root;

    BSTree() {
        root = null;
    }

    boolean isEmpty() {
        return (root == null);
    }

    void clear() {
        root = null;
    }

    void fvisit(Node p, RandomAccessFile f) throws Exception {
        if (p != null) {
            f.writeBytes(p.info + " ");
        }
    }

    void preOrder(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        fvisit(p, f);
        preOrder(p.left, f);
        preOrder(p.right, f);
    }

    void preOrder2(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        if (p.info.price >= 3 && p.info.price <= 5) {

            fvisit(p, f);
        }
        preOrder2(p.left, f);
        preOrder2(p.right, f);
    }

    void inOrder(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        inOrder(p.left, f);
        fvisit(p, f);
        inOrder(p.right, f);
    }

    void postOrder(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        postOrder(p.left, f);
        postOrder(p.right, f);
        fvisit(p, f);
    }

    void breadth(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        Queue q = new Queue();
        q.enqueue(p);
        Node r;
        while (!q.isEmpty()) {
            r = q.dequeue();
            fvisit(r, f);
            if (r.left != null) {
                q.enqueue(r.left);
            }
            if (r.right != null) {
                q.enqueue(r.right);
            }
        }
    }

    void loadData(int k) //do not edit this function
    {
        String[] a = Lib.readLineToStrArray("data.txt", k);
        int[] b = Lib.readLineToIntArray("data.txt", k + 1);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            insert(a[i], b[i]);
        }
    }

//===========================================================================
//(2)===YOU CAN EDIT OR EVEN ADD NEW FUNCTIONS IN THE FOLLOWING PART========
//===========================================================================
    void insert(String xOwner, int xPrice) {//You should insert here statements to complete this function
        if ((xOwner.charAt(0) == 'B') || (xPrice > 100)) {

        } else {
            Node newnode = new Node(new Car(xOwner, xPrice));
            Node q = root;
            Node parent = null;
            while (q != null) {
                parent = q;
                if (q.info.price > xPrice) {
                    q = q.left;
                } else {
                    q = q.right;
                }
            }
            if (parent == null) {
                root = newnode;
            } else if (xPrice < parent.info.price) {
                parent.left = newnode;
            } else {
                parent.right = newnode;
            }
        }

    }
/**
 * •	void insert(string xOwner, int xPrice) - check if xOwner.charAt(0) = 'B' or xPrice>100   then do nothing, otherwise insert new car with owner=xOwner, price=xPrice to the tree. 

•	void f1() – You do not need to edit this function. Your task is to complete the insert(...) function above only. Output in the file f1.txt must be the following:
(A,5) (C,2) (E,4) (G,3) (D,6) (F,7) 
(C,2) (G,3) (E,4) (A,5) (D,6) (F,7)


 * @throws Exception 
 */
    void f1() throws Exception {/* You do not need to edit this function. Your task is to complete insert  function
        above only.
         */
        clear();
        loadData(1);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        preOrder(root, f);
        f.writeBytes("\r\n");
        inOrder(root, f);
        f.writeBytes("\r\n");
        f.close();
    }
/**
•	void f2() – Perform pre-order traversal from the root but display to file f2.txt nodes having price in the interval [3,5] only. Hint: Copy the function preOrder(...)  to preOrder2(...) and modify it. Output in the file f2.txt must be the following:
(C,6) (D,2) (F,4) (H,3) (I,5) (E,8) (G,7) 
(F,4) (H,3) (I,5)


 * @throws Exception 
 */
//=============================================================
    void f2() throws Exception {
        clear();
        loadData(4);
        String fname = "f2.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        preOrder(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/

        //------------------------------------------------------------------------------------
        this.preOrder2(root, f);
        f.writeBytes("\r\n");
        f.close();
    }
/**
•	void f3() – Perform breadth-first traversal from the root and delete by copying the first node having both 2 sons and price < 7. Output in the file f3.txt must be the following:
(C,8) (D,6) (E,9) (F,2) (G,7) (H,1) (I,3) (J,5) (K,4) 
(C,8) (J,5) (E,9) (F,2) (G,7) (H,1) (I,3) (K,4)

 * @throws Exception 
 */
// f.writeBytes(" k = " + k + "\r\n");
//=============================================================
    void f3() throws Exception {
        clear();
        loadData(7);
        String fname = "f3.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        breadth(root, f);
        f.writeBytes("\r\n");

        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
        //------------------------------------------------------------------------------------
        this.breathth();

        breadth(root, f);
        f.writeBytes("\r\n");
        f.close();
    }

    public void del(int x) {
        if (isEmpty()) {
            return;
        }
        Node p = root;
        Node parent = null;
        //traverse
        while (p != null) {
            if (p.info.price == x) {
                break;
            }
            parent = p;
            if (p.info.price > x) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        //p=null, x not found
        if (p == null) {
            return;
        }
        //p==x
        //if p has no leaf or p is a leaf
        if (p.left == null && p.right == null) {
            if (parent == null) {
                root = null;
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
    }//=============================================================

    public void delByCopying(Node p) {
        Node rm = p.left;
        Node parentRm = p;
        while (rm.right != null) {
            parentRm = rm;
            rm = rm.right;
        }
        System.out.println(rm.info);
        p.info = rm.info;
        if (parentRm == p) {
            p.left = rm.left;
        } else {
            parentRm.right = rm.left;
        }
    }

    public void breathth() {
        if (root == null) {
            return;
        }
        Queue q = new Queue();
        q.enqueue(root);
        Node p;
        while (!q.isEmpty()) {
            try {
                p = (Node) q.dequeue();
                if (p.left != null && p.right != null && p.info.price < 7) {
                    this.delByCopying(p);
                    break;
                }
                if (p.left != null) {
                    q.enqueue(p.left);
                }
                if (p.right != null) {
                    q.enqueue(p.right);
                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public Node breathth4() {
        if (root == null) {
            return null;
        }
        Queue q = new Queue();
        q.enqueue(root);
        Node p;
        while (!q.isEmpty()) {
            try {
                p = (Node) q.dequeue();
                if (p.left != null && p.info.price < 7) {
                    p = this.rotateRight(p);
                    break;
                }
                if (p.left != null) {
                    q.enqueue(p.left);
                }
                if (p.right != null) {
                    q.enqueue(p.right);
                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        return null;
    }

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

        return x;// new root of subtree
    }
/**
 * •	void f4() – Perform breadth-first traversal from the root and find the first node p having left son and price < 7. Rotate p to right about its’ left son. Output in the file f4.txt must be the following:
(C,8) (D,6) (E,9) (F,2) (G,7) (H,1) (I,3) (J,5) (K,4) 
(C,8) (F,2) (E,9) (H,1) (D,6) (I,3) (G,7) (J,5) (K,4)

 * @throws Exception 
 */
    void f4() throws Exception {
        clear();
        loadData(10);
        String fname = "f4.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        breadth(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/

        //------------------------------------------------------------------------------------
        Queue q = new Queue();
        q.enqueue(root);
        Node p = root;
        while (!q.isEmpty()) {
            try {
                p = (Node) q.dequeue();
                if (p.left != null && p.info.price < 7) {
                    Node x = p.left;
                    Node y = x.right;
                    //rotate
                    x.right = p;
                    p.left = y;
                    if(this.parent(p.info.price).right == p ) {
                        this.parent(p.info.price).right = x;
                    } else this.parent(p.info.price).left = x;
                            
                    break;
                }
                if (p.left != null) {
                    q.enqueue(p.left);
                }
                if (p.right != null) {
                    q.enqueue(p.right);
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

        breadth(root, f);
        f.writeBytes("\r\n");
        f.close();
    }

    public Node parent(int price) {
        Node p = root;
        if (root == null) {
            return null;
        }
        if (root.info.price == price) {
            return root;
        }
        while (p != null) {
            if(p.left.info.price == price || p.left.info.price == price) { 
                return p;
            } else if (p.info.price < price) {
                p = p.right;
            } else {
                p = p.left;
            }
        }
        return null;
    }

}
