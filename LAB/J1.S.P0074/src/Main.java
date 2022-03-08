
import java.util.ArrayList;
import java.util.Arrays;


public class Main {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        ArrayList<String> listOptions = new ArrayList<>(Arrays.asList(
                "Addition Matrix",
                "Subtraction Matrix",
                "Multiplication Matrix",
                "Quit"));
        //Return to main menu unless user choose exit
        do {
            //step 1: display menu
            calculator.displayMenu(listOptions);
            //step 2: ask users to select an option
            int choice = calculator.selectOption("Your choice: ", 1, 4);
            //step 3: perform function based on the selected option
            switch (choice) {
                //Addition
                case 1:
                    calculator.addMatrix();
                    break;
                //Subtraction
                case 2:
                    calculator.subtractMatrix();
                    break;
                //Multiplication
                case 3:
                    calculator.multiplyMatrix();
                    break;
                //Exit
                case 4:
                    System.exit(0);
            }
        } while (true);
    }
}
