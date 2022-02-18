/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treeperson;

/**
 *
 * @author ADMIN
 */
public class Person {
    String id;
    String name1;
    int age;
    public static void main(String[] args) {
//        System.out.println(fun(3,3));
        fun(9);
    }
//    static int fun(int a, int n) {
//        System.out.println("here");
//        if(n==0) {
//            return 1;
//        }
//        int t = fun(a,n/2);
//        if(n%2==0) return t*t;
//        else return (t*t*a);
//    }
    static void fun(int n){
            if(n<0) return;
            else if(n<7) {
                System.out.println(n);
                fun(n-2);
            } else fun(n-2);
    }
}
