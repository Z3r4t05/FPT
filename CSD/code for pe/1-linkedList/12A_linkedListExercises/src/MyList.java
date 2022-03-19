public class MyList
 {Node head, tail;
   MyList() {head=tail=null;}
   boolean isEmpty() {return(head==null);}
   void clear() {head=tail=null;}

   // (1) 
   void addLast(Person x)
     {Node q = new Node(x);
       if(isEmpty()) {head=tail=q;return;}
       tail.next = q;
       tail = q;
     }

    void visit(Node p) {if(p!=null) System.out.print(p.info);}
    void traverse()
     {Node p=head;
       while(p!=null)
         {visit(p);
           p=p.next;
         }
      System.out.println();
     }
   void addMany(String [] a, int [] b)
     {int n,i; n=a.length;
       for(i=0;i<n;i++) addLast(new Person(a[i],b[i]));
     }

   // (2)
   Node searchByName(String xName)
     {Node p=head;
       while(p!=null)
        {if(p.info.name.equals(xName)) return(p);
          p=p.next;
        }
       return(null);
     }

   // (3)
   void addFirst(Person x)
     {
     }

   // (4)
   void insertAfter(Node q, Person x)
     {
     }

   // (5)
   void insertBefore(Node q, Person x)
     {
     }

   // (6)
   void remove(Node q)
     {
     }

   // (7)
   void remove(String xName)
     {
     }

   // (8)
   void remove(int xAge)
     {
     }

   // (9)
   void removeAll(int xAge)
     {
     }

   // (10)
   Node pos(int k)
     {return(null);
     }

   // (11)
   void removePos(int k)
     {
     }

   // (12)
   void sortByName()
     {
     }

   // (13)
   void sortByAge()
     {
     }


   // (14)
   int size()
     {return(0);
     }

   // (15)
   Person [] toArray()
     {return(null);
     }

   // (16)
   void reverse()
     {
     }

  // (17) 
  Node findMaxAge()
     {return(null);
     }

  // (18) 
  Node findMinAge()
     {return(null);
     }

  // (19) 
  void setData(Node p, Person x)
     {
     }

  // (20) 
  void sortByAge(int  k, int h)
     {
     }

  // (21) 
  void reverse(int k, int h) // reverse from k to h 
     {
     }
 }
