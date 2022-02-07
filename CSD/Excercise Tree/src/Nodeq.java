public class Nodeq {
    public Object info;
    public Nodeq next;
    public Nodeq(Object x, Nodeq p) {
        info = x;
        next = p;
    }
    public Nodeq(Object x) {
        this(x,null);
    }
}