/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ADMIN
 */
public class Graph {

    int n; // so luong dinh
    int[][] a; //matrix lien ke, quan he cua dinh
    char[] v; //ten dinh

    Graph() {
        v = "ABCDEFGHIJKLMN".toCharArray();
    }

    public static void main(String[] args) {
        int[][] b = {
            {0, 1, 1, 0, 0, 0}, //dinh 0(A) noi voi dinh 1,2
            {1, 0, 0, 1, 0, 0}, //dinh 1(b) noi voi 0,3
            {1, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1}, //dinh 4 noi voi dinh 5
            {0, 0, 0, 0, 1, 0}  //dinh 5 noi voi dinh 4
        };
        Graph g = new Graph();
        g.setData(b);
        g.displayGraph();
    }

    private void setData(int[][] b) {
        n = b.length;
        a = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = b[i][j];
            }
        }
    }
    
    public void displayGraph() {
        for(int i = 0; i < n; i++) {
            System.out.print(v[i] + ": ");
            for (int j = 0; j < n; j++) {
                if(a[i][j] != 0) {
                    System.out.print(v[j] + " ");
                }
                
            }
            System.out.println("");
        }
    }
}
