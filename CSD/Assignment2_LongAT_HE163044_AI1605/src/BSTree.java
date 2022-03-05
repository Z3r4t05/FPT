
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ADMIN
 */
public class BSTree {

    Song root;

    BSTree() {
        root = null;
    }

    /**
     * Check empty
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return (root == null);
    }

    /**
     * Clear tree
     */
    public void clear() {
        root = null;
    }

    public Song search(String x) {
        Song q = root;
        while (q != null) {
            if (x.compareTo(q.id) > 0) {
                q = q.right;
            } else if (x.compareTo(q.id) < 0) {
                q = q.left;
            } else {
                return q;
            }
        }
        return null;
    }

    public void insert(String id, String name, double rating) {
        if (search(id) == null) {
            Song newnode = new Song(id, name, rating);
            Song q = root;
            Song parent = null;//trailing
            //traverse down
            while (q != null) {
                parent = q;
                if (q.id.compareTo(id) > 0) {
                    q = q.left;
                } else {
                    q = q.right;
                }
            }
            if (parent == null) {
                root = newnode;
            } else if (id.compareTo(parent.id) < 0) {
                parent.left = newnode;
            } else {
                parent.right = newnode;
            }
        }
    }
/*
      6
    1   7
   3   5   9
    4    8
    
    */
    public void breathth() {
        if (root == null) {
            return;
        }
        MyQueue q = new MyQueue();
        q.enqueue(root);
        Song p;
        Song x = null;
        while (!q.isEmpty()) {
            try {
                p = (Song) q.dequeue();
                if(p != root && p.left != null && p.right != null && this.findHeight(this.root, p.id) < 5)
                   x = p;
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
        this.del(x.id);
    }
public void breathth4() {
        if (root == null) {
            return;
        }
        MyQueue q = new MyQueue();
        q.enqueue(root);
        Song p;
        Song x = null;
        while (!q.isEmpty()) {
            try {
                p = (Song) q.dequeue();
                System.out.println("(" + p.id + "," + p.name + "," + p.rating
                + ")");
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
    public void preorder(Song p) {
        if (p == null) {
            return;
        }
//        System.out.print(p.id + " ");
        System.out.println(p);
        preorder(p.left);
        preorder(p.right);
    }

    public void inorder(Song p) {
        if (p == null) {
            return;
        }
        inorder(p.left);
        if (p.rating >= 4) {
            System.out.println("(" + p.id + "," + p.name + "," + p.rating
                    + "," + this.getLevel(root, p.id) + ")");
        }
        inorder(p.right);
    }

    public void postorder(Song p) {
        if (p == null) {
            return;
        }
        postorder(p.left);
        postorder(p.right);
        System.out.print(p.id + " ");
    }
static int findHeightUtil(Song root, String x)
{
     
    // Base Case
    if (root == null)
    {
        return -1;
    }
    int height;
    // Store the maximum height of
    // the left and right subtree
    int leftHeight = findHeightUtil(root.left, x);
 
    int rightHeight = findHeightUtil(root.right, x);
 
    // Update height of the current node
    int ans = Math.max(leftHeight, rightHeight) + 1;
 
    // If current node is the required node
    if (root.id.equals(x))
        height = ans;
 
    return ans;
}
 
// Function to find the height of
// a given node in a Binary Tree
 int findHeight(Song root, String x)
{
     
    // Stores height of the Tree
    int height = findHeightUtil(root, x);
 
    // Return the height
    return height;
}
    public int count(Song root) {
        if (root == null) {
            return 0;
        }
        return 1 + count(root.left) + count(root.right);
    }

    public boolean loadFromFile(String filename) {
        File f = new File(filename);
        if (!f.exists()) {
            return false;
        } else {
            try (FileReader fr = new FileReader(f); BufferedReader bf = new BufferedReader(fr)) {
                String line;
                while ((line = bf.readLine()) != null) {
                    line = line.trim();
                    if (line.length() > 0) {
                        StringTokenizer stk = new StringTokenizer(line, "|");
                        String id = stk.nextToken().trim();
                        String name = stk.nextToken().trim();
                        double rated = Double.parseDouble(stk.nextToken().trim());
                        Song s = new Song(id, name, rated);
                        if (name.contains("Paris") || rated < 3) {
                        } else {
                            this.insert(id, name, rated);
                        }
                    }
                }
            } catch (IOException | NumberFormatException e) {
                System.err.println(e);
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        BSTree tree = new BSTree();
        tree.loadFromFile("src\\song.txt");
        
        System.out.println("");
        tree.q2(tree, "src\\q2.out");
        tree.q3("src\\q3.txt");
        tree.q4();
        tree.breathth4();

    }

    public void breadthq3(PrintWriter pw) {
        if (root == null) {
            return;
        }
        MyQueue q = new MyQueue();
        q.enqueue(root);
        Song p;
        while (!q.isEmpty()) {
            try {
                p = (Song) q.dequeue();
                pw.println("(" + p.id + "," + p.name + "," + p.rating + ")");
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

    public boolean q3(String filename) {
        this.breathth();
        try (FileWriter fw = new FileWriter(filename);
                PrintWriter pw = new PrintWriter(fw)) {
            Song p = this.root;
            this.breadthq3(pw);
            pw.close();
            fw.close();
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
        return true;
    }

    public void inorderq2(Song p, PrintWriter pw) {
        if (p == null) {
            return;
        }
        inorderq2(p.left, pw);
        if (p.rating >= 4) {
            pw.println("(" + p.id + "," + p.name + "," + p.rating
                    + "," + this.getLevel(root, p.id) + ")");
        }
        inorderq2(p.right, pw);
    }

    public boolean q2(BSTree tree, String filename) {
        try (FileWriter fw = new FileWriter(filename);
                PrintWriter pw = new PrintWriter(fw)) {
            Song p = this.root;
            this.inorderq2(p, pw);
            pw.close();
            fw.close();
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
        return true;
    }

    int getLevelUtil(Song node, String id, int level) {
        if (node == null) {
            return 0;
        }

        if (node.id.equals(id)) {
            return level;
        }

        int downlevel
                = getLevelUtil(node.left, id, level + 1);
        if (downlevel != 0) {
            return downlevel;
        }

        downlevel
                = getLevelUtil(node.right, id, level + 1);
        return downlevel;
    }

    /* Returns level of given data value */
    int getLevel(Song node, String id) {
        return getLevelUtil(node, id, 1);
    }

    public void del(String x) {
        if (isEmpty()) {
            return;
        }
        Song p = root;
        Song parent = null;
        //traverse
        while (p != null) {
            if (p.id.equals(x)) {
                break;
            }
            parent = p;
            if (p.id.compareTo(x) > 0) {
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

    }

    public void delByCopying(Song p) {
        Song rm = p.left;
        Song parentRm = p;
        while (rm.right != null) {
            parentRm = rm;
            rm = rm.right;
        }
        p.id = rm.id;
        p.name = rm.name;
        p.rating = rm.rating;
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

    public void q4() {
        root = this.buildTree(root);
    }
    
    void storeBSTNodes(Song root, Vector<Song> nodes)
    {
        // Base case
        if (root == null)
            return;
 
        // Store nodes in Inorder (which is sorted
        // order for BST)
        storeBSTNodes(root.left, nodes);
        nodes.add(root);
        storeBSTNodes(root.right, nodes);
    }
 
    /* Recursive function to construct binary tree */
    Song buildTreeUtil(Vector<Song> nodes, int start,
            int end)
    {
        // base case
        if (start > end)
            return null;
 
        /* Get the middle element and make it root */
        int mid = (start + end) / 2;
        Song node = nodes.get(mid);
 
        /* Using index in Inorder traversal, construct
           left and right subtress */
        node.left = buildTreeUtil(nodes, start, mid - 1);
        node.right = buildTreeUtil(nodes, mid + 1, end);
 
        return node;
    }
 
    // This functions converts an unbalanced BST to
    // a balanced BST
    public Song buildTree(Song root)
    {
        // Store nodes of given BST in sorted order
        Vector<Song> nodes = new Vector<Song>();
        storeBSTNodes(root, nodes);
 
        // Constructs BST from nodes[]
        int n = nodes.size();
        return buildTreeUtil(nodes, 0, n - 1);
    }
 

 
}
