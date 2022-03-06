
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ADMIN
 */
class Calculator {
    private int a;
    /**
     * Display the menu
     */
    public void displayMenu() {
        ArrayList<String> listOptions = new ArrayList<>(Arrays.asList(
                "Addition Matrix",
                "Subtraction Matrix",
                "Multiplication Matrix",
                "Quit"));
        System.out.println("");
        System.out.println("=======Calculator program======");
        int totalOption = listOptions.size();
        //print all options start with index from 1 to the end
        for (int i = 0; i < totalOption; i++) {
            System.out.println((i + 1) + ". " + listOptions.get(i));
        }
    }

    /**
     * Prompt user to select an option in range min-max
     *
     * @param message question to user
     * @param min min value of option
     * @param max max value of option
     * @return the value of user choice as an integer
     */
    public int selectOption(String message, int min, int max) {
        int input;
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println(message);
            String s = sc.nextLine();
            //throw exception if the input is empty
            if (s.isEmpty()) {
                throw new Exception("Empty input");
            }
            input = Integer.parseInt(s);
            //throw exception if the input is not in range from min to max
            if (input < min || input > max) {
                throw new Exception("Not in range [" + min + "-" + max + "]");
            }
            return input;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return selectOption(message, min, max);
        }
    }

    /**
     * Ask user to enter 2 matrixes. Then add it and display the result.
     */
    public void addMatrix() {
        System.out.println("-------- Addition --------");
        Matrix matrix1 = new Matrix();
        Matrix matrix2 = new Matrix();
        matrix1.inputMatrix(1);
        matrix2.inputMatrix(2, matrix1, 1);
        System.out.println("-------- Result --------");
        matrix1.display();
        System.out.println("+");
        matrix2.display();
        System.out.println("=");
        //For each row of matrix 1, loop through all entry in that row
        for (int r = 0; r < matrix1.row; r++) {
            //Add each entry in the row with the correspondence entry in matrix 2
            for (int c = 0; c < matrix1.col; c++) {
                System.out.print("[" + (matrix1.data[r][c] + matrix2.data[r][c]) + "]");
            }
            System.out.println("");
        }

    }

    /**
     * Ask user to enter 2 matrixes. Then subtract matrix 2 from matrix 1 and
     * display the result
     */
    public void subtractMatrix() {
        System.out.println("-------- Subtraction --------");
        Matrix matrix1 = new Matrix();
        Matrix matrix2 = new Matrix();
        matrix1.inputMatrix(1);
        matrix2.inputMatrix(2, matrix1, 1);

        System.out.println("-------- Result --------");
        matrix1.display();
        System.out.println("-");
        matrix2.display();
        System.out.println("=");
        //For each row of matrix 1, loop through all entry in that row
        for (int r = 0; r < matrix1.row; r++) {
            //Subtract the each entry of matrix 2 with the correspondance entry from matrix 1
            for (int c = 0; c < matrix1.col; c++) {
                System.out.print("[" + (matrix1.data[r][c] - matrix2.data[r][c]) + "]");
            }
            System.out.println("");
        }

    }

    void multiplyMatrix() {
        System.out.println("-------- Multiplication --------");
        Matrix matrix1 = new Matrix();
        Matrix matrix2 = new Matrix();
        matrix1.inputMatrix(1);
        matrix2.inputMatrix(2, matrix1, 2);
        System.out.println("-------- Result --------");
        matrix1.display();
        System.out.println("*");
        matrix2.display();
        System.out.println("=");
        Matrix result = new Matrix();
        result.row = matrix1.row;
        result.col = matrix2.col;
        result.data = new int[result.row][result.col];
        //Loop through each row i of matrix 1
        for (int i = 0; i < matrix1.row; i++) {
            //Loop through each column j of matrix 2
            for (int j = 0; j < matrix2.col; j++) {
                //The entry (i,j) of the result matrix is the dot product of row i and col j
                for (int k = 0; k < matrix1.col; k++) {
                    result.data[i][j] += matrix1.data[i][k] * matrix2.data[k][j];
                }
            }
        }
        result.display();
    }

}
