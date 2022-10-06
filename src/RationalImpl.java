import java.lang.Math;
import java.util.Random;
import java.util.Vector;


public class RationalImpl {
    private int ceiling;
    private int floor;
    private Vector<Integer>values;
    private int numberOfFloor;
    private int numberOfCeil;

    public RationalImpl(double k, Vector<Integer>values) {
        this.values=values;
        this.numberOfFloor=(int)(values.size()*(-k+ceiling));
        this.numberOfCeil=(values.size()-numberOfFloor);
    }

    public Vector<Vector<Integer>> run(double k, Vector<Integer>values){
        Vector<Integer>floor_values=new Vector<Integer>();
        Vector<Vector<Integer>>pair_values=new Vector<Vector<Integer>>();
        Random rnd=new Random();

        for(int i=0;i<floor;i++){
            int index=rnd.nextInt(values.size());
            floor_values.add(values.get(index));
            values.remove(index);
        }

        pair_values.add(floor_values);
        pair_values.add(values);

        System.out.println(pair_values);
        return pair_values;
    }

}
