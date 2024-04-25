import java.io.File;

public class KMeans {
    private final File dataFile;
    private final int groupsCount;

    public KMeans(File dataFile, int groupsCount) {
        this.dataFile = dataFile;
        this.groupsCount = groupsCount;
    }

    @Override
    public String toString() {
        return dataFile.getAbsolutePath() + "       k = " + groupsCount;
    }
}
