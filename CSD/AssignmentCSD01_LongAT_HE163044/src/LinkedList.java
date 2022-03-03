
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ADMIN
 */
public class LinkedList {
    
    Node head;
    Node tail;
    
    public LinkedList() {
        head = null;
        tail = null;
    }
    
    public boolean isEmpty() {
        return head == null;
    }
    
    public void addToHead(Song newSong) {
        Node node = new Node(newSong);
        if (isEmpty()) {
            head = node;
            tail = node;
        } else {
            node.next = head;
            head = node;
        }
    }
    
    public void addToTail(Song newSong) {
        Node node = new Node(newSong);
        if (isEmpty()) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
    }
    
    public void traverse() {
        if (isEmpty()) {
            return;
        }
        Node p = head;
        while (p != null) {
            if (p == tail) {
                System.out.println(p.info + "\n");
                break;
            }
            System.out.println(p.info);
            p = p.next;
        }
    }
    
    public int count() {
        Node p = head;
        int c = 0;
        while (p != null) {
            c++;
            p = p.next;
        }
        return c;
    }
    
    public boolean loadFromFile(String filename) {
        File f = new File(filename);
        if (!f.exists()) {
            return false;
        } else {
            try (FileReader fr = new FileReader(f); BufferedReader bf = new BufferedReader(fr)) {
                String line;
                while ((line = bf.readLine()) != null) {
                    line = line.trim();
                    if (line.length() > 0) {
                        StringTokenizer stk = new StringTokenizer(line, "|");
                        String name = stk.nextToken().trim();
                        String artist = stk.nextToken().trim();
                        int rated = Integer.parseInt(stk.nextToken().trim());
                        Song s = new Song(name, artist, rated);
                        if ((name.charAt(name.length() - 1) == 'y') || rated <= 1) {
                            continue;
                        } else {
                            this.addToTail(s);
                        }
                        
                    }
                }
            } catch (IOException | NumberFormatException e) {
                System.err.println(e);
                return false;
            }
        }
        return true;
    }
    
    public boolean writeToFile(String filename) {
        try (FileWriter fw = new FileWriter(filename); PrintWriter pw = new PrintWriter(fw)) {
            Node p = this.head;
            while (p != null) {
                pw.println(p.info);
                p = p.next;
            }
            pw.close();
            fw.close();
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
        return true;
    }
    
    public void avgRated() {
        Node p = head;
        if (isEmpty()) {
            return;
        }
        int sum = 0;
        while (p != null) {
            sum += p.info.rated;
            p = p.next;
        }
        float avg = sum / count();
        System.out.println("\nAverage rated: " + avg);
    }
    
    public void deleteThird() {
        Node p = head;
        int max3 = max3();
        if (isEmpty()) {
            return;
        }
        if (count() <= 2) {
            return;
        }
        for (int i = 0; i < count(); i++) {
            if (get(i).info.rated == max3) {
                this.deleteByIndex(i + 1);
            }
        }
    }
    
    public Node get(int k) {
        Node p = head;
        int c = 0;
        while (p != null && c < k) {
            c++;
            p = p.next;
        }
        return p;
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
        } //if tail is the deleted node
        else if (i == count()) {
            this.deleteFromTail();
            return;
        }
        //Find the previous node(Index i - 1)
        int j = 1;
        while (p != null) {
            if (j == i - 1) {
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
    
    public void dele(Node p) {
        Node tmp = head;
        if (p == tail) {
            this.deleteFromTail();
            return;
        } else if (p == head) {
            this.deleteFromHead();
            return;
        }
        //Loop to the tail
        while (tmp != null) {
            //If the next node is p then we move the pointer
            if (tmp.next == p) {
                tmp.next = tmp.next.next;
            }
            //increment
            tmp = tmp.next;
        }
    }
    
    public void deleteFromHead() {
        // if the list is already empty return 0
        if (isEmpty() || count() == 1) {
            return;
        }
        //Move the pointer to the next node
        head = head.next;
        
        return;
    }
    
    public void deleteFromTail() {
        if (head == null) {
            return;
        }
        if (head.next == null) {
            Node p = head;
            head = tail = null;
            return;
        }
        Node secondLast = head;
        //loop to the second last node
        while (secondLast.next != tail) {
            secondLast = secondLast.next;
        }
        //Move the pointer to the second last node.
        tail = secondLast;
    }
    
    public int max3() {
        Node p = head;
        if (isEmpty()) {
            return 0;
        }
        int max1 = p.info.rated;
        int max2 = p.info.rated;
        int max3 = p.info.rated;
        for (p = p.next; p != null; p = p.next) {
            if (p.info.rated > max1) {
                max1 = p.info.rated;
            }
        }
        p = head;
        for (p = p.next; p != null; p = p.next) {
            if (p.info.rated < max1 && p.info.rated > max2) {
                max2 = p.info.rated;
            }
        }
        p = head;
        for (p = p.next; p != null; p = p.next) {
            if (p.info.rated < max2 && p.info.rated > max3) {
                max3 = p.info.rated;
            }
        }
        return max3;
    }
    
    public static void main(String[] args) {
        LinkedList l = new LinkedList();
        l.loadFromFile("src\\songs.txt");
        l.traverse();
        l.avgRated();
        System.out.println("Third highest rated: " + l.max3());
        System.out.println(l.nth_max(3));
//        l.deleteThird();
//        l.traverse();
//        l.writeToFile("src\\output.txt");
//        System.out.println("remove duplicate");
//        l.removeDup();
//        l.traverse();
    }
    
    public void removeDup() {
        Node p1, p2;
        p1 = head;
        while (p1 != null) {
            p2 = p1.next;
            while (p2 != null) {
                if (p1.info.name.equals(p2.info.name)) {
                    if (p1.info.rated <= p2.info.rated) {
                        this.dele(p1);
                    } else {
                        this.dele(p2);
                    } 
                }
                p2 = p2.next;
            }
            p1 = p1.next;
        }
    }
    
    int max(int upper) {
        Node p = head;
        int max = Integer.MIN_VALUE;
        while(p!=null) {
            if(p.info.rated > max && p.info.rated < upper) {
                max = p.info.rated;
            }
            p = p.next;
        }
        return max;
    }
    //max(max) 2
    //max(max(max) 3
    int nth_max(int nth) {
        if(nth == 1) return max(Integer.MAX_VALUE);
        else if (nth < 1) {
            return 0;
        } else {
            int nmax = max(Integer.MAX_VALUE);
            for (int i = 0; i < nth - 1; i++) {
                nmax = max(nmax);        
            }
            return nmax;
        }
    }
    
}
