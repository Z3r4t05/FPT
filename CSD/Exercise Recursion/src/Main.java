/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ADMIN
 */
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
        if() return 1;
        
        if()

    }

    public static int GCD(int m, int n) {
        if (n > 0) {
            return GCD(n, m % n);
        } else {
            return m;
        }
    }
    
    public static int min(int a, int b) {
        return (a < b) ? a : b;
    }
    
    private static int power(int x, int n) {
        if (n == 0) {
            return 1;
        } else {
            return x * power(x, n - 1);
        }
    }
}
