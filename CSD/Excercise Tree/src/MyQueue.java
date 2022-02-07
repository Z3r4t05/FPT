/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ADMIN
 */

public class MyQueue {
    protected Nodeq head, tail;
    public MyQueue() {
        head = tail = null;
    }
    public boolean isEmpty() {
        return(head==null);
    }
    Object front() throws Exception {
        if(isEmpty()) 
            throw new Exception("Empty");
        return head;
    }
    public Object dequeue() throws Exception {
        if(isEmpty()) throw new Exception("Empty");
        Object x = head.info;
        head = head.next;
        if(head == null) tail = null;
        return(x);
    }
    public void onqueue(Object x) {
        if(isEmpty()) {
            head = tail = new Nodeq(x);
        } else {
            tail.next = new Nodeq(x);
            tail = tail.next;
        }
    }
}
