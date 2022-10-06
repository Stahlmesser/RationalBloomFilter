import java.util.Vector;
import java.util.stream.IntStream;

public class CheckImpl {
    private int maximumOfU;
    private Vector<Integer> falsePositives;
    private Vector<Integer> truePositives;
    private Vector<Integer> bitmap;
    private Vector<HashFunctionImpl> hashFunctions;
    private float falsePositiveRate;

    public CheckImpl(int maximumOfU, Vector<Integer> truePositives, Vector<Integer> bitmap, Vector<HashFunctionImpl> hashFunctions) {
        this.maximumOfU = maximumOfU;
        this.truePositives = truePositives;
        this.bitmap = bitmap;
        this.hashFunctions = hashFunctions;
        this.falsePositives=new Vector<>();
    }

    public void check(){
        int[] rangeValues= IntStream.range(0, maximumOfU).toArray();
        for(int value:rangeValues){
            if(!truePositives.contains(value)){
                Vector<Integer> values=new Vector<>();
                for(int i=0;i<hashFunctions.size();i++){
                    int hashValue=hashFunctions.get(i).twoUniversalHashing(value);
                    if(!values.contains(hashValue)){
                        values.add(hashValue);
                    }
                }
                boolean isFP=true;
                for(int hashValue:values){
                    if(!bitmap.contains(hashValue)){
                        isFP=false;
                    }
                }
                if(isFP){
                    falsePositives.add(value);
                }
            }
        }
        falsePositiveRate=falsePositives.size()/(maximumOfU-truePositives.size());
    }
}
