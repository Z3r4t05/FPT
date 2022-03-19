import java.io.*;
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
  void addLast(Employee x) {
    Node q = new Node(x);
    if(isEmpty()) {
      head=tail=q;
      return;
    }
    tail.next=q;
    tail=q;
  }
  void visit(Node p) {
    if(p!=null) System.out.println(p.info + " ");
  }
  void traverse() {
    Node p=head;
    while(p!=null) {
      visit(p);
      p=p.next;
    }
  }
  void addLastMany(String [] a, int [] b, double [] c) {
     for(int i=0;i<a.length;i++) addLast(new Employee(a[i],b[i],c[i])); 
  }

  void loadFile(String fname) throws IOException { // Using FileReader class
   FileReader fr = new FileReader(fname);
   BufferedReader br = new BufferedReader(fr);
   String s; String [] a;
   String xName; int xAge; double xIncome;
   while(true) {
     s = br.readLine();
     if(s==null || s.trim().length()<3) break;
     a = s.split("[|]");
     xName = a[0].trim();
     xAge = Integer.parseInt(a[1].trim());
     xIncome = Double.parseDouble(a[2].trim());
     addLast(new Employee(xName,xAge,xIncome));
   }
   fr.close();
   br.close();
 }

  void saveFile(String fname) throws IOException { // Using FileReader class
   FileWriter fw = new FileWriter(fname);
   PrintWriter pw = new PrintWriter(fw);
   Node p = head;
   while(p!=null) {
     pw.printf("%10s | %2d | %.1f\r\n",p.info.name,p.info.age,p.info.income);
     p=p.next;
   }
   pw.close();
   fw.close();
  }
  
}
