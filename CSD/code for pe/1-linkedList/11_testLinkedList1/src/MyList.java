public class MyList {
  Node head,tail;
  MyList() {
    head=tail=null;  
  }
  void clear() {
     head=tail=null; 
  }
  boolean isEmpty() {
    return(head==null);  
  }
  Node searchByName(String xName) {
     Node p=head;
     while(p!=null) {
       if(p.info.name.equals(xName)) return(p);
       p=p.next;
       
     }
     return(null);
  }
  void addLast(Person x) {
     Node q=new Node(x);
     if(isEmpty()) {
        head=tail=q;
        return;
     }
     tail.next=q;
     tail=q;
  }
  void addFirst(Person x) {
     head=new Node(x,head);
     if(tail==null) tail=head;
  }
  void visit(Node p) {
    if(p!=null) System.out.print(p.info+" ");
  }
  void traverse() {
     Node p=head;
     while(p!=null) {
      visit(p);
      p=p.next;
  }
  }
  void addMany(String [] a, int [] b) {
      for(int i=0;i<a.length;i++) addLast(new Person(a[i],b[i]));
  }
}
