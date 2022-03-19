public class Node {
  Employee info;
  Node next;
  Node(Employee x, Node p) {
    info=x; next=p;  
  }
  Node(Employee x) {
    info=x; next=null; //this(x,null);  
  }
}
