import java.util.Vector;

public class PredictorImpl {
    private Vector<Vector<Integer>> previous2DCollection;
    private Vector<Vector<Integer>> notInP;
    private Vector<Vector<Integer>> inP;
    private int previousDistance;
    Vector<Integer> centralPoint;

    public Vector<Vector<Integer>> getNotInP() {
        return notInP;
    }

    public Vector<Vector<Integer>> getInP() {
        return inP;
    }

    public PredictorImpl(Vector<Integer> centralPoint) {
        this.previous2DCollection = new Vector<Vector<Integer>>();
        this.notInP = new Vector<Vector<Integer>>();
        this.inP = new Vector<Vector<Integer>>();
        this.centralPoint = centralPoint;
    }

    public void setCentralPoint(Vector<Integer> centralPoint) {
        this.centralPoint = centralPoint;
    }

    public void predictDirectionOf2DCollection(Vector<Vector<Integer>> current2DCollection){
        if(previous2DCollection.size()==0){
           previous2DCollection.addAll(current2DCollection);
           for(Vector<Integer>point:current2DCollection){
               previousDistance+=(int)(Math.sqrt(Math.pow(point.get(0)-centralPoint.get(0), 2)+Math.pow(point.get(1)-centralPoint.get(1), 2)));
           }
           previousDistance/=current2DCollection.size();
           notInP.addAll(current2DCollection);
        }else{
            int instance=0;
            for(Vector<Integer>point:current2DCollection){
                instance+=(int)(Math.sqrt(Math.pow(point.get(0)-centralPoint.get(0), 2)+Math.pow(point.get(1)-centralPoint.get(1), 2)));
            }
            instance/=current2DCollection.size();
            if(instance<previousDistance){
                for(Vector<Integer>point:current2DCollection){
                    int currentDistance =(int)(Math.sqrt(Math.pow(point.get(0)-centralPoint.get(0), 2)+Math.pow(point.get(1)-centralPoint.get(1), 2)));
                    if(currentDistance<=previousDistance){
                        inP.add(point);
                    }else{
                        notInP.add(point);
                    }
                }
            }else{
                for(Vector<Integer>point:current2DCollection){
                    int currentDistance =(int)(Math.sqrt(Math.pow(point.get(0)-centralPoint.get(0), 2)+Math.pow(point.get(1)-centralPoint.get(1), 2)));
                    if(currentDistance<=previousDistance){
                        notInP.add(point);
                    }else{
                        inP.add(point);
                    }
                }
            }
        }
    }




}
