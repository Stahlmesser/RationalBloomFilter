import java.util.Random;

public class NormalDistribution {
    private float u;
    private float v;
    private Random rng;

    public NormalDistribution() {
        this.rng=new Random();
    }

    public int returnDistribution(float u, float v){
        this.u = u;
        this.v = v;
        return (int)(Math.sqrt(v)*rng.nextGaussian()+u);
    }


}
