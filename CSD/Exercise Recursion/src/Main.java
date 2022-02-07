
import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ADMIN
 */
class Node {

    int data;
    Node l, r;

    Node(int info) {
        data = info;
        l = r = null;
    }
}

class Tree {

    Node root;

    public static int maxDepth(Node node) {
        if (node == null) {
            return -1;
        } else {
            int ldepth = maxDepth(node.l);
            int rdepth = maxDepth(node.r);
            if (ldepth > rdepth) {
                return (ldepth + 1);
            } else {
                return rdepth + 1;
            }/*
            maxDepth('1') = max(maxDepth('2'), maxDepth('3')) + 1
                               = 1 + 1
                                  /    \
                                /         \
                              /             \
                            /                 \
                          /                     \
               maxDepth('2') = 1                maxDepth('3') = 0
= max(maxDepth('4'), maxDepth('5')) + 1
= 1 + 0   = 1         
                   /    \
                 /        \
               /            \
             /                \
           /                    \
 maxDepth('4') = 0     maxDepth('5') = 0
             */
        }

    }

    public static int maxHeight(Node node) {
        if (node.l == null && node.r == null) return 0;
        else {
            return 1 + Math.max(maxHeight(node.l), maxHeight(node.r));
        }
    }
}
public class Main {

    public static int intSum(int n) {
        if (n <= 1) {
            return n;
        }
        return n + intSum(n - 1);
    }

    public static int findMin(int a[], int n) {
        // if size = 0 means whole array
        // has been traversed
        if (n == 1) {
            return a[0];
        }
        return Math.min(a[n - 1], findMin(a, n - 1));
    }

    public static int findSum(int a[], int n) {
        if (n <= 0) {
            return 0;
        }
        return (findSum(a, n - 1) + a[n - 1]);
    }

    public static int isPalindrome(char a[], int n) {
        int len = a.length;
        if (len / 2 == n) {
            return 1;
        }
        if (a[n - 1] != a[len - n]) {
            return 0;
        }
        return isPalindrome(a, n - 1);
    }

    public static int GCD(int m, int n) {
        if (n > 0) {
            return GCD(n, m % n);
        } else {
            return m;
        }
    }

    public static int power(int x, int n) {
        if (n == 0) {
            return 1;
        } else {
            return x * power(x, n - 1);
        }
    }

    public static int bin(int[] arr, int size, int val) {

        int mid = size / 2;
//        System.out.print("arr ");
//        for (int i = 0; i < arr.length; i++) {
//            int j = arr[i];
//            System.out.print(j + " ");
//        }
//        System.out.print("\nmid = " + mid);
//        System.out.print(" a[mid] = " + arr[mid]);
//        System.out.println("size = " + size);
//        System.out.println("arr length" + arr.length);
        if (arr[mid] > val) {//left
            arr = Arrays.copyOfRange(arr, 0, mid);
            return bin(arr, mid, val);
        } else if (arr[mid] < val) {//right
            arr = Arrays.copyOfRange(arr, mid + 1, arr.length);
            return mid + 1 + bin(arr, size - mid - 1, val);
        } else {
            return mid;
        }
    }

    public static void main(String[] args) {
//        int[] arr = {1, 2, 3, 4, 5, 7, 8, 9, 10};
//        System.out.println(bin(arr, 9, 10));

//        System.out.println(gcd(13, 13));
//        System.out.println(gcd(37, 600));
//        System.out.println(gcd(20, 100));
//        System.out.println(gcd(100,20));
//        System.out.println(gcd(624129, 2061517));
//        System.out.println(fact(4));
//        System.out.println(addReciprocals(3));
        Tree tree = new Tree();
  
        tree.root = new Node(1);
        tree.root.l = new Node(2);
        tree.root.r = new Node(3);
        tree.root.l.l = new Node(4);
        tree.root.l.r = new Node(5);
  
        System.out.println("depth of tree is : " +
                                      Tree.maxDepth(tree.root));
        System.out.println("height " + Tree.maxHeight(tree.root));
    }

    public static int gcd(int m, int n) {
        if (n == 0) {
            return m;
        }
        if (m == 0) {
            return n;
        }
        if (m == n) {
            return m;
        }

        return (m > n) ? gcd(n, m % n) : gcd(m, n % m);
    }

    public static int fact(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * fact(n - 1);
    }

    public static int fib(int n) {
        if (n <= 2) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }
    
    public static double addReciprocals(int n) {
        if(n==1) return 1;
        return 1.0/n + addReciprocals(n-1);
    }
    
    public static int stirlingNum(int n1, int n2) {
        if(n1 == 0 && n2 == 0) return 1;
        if(n1 > 0 && n2 == 0) return 0;
        return stirlingNum(n1, n2 - 1) - n1*stirlingNum(n1, n2);
    }
}
