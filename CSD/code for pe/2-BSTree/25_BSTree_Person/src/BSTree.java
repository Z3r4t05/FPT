public class BSTree {
    Node root;
    BSTree() {
     root=null;   
    }
    void clear() {
     root=null;   
    }
    boolean isEmpty() {
     return(root==null);   
    }
    Node search(Node p, int xAge) {
       if(p==null) return(null);
       if(p.info.age==xAge) return(p);
       if(xAge<p.info.age)
           return(search(p.left,xAge));
         else
           return(search(p.right,xAge));
    }
    void insert(Person x) {
      if(isEmpty()) {
        root=new Node(x);
        return;
      }
      Node f,p;
      f=null;p=root;
      while(p!=null) {
         if(p.info.age==x.age) {
             System.out.println("The key " + x.age + " already exists, no insertion");
             return;
         }
         f=p;
         if(x.age<p.info.age)
             p=p.left;
           else
             p=p.right;
      }
     if(x.age<f.info.age) 
        f.left = new Node(x);
        else
         f.right=new Node(x);
    }
    void insertMany(String [] a, int [] b) {
        for(int i=0;i<a.length;i++) insert(new Person(a[i],b[i]));
    }
    void visit(Node p) {
       System.out.print(p.info + "  ");
    }
    void breadth(Node p) {
       if(p==null) return;
       MyQueue q = new MyQueue();
       q.enqueue(p); Node r;
       while(!q.isEmpty()) {
         r=q.dequeue();
         visit(r);
         if(r.left!=null) q.enqueue(r.left);
         if(r.right!=null) q.enqueue(r.right);
       }
    }
    void preOrder(Node p) {
        if(p==null) return;
        visit(p);
        preOrder(p.left);
        preOrder(p.right);
    }
    void inOrder(Node p) {
        if(p==null) return;
        inOrder(p.left);
        visit(p);
        inOrder(p.right);
    }
    void postOrder(Node p) {
        if(p==null) return;
        postOrder(p.left);
        postOrder(p.right);
        visit(p);
    }
}
