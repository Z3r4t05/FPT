/* This program contains 2 parts: (1) and (2)
   YOUR TASK IS TO COMPLETE THE PART  (2)  ONLY
 */
//(1)============================================
import java.io.*;
import java.util.*;
//-------------------------------------------------------------------------------

class Graph {

    int[][] a;
    int n;
    char v[];
    int deg[];

    Graph() {
        v = "ABCDEFGHIJKLMNOP".toCharArray();
        deg = new int[20];
        a = new int[20][20];
        n = 0;
    }

    void loadData(int k) //do not edit this function
    {
        RandomAccessFile f;
        int i, j, x;
        String s;
        StringTokenizer t;
        a = new int[20][20];
        try {
            f = new RandomAccessFile("data.txt", "r");
            for (i = 0; i < k; i++) {
                f.readLine();
            }
            s = f.readLine();
            s = s.trim();
            n = Integer.parseInt(s);
            for (i = 0; i < n; i++) {
                s = f.readLine();
                s = s.trim();
                t = new StringTokenizer(s);
                for (j = 0; j < n; j++) {
                    x = Integer.parseInt(t.nextToken().trim());
                    a[i][j] = x;
                }
            }
            f.close();
        } catch (Exception e) {
        }

    }

    void dispAdj() {
        int i, j;
        for (i = 0; i < n; i++) {
            System.out.println();
            for (j = 0; j < n; j++) {
                System.out.printf("%4d", a[i][j]);
            }
        }
    }

    void fvisit(int i, RandomAccessFile f) throws Exception {
        f.writeBytes(" " + v[i]);
    }

    void breadth(boolean[] en, int i, RandomAccessFile f) throws Exception {
        Queue q = new Queue();
        int r, j;
        q.enqueue(i);
        en[i] = true;
        while (!q.isEmpty()) {
            r = q.dequeue();
            fvisit(r, f);
            for (j = 0; j < n; j++) {
                if (!en[j] && a[r][j] > 0) {
                    q.enqueue(j);
                    en[j] = true;
                }
            }
        }
    }

    void breadth(int k, RandomAccessFile f) throws Exception {
        boolean[] en = new boolean[20];
        int i;
        for (i = 0; i < n; i++) {
            en[i] = false;
        }
        breadth(en, k, f);
        for (i = 0; i < n; i++) {
            if (!en[i]) {
                breadth(en, i, f);
            }
        }
    }

    void depth(boolean[] visited, int k, RandomAccessFile f) throws Exception {
        fvisit(k, f);
        visited[k] = true;
        for (int i = 0; i < n; i++) {
            if (!visited[i] && a[k][i] > 0) {
                depth(visited, i, f);
            }
        }
    }

    void depth(int k, RandomAccessFile f) throws Exception {
        boolean[] visited = new boolean[20];
        int i;
        for (i = 0; i < n; i++) {
            visited[i] = false;
        }
        depth(visited, k, f);
        for (i = 0; i < n; i++) {
            if (!visited[i]) {
                depth(visited, i, f);
            }
        }
    }

    void depth1(int k, RandomAccessFile f) throws Exception {
        boolean[] visited = new boolean[20];
        int i;
        for (i = 0; i < n; i++) {
            visited[i] = false;
        }
        depth1(visited, k, f);
        for (i = 0; i < n; i++) {
            if (!visited[i]) {
                depth1(visited, i, f);
            }
        }
    }

    void depth1(boolean[] visited, int k, RandomAccessFile f) throws Exception {
        fvisit1(k, f);
        visited[k] = true;
        for (int i = 0; i < n; i++) {
            if (!visited[i] && a[k][i] > 0) {
                depth1(visited, i, f);
            }
        }
    }

    void fvisit1(int i, RandomAccessFile f) throws Exception {
        f.writeBytes(" " + v[i] + "(" + deg(i) + ")");
    }

