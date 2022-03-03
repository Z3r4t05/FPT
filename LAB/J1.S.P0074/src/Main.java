
public class Main {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        //step 1: display menu
        calculator.displayMenu();
        //step 2: ask users to select an option
        int choice = calculator.selectOption("Your choice: ", 1, 4);
        //step 3: perform function based on the selected option
        switch (choice) {
            case 1:
                calculator.addMatrix();
                break;
            case 2:
                calculator.subtractMatrix();
                break;
            case 3:
                calculator.multiplyMatrix();
                break;
        }
                

    }

}
