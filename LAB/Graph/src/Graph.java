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
    char[] vertex = "ABCDEFGHIJKLMN".toCharArray();

    Graph() {

    }

    public void visit(int i) {
        System.out.print(vertex[i] + " ");
    }

    public static void main(String[] args) {
        int[][] b = { // a b c d e f g h i
                    {0,1,1,1,0,0,0,0,0},
                    {1,0,1,1,0,0,0,0,0},
                    {1,1,0,1,0,0,0,0,0},
                    {1,1,1,0,0,1,0,0,0},
                    {0,0,0,0,0,1,0,0,0},
                    {0,0,0,1,1,0,0,0,0},
                    {0,0,0,0,0,0,0,1,1},
                    {0,0,0,0,0,0,1,0,0},
                    {0,0,0,0,0,0,1,0,0}
        //            {0,1,1,1,0,0,1},
        //            {1,0,1,0,1,0,0},
        //            {1,1,0,0,1,0,0},
        //            {1,0,0,0,0,1,0},
        //            {0,1,1,0,0,1,1},
        //            {0,0,0,1,1,0,0},
        //            {1,0,0,0,1,0,0}
        };
        Graph g = new Graph();
        g.setData(b);
        g.displayGraph();
        System.out.println("");
        g.DFT(0, b);
        System.out.println("");
        g.BFT(0, b);
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
        for (int i = 0; i < n; i++) {
            System.out.print(vertex[i] + ": ");
            for (int j = 0; j < n; j++) {
                if (a[i][j] != 0) {
                    System.out.print(vertex[j] + " ");
                }
            }
            System.out.println("");
        }
    }

    //Breath first traversal
    public void BFT(int u) {
        boolean[] c = new boolean[n];
        BFT(u, c);
        for (int i = 0; i < n; i++) {
            if (!c[i]) {
                BFT(i, c);
            }
        }
    }

    //Breadth first traversal
    public void BFT(int u, boolean[] c) {
        MyQueue mq = new MyQueue();
        mq.enqueue(u);
        c[u] = true;
        while (!mq.isEmpty()) {
            int j = (int) mq.dequeue();
            visit(j);
            for (int i = 0; i < n; i++) {
                if (!c[i] && a[j][i] > 0) {
                    mq.enqueue(i);
                    c[i] = true;
                }
            }
        }
    }

    //Depth first traversal
    public void DFT(int i, boolean[] c) {
        c[i] = true;
        visit(i);
        for (int j = 0; j < n; j++) {
            if (!c[j] && a[i][j] > 0) {
                DFT(j, c);
            }
        }
    }

    public void DFT(int i) {
        boolean[] c = new boolean[n];
        DFT(i, c);
        for (int j = 0; j < n; j++) {
            if (!c[j]) {
                DFT(j, c);
            }
        }
    }

}
