import java.util.Random;
import java.util.Vector;

public class SenderImpl {
    private DirectionRNGImpl DirectionRNG;
    private int maximumOfX;
    private int seed;
    private int lengthOfFrame;
    private NormalDistribution nd;
    private coordinateOfPointRNGImpl pointRNG;

    public SenderImpl(int maximumOfX, int lengthOfFrame) {
        this.DirectionRNG = new DirectionRNGImpl(maximumOfX);
        this.maximumOfX = maximumOfX;
        this.lengthOfFrame=lengthOfFrame;
        this.nd=new NormalDistribution();
    }

    public void setPointRNG(coordinateOfPointRNGImpl pointRNG) {
        this.pointRNG = pointRNG;
    }

    public Vector<Integer> send(int seed){
        DirectionRNG.getFrameRNG().setSeed(seed);
        Vector<Integer> sendingCollection=DirectionRNG.generateFrame(lengthOfFrame);
        System.out.println(sendingCollection);
        return sendingCollection;
    }

    public Vector<Vector<Integer>> send2D(int seed){
        pointRNG.getAxisRNG().setSeed(seed);
        Vector<Vector<Integer>> frame=new Vector<Vector<Integer>>();
        for(int i=0;i<lengthOfFrame;i++){
            Vector<Integer>point=pointRNG.generatePoint();
            frame.add(point);
        }
        return frame;
    }
}