    int deg(int i) {
        int s, j;
        s = 0;
        for (j = 0; j < n; j++) {
            s += a[i][j];
        }
        s += a[i][i];
        return (s);
    }

    void breadth1(int k, RandomAccessFile f) throws Exception {
        Queue q = new Queue();
        int i, h;
        boolean[] enqueued = new boolean[n]; //Đánh dấu đỉnh i đã được đưa vào queue chưa
        for (i = 0; i < n; i++) {
            enqueued[i] = false; //khởi tạo các đỉnh chưa đưa vào queue
        }
        q.enqueue(new Integer(k));
        enqueued[k] = true; //duyệt từ đỉnh k với trạng thái đánh dấu
        while (!q.isEmpty()) {
            h = Integer.parseInt((q.dequeue()).toString().trim());
            fvisit(h, f);
            for (i = 0; i < n; i++) {
                if ((!enqueued[i]) && a[h][i] > 0) {
                    q.enqueue(new Integer(i));
                    enqueued[i] = true;
                }
            }
        }
        System.out.println();
        for (i = 0; i < n; i++) {
            if (enqueued[i] == false) {
                System.out.print(v[i] + " ");
            }
        }
        System.out.println("");
    }
//===========================================================================
//(2)===YOU CAN EDIT OR EVEN ADD NEW FUNCTIONS IN THE FOLLOWING PART========
//===========================================================================
/**
 * 	void f1() - Perfom depth-first traversal (to the file f1.xt) from the vertex  i=1 (the vertex B) but display vertices with their deegrees in bracket. Hint: copy depth(...) to depth2(...) and modify the latter one. Content of  the output file f1.txt must be:
B G A E F I C H D
B(1) G(2) A(4) E(3) F(3) I(3) C(1) H(2) D(1)

 * @throws Exception 
 */
    void f1() throws Exception {
        loadData(1);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        depth(1, f);
        f.writeBytes("\r\n");
        //-------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/

        //-------------------------------------------------------------------------------------
        this.depth1(1, f);
        f.writeBytes("\r\n");
        f.close();
    }
//===========================================================================
/**
 * 	void f2() – Apply the Dijkstra’s shortest path algorithm to find the shortest path from the vertex 0 (A) to the vertex 4 (E). (Note that in the weighted matrix, the value 999 is considered as infinity). Write 2 lines into the file f6.txt. The first line contains the list of vertices in the shortest path. The second lines contains shortest distances to the vertices in the first line. Content of  the output file f2.txt must be:
A   C   F   E
0   9   11   20


 * @throws Exception 
 */
    void f2() throws Exception {
        loadData(12);
        String fname = "f2.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        //-------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/

        //-------------------------------------------------------------------------------------
        this.dijkstra(0, 4, f);
        f.writeBytes("\r\n");
        f.close();
    }

    void dijkstra(int startVertex, int endVertex, RandomAccessFile f) throws IOException {
        boolean[] checked = new boolean[n]; //danh dau dinh da duoc xac dinh hay chua
        int[] d = new int[n]; //khoang cach tu dinh startV den dinh i
        int[] previous = new int[n]; //luu dinh truoc dinh i
        int min_d;
        for (int i = 0; i < n; i++) {
            checked[i] = false;
        }

        for (int i = 0; i < n; i++) {
            d[i] = 999;
        }

        d[startVertex] = 0;

        for (int i = 0; i < n; i++) {
            previous[i] = -1;
        }

        int u = startVertex;
        while (u != -1) {
            checked[u] = true;
            for (int i = 0; i < n; i++) {
                if (!checked[i] && (d[i] > d[u] + a[u][i])) {
                    d[i] = d[u] + a[u][i];
                    previous[i] = u;
                }
            }
            min_d = 999;
            u = -1;
            for (int i = 0; i < n; i++) {
                if (!checked[i] && (d[i] < min_d)) {
                    min_d = d[i];
                    u = i;
                }
            }
            if (u == endVertex) {
                u = -1;
            }
        }

        displayPath(startVertex, endVertex, d[endVertex], previous, d, f);
//        for (int i = startVertex; i < endVertex; i++) {
//            System.out.print(" " + d[i]);
//        }
//        for (int i = 0; i < previous.length; i++) {
//            System.out.println(previous[i]);
//            
//        }
    }

