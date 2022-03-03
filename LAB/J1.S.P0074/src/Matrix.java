
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

    public void inputMatrix(int number) {
        this.row = inputInteger("Enter Row Matrix " + number + ":");
        this.col = inputInteger("Enter Column Matrix " + number + ":");
        this.data = new int[row][col];
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                this.data[r][c] = inputInteger("Enter Matrix" + number
                        + "[" + (r + 1) + "]"
                        + "[" + (c + 1) + "]:");
            }
        }
    }

    public Matrix() {
    }

    private int inputInteger(String string) {
        int input = 0;
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print(string);
            input = Integer.parseInt(sc.nextLine());
            return input;
        } catch (NumberFormatException e) {
            System.out.println("Value of matrix is digit");
            return inputInteger(string);
        }
    }

    void display() {
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                System.out.print("[" + this.data[r][c] + "]");
            }
            System.out.println("");
        }
    }

    public void inputMatrix(int number, Matrix matrix1, int option) {
        do {
            try {
                this.row = inputInteger("Enter Row Matrix " + number + ":");
                this.col = inputInteger("Enter Column Matrix " + number + ":");
                if (option == 1) {
                    if ((matrix1.row != this.row) || (matrix1.col != this.col)) {
                        throw new Exception("Rows and columns of 2 matrixes must be equal!");
                    }
                    break;
                } else {
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
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                this.data[r][c] = inputInteger("Enter Matrix" + number
                        + "[" + (r + 1) + "]"
                        + "[" + (c + 1) + "]:");
            }
        }
    }
}
