import java.util.Collections;
import java.util.Random;
import java.util.Vector;
import java.util.HashSet;

public class ReceiverImpl {
    private Random rng;
    private Vector<Integer> previousCollection;
    private Vector<Integer> inP;
    private Vector<Integer> notInP;
    private Vector<Integer> centralPoint;
    private Vector<Integer> notInP2D;
    private Vector<Integer> inP2D;
    private PredictorImpl pri;

    public void initializePri(Vector<Integer> centralPoint) {
        this.centralPoint = centralPoint;
        this.pri = new PredictorImpl(centralPoint);
    }

    public ReceiverImpl(Random rng){
        this.rng = rng;
        this.inP = new Vector<Integer>();
        this.notInP = new Vector<Integer>();
        this.inP2D = new Vector<Integer>();
        this.notInP2D = new Vector<Integer>();
    }

    public void receive(Vector<Integer>collection){
        if(previousCollection==null){
            for(int value:collection){
                notInP.add(value);
            }
        }else{
            int previousAVG=0;
            for(int value:previousCollection){
                previousAVG+=value;
            }
            previousAVG/=previousCollection.size();

            int AVG=0;
            for(int value:collection){
                AVG+=value;
            }
            AVG/=collection.size();

            if(AVG>=previousAVG){
                for(int value:collection){
                    if(value>=previousAVG){
                        if(!inP.contains(value)) {
                            inP.add(value);
                        }
                    }else{
                        if(!notInP.contains(value)){
                            notInP.add(value);
                        }
                    }
                }
            }else{
                for(int value:collection){
                    if(value>=previousAVG){
                        if(!notInP.contains(value)){
                            notInP.add(value);
                        }
                    }else{
                        if(!inP.contains(value)) {
                            inP.add(value);
                        }
                    }
                }
            }
        }
        this.previousCollection = new Vector<Integer>(collection);

        HashSet inPHashSet = new HashSet(inP);
        HashSet notInPHashSet = new HashSet(notInP);

        inP=new Vector<>(inPHashSet);
        notInP=new Vector<>(notInPHashSet);
    }

    public void receive2D(Vector<Vector<Integer>> collection){
        pri.predictDirectionOf2DCollection(collection);
    }

    public void hash2D(int boundary){
        Vector<Integer>notInPHashValues=new Vector<>();
        Vector<Integer>inPHashValues=new Vector<>();
        for(Vector<Integer> point:pri.getNotInP()){
            int hashValue=point.get(0)+point.get(1)*10;
            notInPHashValues.add(hashValue);
        }
        for(Vector<Integer> point:pri.getInP()){
            int hashValue=point.get(0)+point.get(1)*10;
            inPHashValues.add(hashValue);
        }

        HashSet inPHashSet = new HashSet(inPHashValues);
        HashSet notInPHashSet = new HashSet(notInPHashValues);

        inPHashValues=new Vector<>(inPHashSet);
        notInPHashValues=new Vector<>(notInPHashSet);

        Vector<Integer> duplicateValues=new Vector<>();
        for(int value:notInPHashValues){
            if(inPHashValues.contains(value)){
                duplicateValues.add(value);
            }
        }
        for(int value:duplicateValues){
          int index=notInPHashValues.indexOf(value);
          notInPHashValues.remove(index);
        }

        notInP2D.addAll(notInPHashValues);
        inP2D.addAll(inPHashValues);

    }

    public Vector<Integer> print(){
        Vector<Integer> _T_=new Vector<>();
        System.out.println("Here is the vector of elements that may be in the set");
        System.out.println(notInP);
        System.out.println("Here is the vector of elements that may not be mapped");
        System.out.println(inP);
        _T_.addAll(notInP);
        _T_.addAll(inP);
        Collections.sort(_T_);
        System.out.println("The whole collection is:");
        System.out.println(_T_);
        return _T_;
    }

    public Vector<Integer> getNotInP2D() {
        return notInP2D;
    }

    public Vector<Integer> getInP2D() {
        return inP2D;
    }

    public Vector<Integer> getInP() {
        return inP;
    }

    public Vector<Integer> getNotInP() {
        return notInP;
    }
}
