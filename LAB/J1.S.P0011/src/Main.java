
import java.math.BigInteger;

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
        Inputter inputter = new Inputter();
        Converter converter = new Converter();
        int choice = 0;
        do {            
            //1. display menu
            MenuInterface.displayMenu(choice);
            //2. prompt user to enter base number input
            int baseIn = inputter.getBaseIn(inputter.getOption("Choose base for number input: ", 1, 4));
            //3. prompt user to enter base number ouput
            int baseOut = inputter.getBaseOut(baseIn);
            //4. prompt user to enter input value
            String inputNum = inputter.getInputValue("Enter number input: ", baseIn);
            //5. process converting base and display the result
            MenuInterface.displayResult(converter.convert(inputNum, baseIn, baseOut));
        } while (true);
    }
    
}
