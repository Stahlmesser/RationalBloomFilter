import java.util.HashMap;

public class NodeImpl {
    private BloomFilterImpl NeighborFilter;
    private BloomFilterImpl UrgentMemberFilter;
    private int _x_;
    private int _y_;
    private static int counter = 0;
    private final int Id;
    private HashMap<Integer,Integer> neighbors;
    private boolean Received=false;

    public HashMap<Integer, Integer> getNeighbors() {
        return neighbors;
    }

    public BloomFilterImpl getNeighborFilter() {
        return NeighborFilter;
    }

    public int getId() {
        return Id;
    }

    public int get_x_() {
        return _x_;
    }

    public int get_y_() {
        return _y_;
    }

    public NodeImpl(int _x_, int _y_) {
       this._x_=_x_;
       this._y_=_y_;
       this.Id=counter++;
       this.neighbors=new HashMap<>();
    }

    public void print(){
        System.out.println(_x_+","+_y_);
    }

}
