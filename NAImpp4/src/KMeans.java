import java.io.*;
import java.util.*;

public class KMeans {
    private final File dataFile;
    private final int groupsCount;
    private final List<double[]> data;
    private final List<Group> currentGroups;
    private List<Group> previousGroups;

    public KMeans(File dataFile, int groupsCount) {
        this.dataFile = dataFile;
        this.groupsCount = groupsCount;
        this.data = new ArrayList<>();
        this.currentGroups = new ArrayList<>();
        this.previousGroups = new ArrayList<>();
        loadDataFromFile();
        generateGroups();
        assignToGroups();

        for (int i = 0; !identicalGroups() ; i++) {
            previousGroups = new ArrayList<>();
            for (Group currentGroup : currentGroups) {
                previousGroups.add(new Group(currentGroup.getCentroid(), currentGroup.getInGroup()));
            }
            System.out.println("Iteration " + (i + 1));
            System.out.println("Current Groups:");
            for (int i1 = 0; i1 < currentGroups.size(); i1++) {
                System.out.println("Group " + (i1 + 1));
                System.out.println(currentGroups.get(i1));
                System.out.println("the sum of squares of the distances: " + currentGroups.get(i1).sumOfDistancesSquares());
                System.out.println();
                currentGroups.get(i1).calculateCentroid();
            }
            assignToGroups();
        }
    }

    public boolean identicalGroups() {
        if(currentGroups.size() != previousGroups.size()){
            return false;
        }
        for (int i = 0; i < currentGroups.size(); i++) {
            if(!currentGroups.get(i).equals(previousGroups.get(i))){
                return false;
            }
        }
        return true;
    }

    private void assignToGroups() {
        for (Group currentGroup : currentGroups) {
            currentGroup.clearValues();
        }
        for (double[] d : data) {
            Map<Group, Double> distances = new HashMap<>();
            for (Group group : currentGroups) {
                distances.put(group, calculateDistance(group, d));
            }
            double minDistance = 99999999;
            for (Double value : distances.values()) {
                if (value < minDistance) {
                    minDistance = value;
                }
            }
            Group assignTo = null;
            for (Group group : distances.keySet()) {
                if (distances.get(group) == minDistance) {
                    assignTo = group;
                }
            }
            assignTo.addToGroup(d);
        }
    }

    private Double calculateDistance(Group group, double[] data) {
        double[] centroid = group.getCentroid();
        double distance = 0;
        for (int i = 0; i < centroid.length; i++) {
            distance += Math.pow((centroid[i] - data[i]), 2);
        }
        return distance;
    }

    private void generateGroups() {
        currentGroups.add(new Group(data.getFirst()));
        currentGroups.add(new Group(data.get(1)));
        currentGroups.add(new Group(data.get(2)));
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
