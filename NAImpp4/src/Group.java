import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Group {
    private double[] centroid;
    private List<double[]> inGroup;
    public Group(double[] centroid) {
        this.centroid = centroid;
        this.inGroup = new ArrayList<>(){};
    }

    public Group(double[] centroid, List<double[]> data){
        this.centroid = centroid;
        this.inGroup = data;
    }

    public void addToGroup(double[] value){
        this.inGroup.add(value);
    }

    public double[] getCentroid() {
        return centroid;
    }

    public List<double[]> getInGroup() {
        return inGroup;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Group){
            return Arrays.equals(centroid, ((Group) obj).centroid);
        } else {
            return super.equals(obj);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Centroid: ").append(Arrays.toString(centroid)).append("\n Values:\n");
        for (double[] doubles : inGroup) {
            stringBuilder.append("\t").append(Arrays.toString(doubles)).append('\n');
        }
        return stringBuilder.toString();
    }

    public void calculateCentroid() {
        if(!inGroup.isEmpty()){
            double[] newCentroid = new double[centroid.length];
            for (double[] doubles : inGroup) {
                for (int i = 0; i < doubles.length; i++) {
                    newCentroid[i] += doubles[i];
                }
            }
            for (int i = 0; i < newCentroid.length; i++) {
                newCentroid[i] = newCentroid[i] / inGroup.size();
            }
            centroid = newCentroid;

        }
    }

    public void clearValues(){
        this.inGroup = new ArrayList<>();
    }
}
