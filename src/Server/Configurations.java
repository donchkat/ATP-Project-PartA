package Server;

public class Configurations {
    private static Configurations configurations = null;
    private int threadPoolSize;
    private String mazeGeneratingAlgorithm;
    private String mazeSearchingAlgorithm;

    private Configurations () {
        this.threadPoolSize = 1;
        this.mazeGeneratingAlgorithm = "Simple";
        this.mazeSearchingAlgorithm = "BestFirstSearch";
    }

    public static Configurations getInstance () {
        if (configurations == null)
            configurations = new Configurations();
        return configurations;
    }

    public int getThreadPoolSize () {
        return threadPoolSize;
    }

    public void setThreadPoolSize (int threadPoolSize) {
        this.threadPoolSize = threadPoolSize;
    }

    public String getMazeGeneratingAlgorithm () {
        return mazeGeneratingAlgorithm;
    }

    public void setMazeGeneratingAlgorithm (String mazeGeneratingAlgorithm) {
        this.mazeGeneratingAlgorithm = mazeGeneratingAlgorithm;
    }

    public String getMazeSearchingAlgorithm () {
        return mazeSearchingAlgorithm;
    }

    public void setMazeSearchingAlgorithm (String mazeSearchingAlgorithm) {
        this.mazeSearchingAlgorithm = mazeSearchingAlgorithm;
    }


}



