import java.util.*;

public class BroadcastImpl {
    private Vector<NodeImpl>nodes;
    private int avg;

    public int getAvg() {
        return avg;
    }

    public Vector<NodeImpl> getNodes() {
        return nodes;
    }

    public BroadcastImpl() {
        this.nodes = new Vector<NodeImpl>();
    }

    public void generatePoints(int size, int maximumOfAxis, int seed) {
        Random rng = new Random(seed);
        Map contain = new HashMap();
        while (size > 0) {
            int _x_ = rng.nextInt(maximumOfAxis);
            int _y_ = rng.nextInt(maximumOfAxis);
            if (!contain.containsValue("(" + _x_ + "," + _x_ + ")"))
                contain.put(size--, "(" + _x_ + "," + _x_ + ")");
                nodes.add(new NodeImpl(_x_,_y_));

        }
    }

    public void setNeighbors(){
        for(NodeImpl node:nodes){
            for(int i=0;i<nodes.size();i++){
                if(node.getId()!=nodes.get(i).getId()){
                    int result=(int)(Math.pow(node.get_x_()-nodes.get(i).get_x_(),2)+Math.pow(node.get_y_()-nodes.get(i).get_y_(),2));
                    int distance= (int)Math.sqrt(result);
                    node.getNeighbors().put(nodes.get(i).getId(),distance);
                    avg+=distance;
                }
            }
        }

        for(NodeImpl node:nodes){
            Map<Integer, Integer> map = new HashMap<>();
            node.getNeighbors().keySet().stream().sorted().limit( 3 ).map( key -> node.getNeighbors().get( key ) ).forEach( System.out :: println ) ;



        }

    }

}
