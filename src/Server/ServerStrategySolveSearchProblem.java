package Server;

import IO.MyCompressorOutputStream;
import IO.SimpleCompressorOutputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.*;

import java.util.concurrent.*;
import java.io.*;

public class ServerStrategySolveSearchProblem implements IServerStrategy {
    ConcurrentHashMap<byte[], File> concurrentHashMap = new ConcurrentHashMap<>();
    String tempDirectoryPath = System.getProperty("java.io.tmpdir");
    volatile int counter=0;
    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            Maze toSolveMaze = (Maze) fromClient.readObject();
            byte[] compMaze = toSolveMaze.toByteArray();
            Solution MazeSolution;
            File myFile;
            if (concurrentHashMap.containsKey(compMaze)) {
                myFile = concurrentHashMap.get(compMaze).getAbsoluteFile();
                ObjectInputStream fromFile = new ObjectInputStream(new FileInputStream(myFile));
                MazeSolution = (Solution) fromFile.readObject();

            } else {
                SearchableMaze searchableMaze = new SearchableMaze(toSolveMaze);
                ISearchingAlgorithm searcher = getSearchingAlgFromConfig();
                MazeSolution = searcher.solve(searchableMaze);
                myFile=new File(tempDirectoryPath+"MazeNumber"+counter);
                System.out.println("++++++++++++++++++++++++++++++++++++++");
                System.out.println(myFile.getAbsolutePath());
                System.out.println("++++++++++++++++++++++++++++++++++++++");

                counter++;
            }
            OutputStream out = new MyCompressorOutputStream(toClient);
            toClient.writeObject(MazeSolution);
            out.flush();
            out.close();
            fromClient.close();
            toClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ISearchingAlgorithm getSearchingAlgFromConfig() {
        Configurations configurations = Configurations.getInstance();
        try (InputStream input = new FileInputStream("src/resources/config.properties")) {

            // load a properties file
            try {
                configurations.getProperties().load(input);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (configurations.getProperties().getProperty("db.mazeSearchingAlg").equals("BestFirstSearch"))
                return new BestFirstSearch();
            if (configurations.getProperties().getProperty("db.mazeSearchingAlg").equals("BreadthFirstSearch"))
                return new BestFirstSearch();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return new DepthFirstSearch();
    }
}
