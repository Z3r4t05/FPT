
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
public class Matrix {

    public int row;
    public int col;
    public int[][] data;

    /**
     * Ask user to enter row and column of matrix, then get each entry of matrix
     * from user. This function should be use for the first matrix.
     *
     * @param number the number of matrix. Example: matrix 1, matrix 2, ...
     */
    public void inputMatrix(int number) {
        this.row = inputInteger("Enter Row Matrix " + number + ":");
        this.col = inputInteger("Enter Column Matrix " + number + ":");
        this.data = new int[row][col];
        //Loop through each row of matrix
        for (int r = 0; r < row; r++) {
            //Loop through each column of matrix, then ask user to enter an integer for each entry
            for (int c = 0; c < col; c++) {
                this.data[r][c] = inputInteger("Enter Matrix" + number
                        + "[" + (r + 1) + "]"
                        + "[" + (c + 1) + "]:");
            }
        }
    }

    public Matrix() {
    }

    /**
     * Get input integer from user
     *
     * @param string input must be an integer otherwise will throw exception
     * @return an integer that user enter
     */
    private int inputInteger(String string) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print(string);
            int input = Integer.parseInt(sc.nextLine());
            return input;
        } catch (NumberFormatException e) {
            System.out.println("Value of matrix is digit");
            return inputInteger(string);
        }
    }

    /**
     * Display the matrix
     */
    void display() {
        //loop through each row r of matrix
        for (int r = 0; r < row; r++) {
            //Loop through each column c of matrix and display the entry (r,c)
            for (int c = 0; c < col; c++) {
                System.out.print("[" + this.data[r][c] + "]");
            }
            System.out.println("");
        }
    }

    /**
     * Ask user to enter row, column and each entry value of matrix from user.
     * This function should be use for the second matrix.
     *
     * @param number name of matrix
     * @param matrix1 the first matrix used to compare the number of row and
     * column
     * @param option 1 for subtraction, otherwise means multiplication
     */
    public void inputMatrix(int number, Matrix matrix1, int option) {
        //Keep asking user if there is an exception
        do {
            try {
                this.row = inputInteger("Enter Row Matrix " + number + ":");
                this.col = inputInteger("Enter Column Matrix " + number + ":");
                //If option is 1 then it is subtraction checking
                if (option == 1) {
                    //throw exception if the dimension of 2 matrixes are different
                    if ((matrix1.row != this.row) || (matrix1.col != this.col)) {
                        throw new Exception("Rows and columns of 2 matrixes must be equal!");
                    }
                    break;
                } //otherwise, it is multiplication validation
                else {
                    //throw exception when row of matrix 2 not equal col of matrix 1
                    if (matrix1.col != this.row) {
                        throw new Exception("Columns of matrix 1 must be equal"
                                + " with rows of matrix 2");
                    }
                    break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
        this.data = new int[row][col];
        //Loop through each row r of matrix
        for (int r = 0; r < row; r++) {
            //For each entry (r,c) in that row, display it to the screen
            for (int c = 0; c < col; c++) {
                this.data[r][c] = inputInteger("Enter Matrix" + number
                        + "[" + (r + 1) + "]"
                        + "[" + (c + 1) + "]:");
            }
        }
    }
}
