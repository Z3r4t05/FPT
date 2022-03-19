public class Main {

    public static void main(String[] args) {
      String [] a = {"Hoa","La","Canh","Cay"};
      int [] b = {22,19,18,21};
      MyList t = new MyList();
      t.addMany(a,b);
      t.traverse();
      System.out.println();
    }
    
}
