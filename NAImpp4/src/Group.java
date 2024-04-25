import java.util.ArrayList;
import java.util.List;

public class Group {
    private double[] centroid;
    List<double[]> inGroup;
    public Group(double[] centroid) {
        this.centroid = centroid;
        this.inGroup = new ArrayList<>();
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
}
