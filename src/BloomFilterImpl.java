import java.util.Collections;
import java.util.Vector;
import java.lang.Math;
import java.util.Random;
import java.util.stream.IntStream;

public class BloomFilterImpl {
    private double _k_;
    private int _m_;
    private double maximumFalsePositiveRate;
    private RationalImpl rational;
    private Vector<HashFunctionImpl> hashFunctions;
    private int ceiling;
    private int floor;
    private Random rng;
    private Vector<Integer> hashValues;

    public BloomFilterImpl(double power,int m)
    {
        this._k_=power;
        this._m_=m;
        this.maximumFalsePositiveRate=Math.pow(2,-power);
        this.floor=(int)Math.floor(_k_);
        this.ceiling=(int)Math.ceil(_k_);
        this.rng=new Random();
        this.hashFunctions=new Vector<HashFunctionImpl>();
        this.hashValues=new Vector<Integer>();
    }

    public void initializeBloomFilter(Vector<Integer> values,long seed)
    {
        rng.setSeed(seed);
        this.rational=new RationalImpl(_k_,values);
        int prime=findNearestGreaterPrimeNumber(_m_);
        for(int i=0;i<ceiling;i++)
        {
            HashFunctionImpl hfi=new HashFunctionImpl(prime,_m_,rng);
            hashFunctions.add(hfi);
        }
    }

    public int getCeiling() {
        return ceiling;
    }

    public int getFloor() {
        return floor;
    }

    public Vector<Integer> rationalRun(Vector<Integer> inP, Vector<Integer> notInP, long seed)
    {
        System.out.println("And the current seed is:"+seed);
        for(int value:inP) {
            for (int i = 0; i < floor; i++) {
                HashFunctionImpl hashFunction=hashFunctions.get(i);
                int hashValue=hashFunction.twoUniversalHashing(value);
                if(!hashValues.contains(hashValue)){
                    hashValues.add(hashValue);
                }
            }
        }

        for(int value:notInP) {
            for (int i = 0; i < ceiling; i++) {
                HashFunctionImpl hashFunction=hashFunctions.get(i);
                int hashValue=hashFunction.twoUniversalHashing(value);
                if(!hashValues.contains(hashValue)){
                    hashValues.add(hashValue);
                }
            }
        }
        Collections.sort(hashValues);

        System.out.println("The bitmap follows:");
        System.out.println(hashValues);
        return hashValues;
    }

    public Vector<Integer> run(Vector<Integer> inP,Vector<Integer> notInP,long seed)
    {
        System.out.println("And the current seed is:"+seed);
        Vector<Integer>inputValues=new Vector<>();
        inputValues.addAll(inP);
        inputValues.addAll(notInP);

        for(int value:inputValues) {
            for (int i = 0; i <floor; i++) {
                HashFunctionImpl hashFunction=hashFunctions.get(i);
                int hashValue=hashFunction.twoUniversalHashing(value);
                if(!hashValues.contains(hashValue)){
                    hashValues.add(hashValue);
                }
            }
        }
        Collections.sort(hashValues);

        System.out.println("The bitmap follows:");
        System.out.println(hashValues);
        return hashValues;
    }


    public Vector<Integer> check(int maximum,long seed,Vector<Integer>_T_,Vector<Integer> hashResults)
    {
        Vector<Integer> falsePositiveValues=new Vector<>();
        int[] rangeValues= IntStream.range(0, maximum).toArray();
        for(int value:rangeValues){
            if(!_T_.contains(value)){
                Vector<Integer> values=new Vector<>();
                for(int i=0;i<hashFunctions.size();i++){
                    int hashValue=hashFunctions.get(i).twoUniversalHashing(value);
                    if(!values.contains(hashValue)){
                        values.add(hashValue);
                    }
                }
                boolean isFP=true;
                for(int hashValue:values){
                    if(!hashResults.contains(hashValue)){
                        isFP=false;
                    }
                }
                if(isFP){
                    falsePositiveValues.add(value);
                }
            }
        }
        System.out.println("For elements in the Universe range from 0 to 19");
        System.out.println("Those elements are false positives:");
        System.out.println(falsePositiveValues);
        return falsePositiveValues;
    }

    public Vector<Integer> check2D(Vector<Integer> centralPoint,int boundary,Vector<Integer>_T_,Vector<Integer> hashResults){
        Vector<Integer> falsePositiveValues=new Vector<>();
        Vector<Integer> rangeValues=new Vector<>();

        int x_lower_boundary=centralPoint.get(0)-boundary;
        int x_higher_boundary=centralPoint.get(0)+boundary;
        int y_lower_boundary=centralPoint.get(1)-boundary;
        int y_higher_boundary=centralPoint.get(1)+boundary;
        for(int x=x_lower_boundary;x<x_higher_boundary;x++){
            for(int y=y_lower_boundary;y<y_higher_boundary;y++){
                int value=x+y*10;
                rangeValues.add(value);
            }
        }
        for(int value:rangeValues){
            if(!_T_.contains(value)){
                Vector<Integer> values=new Vector<>();
                for(int i=0;i<hashFunctions.size();i++){
                    int hashValue=hashFunctions.get(i).twoUniversalHashing(value);
                    if(!values.contains(hashValue)){
                        values.add(hashValue);
                    }
                }
                boolean isFP=true;
                for(int hashValue:values){
                    if(!hashResults.contains(hashValue)){
                        isFP=false;
                    }
                }
                if(isFP){
                    falsePositiveValues.add(value);
                }
            }
        }
        System.out.println("Those elements are false positives:");
        System.out.println(falsePositiveValues.size()+"/"+(rangeValues.size()));

        return falsePositiveValues;
    }

    public boolean isPrime(int n)
    {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        for (int i = 5; i * i <= n; i = i + 6)
            if (n % i == 0 || n % (i + 2) == 0)
                return false;
        return true;
    }

    public int findNearestGreaterPrimeNumber(int value)
    {
        if(value<=1)
            return 2;
        int prime=value;
        boolean found=false;
        while(!found){
            prime++;
            if(isPrime(prime))
                found=true;
        }
        return prime;
    }
}