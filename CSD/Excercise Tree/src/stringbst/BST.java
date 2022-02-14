package stringbst;

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

    public Node search(String x) {
        Node q = root;
        while (q != null) {
            if (x.compareTo(q.info) > 0) {
                q = q.right;
            } else if (x.compareTo(q.info) < 0) {
                q = q.left;
            } else {
                return q;
            }
        }
        return null;
    }

    public void insert(String x) {
        if (search(x) == null) {
            Node newnode = new Node(x);
            Node q = root;
            Node parent = null;//trailing
            //traverse down
            while (q != null) {
                parent = q;
                if (x.compareTo(q.info) < 0) {
                    q = q.left;
                } else {
                    q = q.right;
                }
            }
            if (parent == null) {
                root = newnode;
            } else if (x.compareTo(q.info) < 0) {
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
        q.enqueue(root);
        Node p;
        while (!q.isEmpty()) {
            try {
                p = (Node) q.dequeue();
                System.out.print(p.info + " ");
                if (p.left != null) {
                    q.enqueue(p.left);
                }
                if (p.right != null) {
                    q.enqueue(p.right);
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
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public boolean isAVL(Node root) {
        int l, r;
        if (root == null) {
            return true;
        }
        l = height(root.left);
        r = height(root.right);
        return Math.abs(l - r) <= 1 && isAVL(root.left) && isAVL(root.right);
    }

    public void del(String x) {
        if (isEmpty()) {
            return;
        }
        Node p = root;
        Node parent = null;
        //traverse
        while (p != null) {
            if (p.info == null ? x == null : p.info.equals(x)) {
                break;
            }
            parent = p;
            if (x.compareTo(p.info) < 0) {
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

    public static void main(String[] args) {
        BST tree = new BST();
        
    }

//    private Node del(Node root, int x) {
//        if(root.info != null) {
//            
//        }
//    }
    public void delByCopying(Node p) {
        Node rm = p.left;
        Node parentRm = p;
        while (rm.right != null) {
            parentRm = rm;
            rm = rm.right;
        }
        p.info = rm.info;
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

    public void deleteByMerge(Node root, String info) {
        root = deleteMerge(root, info);
    }

    public Node deleteMerge(Node node, String info) {
        //node = null means that the node is not in the tree
        if (node == null) {
            return node;
        } //Now recursively traverse from root to the node that need to remove
        //the node as the root will be return;
        else if (info.compareTo(node.info) < 0) {
            node.left = deleteMerge(node.left, info);
            return node;
        } else if (info.compareTo(node.info) > 0) {
            node.right = deleteMerge(node.right, info);
            return node;
        } //Now we find the node to remove
        else {
            //leaf or the node to remove has only one child on the right
            if (node.left == null) {
                return node.right;
            }
            //Similar with the above
            if (node.right == null) {
                return node.left;
            }
            //if the node to remove has 2 child, delete by merge 2 subtree
            //1-FIND THE DIRECT PREDECESSOR
            Node tmp = node.left;
            while(tmp.right != null) {
                tmp = tmp.right;
            }
            //append the right subtree to the direct predecessor
            tmp.right = node.right;
            //now we link the parent directly to the node's left subtree. so
            //we return the node.left
            return node.left;
        }
    }
}