    void displayPath(int startVertex, int endVertex, int d, int[] p, int[] dis,
            RandomAccessFile f) throws IOException {
        if (d == 999) {
            System.out.println("No path from " + v[startVertex] + " to " + v[endVertex] + ". ");
            return;
        }
//        System.out.println("The shortest dustance from " + v[startVertex]
//                + " to " + v[endVertex] + " is " + d);
        Stack s = new Stack();
        int u = endVertex;
        while (u != -1) {
            s.push(u);
            u = p[u];
        }
        u = (int) s.pop();
        ArrayList<Integer> distance = new ArrayList<>();
        distance.add(u);
        f.writeBytes(Character.toString(v[u]));
        while (!s.isEmpty()) {
            u = (int) s.pop();
            distance.add(u);
            f.writeBytes(" " + v[u]);
        }
        f.writeBytes("\n");
        for (Integer i : distance) {
            f.writeBytes(dis[i] + " ");
        }
        f.writeBytes("\n");
    }
//===========================================================================
/*
Algorithm for finding an Euler cycle from the vertex X using stack 
//Input: Connected graph G with all vertices having even degrees
//Output: Euler cycle
declare a stack S of characters
declare empty array E (which will contain Euler cycle)
push the vertex X to S
while(S is not empty)
 {r = top element of the stack S 
  if r is isolated then remove it from the stack and put it to E
   else
   select the first vertex Y (by alphabet order), which is adjacent
   to r, push  Y  to  S and remove the edge (r,Y) from the graph   
 }
 the last array E obtained is an Euler cycle of the graph
     */
    void f3() throws Exception {
        loadData(20);
        String fname = "f3.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        f.writeBytes("\r\n");
        //-------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
        // You can use the statement fvisit(i,f); i = 0, 1, 2,...,n-1 to display the vertex i to file f5.txt 
        //  and statement f.writeBytes(" " + k); to write  variable k to the file f5.txt  

        //-------------------------------------------------------------------------------------
        this.euler(1, f);
        f.writeBytes("\r\n");
        f.close();
    }
/*
Algorithm for finding an Euler cycle from the vertex X using stack 
//Input: Connected graph G with all vertices having even degrees
//Output: Euler cycle
declare a stack S of characters
declare empty array E (which will contain Euler cycle)
push the vertex X to S
while(S is not empty)
 {r = top element of the stack S 
  if r is isolated then remove it from the stack and put it to E
   else
   select the first vertex Y (by alphabet order), which is adjacent
   to r, push  Y  to  S and remove the edge (r,Y) from the graph   
 }
 the last array E obtained is an Euler cycle of the graph
     */
    public void euler(int x, RandomAccessFile f) throws IOException {
        Stack ms = new Stack();
        ms.push(x);
        int[] e = new int[20];
        int ne = 0;
        while (!ms.isEmpty()) {
            int r = (int) ms.top();
            //neu r la dinh co lap -> ket nap r vao e, xoa r khoi ngan xep
            //nguoc lai, tim i la dinh dau tien co canh noi voi r
            //xoa canh noi r va i, ket nap i vao ngan xep
            int i = 0;
            while (i < n && a[r][i] == 0) {
                i++;
            }
            if (i == n) {//r la dinh co lap
                e[ne] = r;
                ms.pop();
                ne++;
            } else {
                a[r][i]--;
                a[i][r]--;
                ms.push(i);
            }
        }
        
        for (int i = 0; i < ne; i++) {
            f.writeBytes(v[e[i]] + " ");
        }
        f.writeBytes("\n");
    }
    

}
//=================================================================
