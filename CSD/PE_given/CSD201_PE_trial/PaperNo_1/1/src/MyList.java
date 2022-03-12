/* This program contains 2 parts: (1) and (2)
   YOUR TASK IS TO COMPLETE THE PART  (2)  ONLY
 */
//(1)==============================================================
import java.io.*;

class MyList {

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

    void loadData(int k) //do not edit this function
    {
        String[] a = Lib.readLineToStrArray("data.txt", k);
        int[] b = Lib.readLineToIntArray("data.txt", k + 1);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            addLast(a[i], b[i]);
        }
    }

//===========================================================================
//(2)===YOU CAN EDIT OR EVEN ADD NEW FUNCTIONS IN THE FOLLOWING PART========
//===========================================================================
    void addLast(String xOwner, int xPrice) {//You should write here appropriate statements to complete this function.
        if(xOwner.charAt(0) == 'B' || xPrice > 100) {
            
        } else {
            Node q = new Node(new Car(xOwner, xPrice));
            if(this.isEmpty()) {
                head = q;
                tail = q;
            } else {
                tail.next = q;
                tail = q;
            }
        }
    }

    void f1() throws Exception {/* You do not need to edit this function. Your task is to complete the addLast  function
        above only.
         */
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

//==================================================================
    void f2() throws Exception {
        clear();
        loadData(4);
        String fname = "f2.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        Car x = new Car("X", 1);
        Car y = new Car("Y", 2);
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/

        //------------------------------------------------------------------------------------
        Node p = new Node(x);
        if(this.isEmpty()) {
            head = p;
            tail = p;
        } else {
            p.next = head;
            head = p;
        }
          
        ftraverse(f);
        f.close();
    }

//==================================================================
    void f3() throws Exception {
        clear();
        loadData(7);
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
        this.deleteByIndex(this.findIndexFirstPrice(5));
        ftraverse(f);
        f.close();
    }

//==================================================================
    void f4() throws Exception {
        clear();
        loadData(10);
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
        sort();
        //------------------------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }
    public void sort() {
        Node p = head;
        Node i, j;
        //Loop from the head to the tail
        for (i = p; i != null && i.next != null; i = i.next) {
            Node min = i;
            //loop from j = i + 1 to the tail
            for (j = i.next; j != null; j = j.next) {
                //if j < min then min = j
                if (j.info.price < min.info.price) {
                    min = j;
                }
            }
            //After the inside loop we swap the new min to the left
            if (min != i) {
                Car temp = min.info;
                min.info = i.info;
                i.info = temp;
            }
        }
    }
    public int findIndexFirstPrice(int price) {
        Node p = head;
        int a = 1;
        while(p != null) {
            if(p.info.price == price) break;
            a++;
            p = p.next;
        }
        return a;
    }
     public int count() {
        Node p = head;
        int c = 0; //store the count value
        //Loop until the end of the list
        while (p != null) {
            c++;
            p = p.next;
        }
        return c;
    }
      public void deleteByIndex(int i) {
        //Check empty
        if (isEmpty()) {
            return;
        }
        //Temporary node
        Node p = head;
        //If head is the deleted node
        if (i == 1) {
            this.deleteFromHead();
            return;
        } 
        //if tail is the deleted node
        else if (i == count()) {
            this.deleteFromTail();
            return;
        }
        //Find the previous node(Index i - 1)
        int j = 1;
        while(p != null) {
            if(j == i - 1) {
                p.next = p.next.next;
                break;
            }
            p = p.next;
            j += 1;
        }
        //if out of index
        if (p == null || p.next == null) {
        }
       
    }
    public Car deleteFromHead() {
        Node p = head;
        // if the list is already empty return 0
        if (isEmpty() || count() == 1) {
            System.out.println("List is already empty!");
            return null;
        }
        //Move the pointer to the next node
        head = head.next;

        return p.info;
    }
public Car deleteFromTail() {
        //if empty list return 0 by default
        if (head == null) {
            return null;
        }
        //if the list has only 1 node, then set the head to null
        if (head.next == null) {
            Node p = head;
            head = tail = null;
            return p.info;
        }

        Node secondLast = head;
        //loop to the second last node
        while (secondLast.next != tail) {
            secondLast = secondLast.next;
        }
        //Move the pointer to the second last node.
        tail = secondLast;
        return tail.next.info;

    }
}
