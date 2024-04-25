import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KMeans {
    private final File dataFile;
    private final int groupsCount;
    private final List<double[]> centroids;
    private final List<double[]> data;

    public KMeans(File dataFile, int groupsCount) {
        this.dataFile = dataFile;
        this.groupsCount = groupsCount;
        this.centroids = new ArrayList<>(groupsCount);
        this.data = new ArrayList<>();
        loadDataFromFile();
        generateCentroids();
    }

    private void generateCentroids() {
        for(int i = 0; i < groupsCount; i++){
            double[] centroid = new double[data.getFirst().length];
            for (int j = 0; j < centroid.length; j++) {
                centroid[j] = Math.random();
            }
            centroids.add(centroid);
        }
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
