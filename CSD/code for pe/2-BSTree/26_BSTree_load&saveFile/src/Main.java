import java.io.*;
public class Main {

    public static void main(String[] args) throws IOException {
      String [] a = {"Hoa","La","Canh","Cay","Goc","Re","Man","Dao","Cam"};  
      int [] b = {8,3,10,1,6,14,4,7,13};
      double [] c = {70,50,90,10,70,60,45,80,45};
      BSTree t = new BSTree();
      t.insertMany(a,b,c);
      System.out.println("1. Test breadth-first traversal:");
      t.breadth(t.root);
      System.out.println();
      t.clear();
      String fname = "emp.txt";
      String fname_rep = "emp_rep.txt";
      t.loadFile(fname);
      t.saveFileBreadth(fname_rep,t.root);
      System.out.println();

    }
    
}
