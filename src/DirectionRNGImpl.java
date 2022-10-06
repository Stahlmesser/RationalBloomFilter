import java.util.Random;
import java.util.Vector;

public class DirectionRNGImpl{
    private int direction;
    private Random directionRNG;
    private Random frameRNG;
    private int previousSum;
    private int maximumOfX;

    public DirectionRNGImpl(int maximumOfX) {
        this.directionRNG = new Random();
        this.frameRNG = new Random();
        this.maximumOfX=maximumOfX;
    }

    //referenced from https://stackoverflow.com/questions/22380890/generate-n-random-numbers-whose-sum-is-m-and-all-numbers-should-be-greater-than
    public static Vector<Integer> n_random(int target, int numbers) {
        Random r = new Random();
        Vector<Integer> load = new Vector<>();

        int sum = 0;
        for (int i = 0; i < numbers; i++) {
            int next = r.nextInt(target) + 1;
            load.add(next);
            sum += next;
        }

        double scale = 1d * target / sum;
        sum = 0;
        for (int i = 0; i < numbers; i++) {
            load.set(i, (int) (load.get(i) * scale));
            sum += load.get(i);
        }

        //take rounding issues into account
        while(sum++ < target) {
            int i = r.nextInt(target);
            load.set(i, load.get(i) + 1);
        }
        return load;
    }

    public Random getFrameRNG() {
        return frameRNG;
    }


    public Vector<Integer> generateFrame(int lengthOfFrame){
        Vector<Integer> result=new Vector<Integer>();
        if(previousSum==0){
            for(int i=0;i<lengthOfFrame;i++){
                result.add(frameRNG.nextInt(0,maximumOfX));
            }
        }else{
            int direction=directionRNG.nextInt(0,1);
            int sum=0;
            if(direction==1){
                System.out.println("This time the whole moves to the left");
                int rangeOfSum=previousSum-frameRNG.nextInt(1,previousSum/2);
                result.addAll(n_random(previousSum,lengthOfFrame));
            }else{
                System.out.println("This time the whole moves to the right");
                int rangeOfSum=previousSum-frameRNG.nextInt(1,previousSum/2);
                result.addAll(n_random(previousSum,lengthOfFrame));
            }
        }

        previousSum=0;
        for(int value:result){
            previousSum+=value;
        }

        return result;
    }
}
