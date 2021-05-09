package Server;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

/**
 * singleton class that is able to change the properties of the maze
 */
public class Configurations {
    private static Configurations configurations = null;
    private int threadPoolSize;
    private String mazeGeneratingAlgorithm;
    private String mazeSearchingAlgorithm;
    private Properties properties;

    public Properties getProperties () {
        return properties;
    }

    public void setProperties (Properties properties) {
        this.properties = properties;
    }

    private Configurations () {
        this.threadPoolSize = 1;
        this.mazeGeneratingAlgorithm = "My";
        this.mazeSearchingAlgorithm = "BestFirstSearch";
        this.properties = new Properties();
        try (OutputStream output = new FileOutputStream("src/resources/config.properties")) {
            // set the properties value
            properties.setProperty("db.numOfThreads", "" + threadPoolSize);
            properties.setProperty("db.mazeGeneratingAlg", mazeGeneratingAlgorithm);
            properties.setProperty("db.mazeSearchingAlg", mazeSearchingAlgorithm);
            // save properties to project root folder
            properties.store(output, null);
            System.out.println(properties);
        } catch (IOException io) {
            io.printStackTrace();
        }
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
        try (OutputStream output = new FileOutputStream("src/resources/config.properties")) {
            // set the properties value
            properties.setProperty("db.numOfThreads", "" + this.threadPoolSize);
            // save properties to project root folder
            properties.store(output, null);
            System.out.println(properties);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public String getMazeGeneratingAlgorithm () {
        return mazeGeneratingAlgorithm;
    }

    public void setMazeGeneratingAlgorithm (String mazeGeneratingAlgorithm) {
        this.mazeGeneratingAlgorithm = mazeGeneratingAlgorithm;
        try (OutputStream output = new FileOutputStream("src/resources/config.properties")) {
            // set the properties value
            properties.setProperty("db.mazeGeneratingAlg", mazeGeneratingAlgorithm);
            // save properties to project root folder
            properties.store(output, null);
            System.out.println(properties);
        } catch (IOException io) {
            io.printStackTrace();
        }

    }

    public String getMazeSearchingAlgorithm () {
        return mazeSearchingAlgorithm;
    }

    public void setMazeSearchingAlgorithm (String mazeSearchingAlgorithm) {
        this.mazeSearchingAlgorithm = mazeSearchingAlgorithm;
        try (OutputStream output = new FileOutputStream("src/resources/config.properties")) {
            // set the properties value
            properties.setProperty("db.mazeSearchingAlg", mazeSearchingAlgorithm);
            // save properties to project root folder
            properties.store(output, null);
            System.out.println(properties);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }


}



