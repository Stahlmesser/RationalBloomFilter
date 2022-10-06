import java.util.Random;
import java.util.Vector;

public class coordinateOfPointRNGImpl {
    private Vector<Integer> centralPoint;
    private int boundary;
    private int length;
    private Random axisRNG;
    private int currentLength;

    public coordinateOfPointRNGImpl(Vector<Integer> centralPoint, int boundary, int length) {
        this.centralPoint = centralPoint;
        this.boundary = boundary;
        this.length=length;
        this.axisRNG = new Random();
    }

    public Random getAxisRNG() {
        return axisRNG;
    }

    public Vector<Integer> generatePoint(){
        int direction=axisRNG.nextInt(0,1);
        if(direction==0){
            currentLength=axisRNG.nextInt(0,length);
        }else{
            currentLength=axisRNG.nextInt(length,boundary);
        }

        int x_lower_boundary=centralPoint.get(0)-currentLength-1;
        int x_higher_boundary=centralPoint.get(0)+currentLength;
        int y_lower_boundary=centralPoint.get(1)-currentLength-1;
        int y_higher_boundary=centralPoint.get(1)+currentLength;
        Vector<Integer> point=new Vector<>();

        for(int i=0;i<1;i++){
            int x=axisRNG.nextInt(x_lower_boundary, x_higher_boundary);
            int y=axisRNG.nextInt(y_lower_boundary,y_higher_boundary);
            point.add(x);
            point.add(y);
        }
        return point;
    }

}
