/* This program contains 2 parts: (1) and (2)
   YOUR TASK IS TO COMPLETE THE PART  (2)  ONLY
 */
//(1)==============================================================
import java.util.*;
import java.io.*;

public class MyList {

    Node head, tail;

    MyList() {
        head = tail = null;
    }

    boolean isEmpty() {
        return (head == null);
    }

    void clear() {
        head = tail = null;
    }

    void fvisit(Node p, RandomAccessFile f) throws Exception {
        if (p != null) {
            f.writeBytes(p.info + " ");
        }
    }

    void ftraverse(RandomAccessFile f) throws Exception {
        Node p = head;
        while (p != null) {
            fvisit(p, f); // You will use this statement to write information of the node p to the file
            p = p.next;
        }
        f.writeBytes("\r\n");
    }

    void loadData(int k) { //do not edit this function
        String[] a = Lib.readLineToStrArray("data.txt", k);
        int[] b = Lib.readLineToIntArray("data.txt", k + 1);
        int[] c = Lib.readLineToIntArray("data.txt", k + 2);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            addLast(a[i], b[i], c[i]);
        }
    }

//===========================================================================
//(2)===YOU CAN EDIT OR EVEN ADD NEW FUNCTIONS IN THE FOLLOWING PART========
//===========================================================================
/* 
   Khong su dung tieng Viet co dau de viet ghi chu.
   Neu dung khi chay truc tiep se bao loi va nhan 0 diem
     */
    void addLast(String xTower, int xSound, int xType) {
        //You should write here appropriate statements to complete this function.
        if (xTower.charAt(0) == 'B') {

        } else {
            Node p = new Node(new Bell(xTower, xSound, xType));
            //if the list is empty then p will be the first node
            if (isEmpty()) {
                head = p;
                tail = p;
            } else {
                tail.next = p;
                tail = p;
            }
        }
    }

    //You do not need to edit this function. Your task is to complete the addLast function above only.
    void f1() throws Exception {
        clear();
        loadData(1);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        f.close();
    }

    void addLast(Bell b) {
        //You should write here appropriate statements to complete this function.

        Node p = new Node(b);
        //if the list is empty then p will be the first node
        if (isEmpty()) {
            head = p;
            tail = p;
        } else {
            tail.next = p;
            tail = p;
        }

    }

    void addAfter(Node p, Bell x) {
        Node q = new Node(x);
        if (p == null) {
            return;
        }
        if (p == tail) {
            addLast(x);
            return;
        }
        q.next = p.next;
        p.next = q;
    }
//==================================================================

    void f2() throws Exception {
        clear();
        loadData(5);
        String fname = "f2.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        Bell x, y;
        x = new Bell("X", 1, 2);
        y = new Bell("Y", 3, 4);
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/

        //------------------------------------------------------------------------------------
        Node p = head;
        int i = 1;
        while (p != null) {
            if (i == 2) {
                this.addAfter(p, x);
            } else if (i == 4) {
                this.addAfter(p, y);
            }
            i++;
            p = p.next;
        }
        ftraverse(f);
        f.close();
    }

    Bell deleteAfter(Node p) {
        Node tmp = head;
        Bell inf = null;
        if (p == null) {
            return null;
        }
        //if p is the tail return the list
        if (p == tail) {
            return null;
        }
        //if p is the second last node
        if (p.next == tail) {
            tail = p;
            return p.info;
        }
        //loop to node p if the list isn't empty
        while (tmp != null) {
            //if the node p is found, then delete the node next to p.
            if (tmp == p) {
                inf = p.next.info;
                //Move the pointer to the next node of the deleted node
                tmp.next = tmp.next.next;
                break;
            }
            //increment
            tmp = tmp.next;
        }
        return inf;
    }

    Node max() {
        Node p = head;
        if (isEmpty()) {
            return null;
        }
        Node m = null;
        int max = p.info.sound;
        for (p = p.next; p != null; p = p.next) {
            if (p.info.sound > max) {
                max = p.info.sound;
                m = p;
            }
        }
        return m;
    }
//==================================================================

    void f3() throws Exception {
        clear();
        loadData(9);
        String fname = "f3.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/

        //------------------------------------------------------------------------------------
        this.deleteAfter(this.max());
        ftraverse(f);
        f.close();
    }

    void sort() {
        Node p = head;
        int k = 1;
        int c = count();
        while (p != null) {
            if (c - k == 4) {
                Node i, j;
                //Loop from the head to the tail
                for (i = p.next; i != null && i.next != null; i = i.next) {
                    Node max = i;
                    //loop from j = i + 1 to the tail
                    for (j = i.next; j != null; j = j.next) {
                        //if j < min then min = j
                        if (j.info.sound > max.info.sound) {
                            max = j;
                        }
                    }
                    //After the inside loop we swap the new min to the left
                    if (max!= i) {
                        Bell temp = max.info;
                        max.info = i.info;
                        i.info = temp;
                    }
                }
                break;
            }
            k++;
            p = p.next;
        }

    }

    int count() {
        Node p = head;
        int c = 0; //store the count value
        //Loop until the end of the list
        while (p != null) {
            c++;
            p = p.next;
        }
        return c;
    }
//==================================================================

    void f4() throws Exception {
        clear();
        loadData(13);
        String fname = "f4.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/

        //------------------------------------------------------------------------------------
        this.sort();
        ftraverse(f);
        f.close();
    }

}
