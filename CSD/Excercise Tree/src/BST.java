
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
public class BST {

    Node root;

    BST() {
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

    public Node search(int x) {
        Node q = root;
        while (q != null) {
            if (x > q.info) {
                q = q.right;
            } else if (x < q.info) {
                q = q.left;
            } else {
                return q;
            }
        }
        return null;
    }

    public void insert(int x) {
        if (search(x) == null) {
            Node newnode = new Node(x);
            Node q = root;
            Node parent = null;//trailing
            //traverse down
            while (q != null) {
                parent = q;
                if (q.info > x) {
                    q = q.left;
                } else {
                    q = q.right;
                }
            }
            if (parent == null) {
                root = newnode;
            } else if (x < parent.info) {
                parent.left = newnode;
            } else {
                parent.right = newnode;
            }
        }
    }

    public void breathth() {
        if (root == null) {
            return;
        }
        MyQueue q = new MyQueue();
        q.onqueue(root);
        Node p;
        while (!q.isEmpty()) {
            try {
                p = (Node) q.dequeue();
                System.out.print(p.info + " ");
                if (p.left != null) {
                    q.onqueue(p.left);
                }
                if (p.right != null) {
                    q.onqueue(p.right);
                }
            } catch (Exception ex) {
                Logger.getLogger(BST.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void preorder(Node p) {
        if (p == null) {
            return;
        }
        System.out.print(p.info + " ");
        preorder(p.left);
        preorder(p.right);
    }

    public void inorder(Node p) {
        if (p == null) {
            return;
        }
        inorder(p.left);
        System.out.print(p.info + " ");
        inorder(p.right);
    }

    public void postorder(Node p) {
        if (p == null) {
            return;
        }
        postorder(p.left);
        postorder(p.right);
        System.out.print(p.info + " ");
    }

    public int count(Node root) {
        if (root == null) {
            return 0;
        }
        return 1 + count(root.left) + count(root.right);
    }

    public Node min(Node p) {
        if (p.left == null) {
            return p;
        }
        return min(p.left);
    }

    public Node max(Node p) {
        if (p.right == null) {
            return p;
        }
        return max(p.right);
    }

    public int sum(Node root) {
        if (root == null) {
            return 0;
        }
        return root.info + sum(root.left) + sum(root.right);
    }

    public int avg() {
        return sum(root) / count(root);
    }

    public int maxheight(Node root) {
        if (root == null) {
            return 0;
        }
        int lefth = maxheight(root.left);
        int righth = maxheight(root.right);

        if (lefth > righth) {
            return lefth + 1;
        } else {
            return righth + 1;
        }
    }
    
    public int height(Node node) {
        if(node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }
    
    public int maxCostPath(Node root) {
        if(root == null) {
            return 0;
        } else if (root.left == null && root.right == null) {
            return root.info;
        } 
        int left = maxCostPath(root.left);
        int right = maxCostPath(root.right);
        return ((left > right) ? left : right) + root.info;
    }
    public boolean isAVL(Node root) {
        int l,r;
        if(root == null) return true;
        l = height(root.left);
        r = height(root.right);
        return Math.abs(l - r) <= 1 && isAVL(root.left) && isAVL(root.right);
    }
    
//    public void del(int x) {
//        del(root, x);
//    }
    public static void main(String[] args) {
        BST tree = new BST();
        tree.insert(6);
        tree.insert(4);
        tree.insert(2);
        tree.insert(5);
        tree.insert(1);
        tree.insert(3);
        tree.insert(7);
        tree.insert(9);
        System.out.println(tree.maxheight(tree.root));
        System.out.println(tree.maxCostPath(tree.root));
        tree.inorder(tree.root);
    }

//    private Node del(Node root, int x) {
//        if(root.info != null) {
//            
//        }
//    }
}
