import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KMeans {
    private final File dataFile;
    private final int groupsCount;
    private final List<double[]> data;
    private final List<Group> currentGroups;
    private final List<Group> previousGroups;

    public KMeans(File dataFile, int groupsCount) {
        this.dataFile = dataFile;
        this.groupsCount = groupsCount;
        this.data = new ArrayList<>();
        this.currentGroups = new ArrayList<>();
        this.previousGroups = new ArrayList<>();
        loadDataFromFile();
        generateGroups();
        assignToGroups();
    }

    private void assignToGroups() {
        Map<Group, Double> distances = new HashMap<>();
        data.forEach(data -> {
            currentGroups.forEach(group -> {
                distances.put(group, calculateDistance(group, data));
            });
        });
        System.out.println(distances);
    }

    private Double calculateDistance(Group group, double[] data) {
        double[] centroid = group.getCentroid();
        double distance = 0;
        for (int i = 0; i < centroid.length; i++) {
            distance += Math.pow((centroid[i] + data[i]), 2);
        }
        return distance;
    }

    private void generateGroups() {
        for (double[] generatedCentroid : generateCentroids()) {
            currentGroups.add(new Group(generatedCentroid));
        }
    }

    private List<double[]> generateCentroids() {
        List<double[]> centroids = new ArrayList<>(groupsCount);
        for(int i = 0; i < groupsCount; i++){
            double[] centroid = new double[data.getFirst().length];
            for (int j = 0; j < centroid.length; j++) {
                centroid[j] = Math.random();
            }
            centroids.add(centroid);
        }
        return centroids;
//        centroids.forEach((centroid) -> {
//            for (double v : centroid) {
//                System.out.print(v + " ");
//            }
//            System.out.println();
//        });
    }

    private void loadDataFromFile() {
        try(
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(dataFile.getAbsolutePath())))
        ){
            String line;
            while ((line = bufferedReader.readLine()) != null){
                data.add(stringArrayToDoubleArray(line.split(",")));
            }
        } catch (IOException e){
            throw new RuntimeException();
        }
    }

    private double[] stringArrayToDoubleArray(String[] stringArray) {
        double[] result = new double[stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            result[i] = Double.parseDouble(stringArray[i]);
        }
        return result;
    }

    @Override
    public String toString() {
        return dataFile.getAbsolutePath() + "       k = " + groupsCount;
    }
}
