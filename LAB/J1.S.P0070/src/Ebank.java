
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ADMIN
 */
public class Ebank {
    public void setLocale(Locale locale) {
        
    }
    public static void main(String[] args) {
        Locale vietnam = new Locale("vi", "VN");
        ResourceBundle languageHandler = ResourceBundle.getBundle("language", vietnam);
        Enumeration keys = languageHandler.getKeys();
        while (keys.hasMoreElements()) {
            String key = (String)keys.nextElement();
            String val = languageHandler.getString(key);
            System.out.println(key + " " + val);
        }
    }
}
