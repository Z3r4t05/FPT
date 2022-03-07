
import java.util.Hashtable;
import java.util.Set;

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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Hashtable<String, String> ht = new Hashtable<>();
        ht.put("abcd", "acm");
        ht.put("abcd", "asdfasdf");
//        System.out.println(ht.get("abcd"));
        System.out.println(ht);
    }
    public static void show(Hashtable<String, String> hashtable) {
        Set<String> keySet = hashtable.keySet();
        for (String key : keySet) {
            System.out.println(key + " " + hashtable.get(key));
        }
    }
    
}
