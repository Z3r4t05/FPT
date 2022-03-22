
import java.util.ArrayList;
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
public class AccountDatabase {
    public static ArrayList database() {
        return  new ArrayList<>(Arrays.asList(
                new Account("0123456789", "123456ab"),
                new Account("9999999999", "abcd1234"),
                new Account("1111111111", "1234abcd")
        ));
    }
}
