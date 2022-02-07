/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

/**
 *
 * @author ADMIN
 */
public class test2 {
    public static String dosth(Update update) {
        update.setB(false);
        return "";
    }
    public static void main(String[] args) {
        Test m = new Test();
        test2.dosth(m);
        System.out.println(m.isB());
    }
}
