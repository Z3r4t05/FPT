public class Main {

    public static void main(String[] args)throws Exception {
      String [] a = {"Hoa","La","Canh","Cay","Goc","Re"};
      int [] b = {22,19,21,23,20,18};
      double [] c = {500,700,600,550,800,650};
      MyList t = new MyList();
      System.out.println();

      String fname = "emp.txt";
      String fnameRep = "emp_rep.txt";
      System.out.println();
      t.clear();
      t.loadFile(fname);
      t.traverse();
      System.out.println();
      t.saveFile(fnameRep);
      System.out.println();
    }
    
}
