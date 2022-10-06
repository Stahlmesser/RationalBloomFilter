import java.util.Random;
import java.util.Vector;

public class RationalTest {
//    public static void main(String[] args){
//        Vector<NodeImpl> nodes=new Vector<>();
//        BroadcastImpl bc=new BroadcastImpl();
//        bc.generatePoints(50,1000,212);
//        bc.setNeighbors();
//
//        System.out.println(bc.getAvg());
//    }

//    public static void main(String[] args)
//    {
//        Vector<Integer> T=new Vector<Integer>();
//        int times=10,maximum=200;
//        double power=8.5;
//        Random rng=new Random();
//
//        System.out.println("We have the length of frame :"+times);
//        System.out.println("And the maximum value of the universe is :"+maximum);
//        System.out.println("The given false positive rate: 2^-"+power);
//        System.out.println("The theoretical hash function number: "+power);
//        SenderImpl sender=new SenderImpl(maximum,10);
//        ReceiverImpl receiver=new ReceiverImpl(rng);
//
//        for(int i=0;i<times;i++){
//            System.out.println("This is the "+(i+1)+"th data transmission");
//            int seed=rng.nextInt(0,100);
//            Vector<Integer> vec=sender.send(seed);
//            T.addAll(vec);
//            receiver.receive(vec);
//        }
//        System.out.println(T);
//        receiver.print();
//
//        int m= (int) ((power*receiver.getInP().size()+power*receiver.getNotInP().size())*1.4427);
//        BloomFilterImpl BFImpl= new BloomFilterImpl(power,m);
//
//        int seed=rng.nextInt(0,1000);
//        BFImpl.initializeBloomFilter(T,seed);
//
//        System.out.println();
//        Vector<Integer> hashResults=BFImpl.run(receiver.getInP(),receiver.getNotInP(),seed);
//        Vector<Integer> falsePositiveResults=BFImpl.check(maximum,seed,T,hashResults);
//        System.out.println("For the standard Bloom filter:");
//        System.out.println("The false positive rate:"+falsePositiveResults.size()+"/"+receiver.getNotInP().size());
//
//        Vector<Integer> rationalHashResults=BFImpl.rationalRun(receiver.getInP(),receiver.getNotInP(),seed);
//        Vector<Integer> rationalFalsePositiveResults=BFImpl.check(maximum,seed,T,rationalHashResults);
//        System.out.println("For the rational Bloom filter:");
//        System.out.println("The false positive rate:"+rationalFalsePositiveResults.size()+"/"+receiver.getNotInP().size());
//        int whole=receiver.getInP().size()+receiver.getNotInP().size();
//        System.out.println(receiver.getInP().size()+"/"+whole);
//    }

    public static void main(String[] args) {
        Vector<Integer> T=new Vector<Integer>();
        int times=10,maximum=50;
        double power=8.5;
        Random rng=new Random();
        System.out.println("We have the length of frame :"+times);
        System.out.println("And the maximum value of the universe is :"+maximum);
        System.out.println("The given false positive rate: 2^-"+power);
        System.out.println("The theoretical hash function number: "+power);

        SenderImpl sender=new SenderImpl(maximum,20);
        //initialize a central point
        Vector<Integer> centralPoint=new Vector<>();
        centralPoint.add(28);
        centralPoint.add(40);

        int boundary=28,length=10;
        sender.setPointRNG(new coordinateOfPointRNGImpl(centralPoint,boundary,length));

        int seed=rng.nextInt(0,1000);
        ReceiverImpl receiver=new ReceiverImpl(rng);
        receiver.initializePri(centralPoint);

        for(int i=0;i<times;i++){
            receiver.receive2D(sender.send2D(seed));
        }

        receiver.hash2D(boundary);
        Vector<Integer> elements=new Vector<>();
        elements.addAll(receiver.getInP2D());
        elements.addAll(receiver.getNotInP2D());

        int m= (int) (power*elements.size()*1.4427);
        BloomFilterImpl BFImpl= new BloomFilterImpl(power,m);
        BFImpl.initializeBloomFilter(elements,seed);

        System.out.println(receiver.getInP2D());
        System.out.println(receiver.getNotInP2D());

        Vector<Integer> hashResults=BFImpl.run(receiver.getInP2D(),receiver.getNotInP2D(),seed);
        Vector<Integer> falsePositiveResults=BFImpl.check2D(centralPoint, boundary,elements,hashResults);

        Vector<Integer> rationalHashResults=BFImpl.rationalRun(receiver.getInP2D(),receiver.getNotInP2D(),seed);
        Vector<Integer> rationalFalsePositiveResults=BFImpl.check2D(centralPoint, boundary,elements,rationalHashResults);
    }
}

