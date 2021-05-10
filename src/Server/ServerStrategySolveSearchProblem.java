package Server;

import IO.MyCompressorOutputStream;
import IO.SimpleCompressorOutputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.concurrent.*;
import java.io.*;

/**
 * Strategy design pattern - one of the strategies of our server.
 * This strategy is to get a specific maze from client and find a solution to the maze
 * if we generated this maze before we will send it from the file that contains this solution
 * and then to return the generated solution to client.(in compressed form)
 */
public class ServerStrategySolveSearchProblem implements IServerStrategy {
    //not sure its right to put it here
    ConcurrentHashMap<Integer, File> concurrentHashMap;
    String tempDirectoryPath = System.getProperty("java.io.tmpdir");

    /**
     * @param inFromClient -maze that the client want to solve
     * @param outToClient the solution of the maze
     */
    @Override
    public void ServerStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            Maze toSolveMaze = (Maze) fromClient.readObject();
            System.out.println("the maze we need to solve:");
            toSolveMaze.print();
            byte[] compMaze = toSolveMaze.toByteArray();
            Solution MazeSolution;
            File myFile,HashMapOfMazesFile=new File(tempDirectoryPath+"hashMapOfMazes.hmom");
            ObjectOutputStream writeToHashFile;
            if(!HashMapOfMazesFile.isFile())
                concurrentHashMap = new ConcurrentHashMap<>();
            else {
                ObjectInputStream fromHashMapFile = new ObjectInputStream(new FileInputStream(HashMapOfMazesFile));
                concurrentHashMap = (ConcurrentHashMap<Integer, File>) fromHashMapFile.readObject();
            }
            if (concurrentHashMap.containsKey(Arrays.hashCode(compMaze))) {
                System.out.println("This maze already solved!");
                myFile = concurrentHashMap.get(Arrays.hashCode(compMaze)).getAbsoluteFile();
                ObjectInputStream fromFile = new ObjectInputStream(new FileInputStream(myFile));
                MazeSolution = (Solution) fromFile.readObject();
            } else {
                System.out.println("I see this maze for the first time!");
                SearchableMaze searchableMaze = new SearchableMaze(toSolveMaze);
                ISearchingAlgorithm searcher = getSearchingAlgFromConfig();
                MazeSolution = searcher.solve(searchableMaze);
                System.out.println("the hash code is:" + Arrays.hashCode(compMaze));
                ObjectOutputStream toFile = new ObjectOutputStream(new FileOutputStream(tempDirectoryPath + Arrays.hashCode(compMaze)));
                toFile.writeObject(MazeSolution);
                File solFile = new File(tempDirectoryPath + Arrays.hashCode(compMaze));
                concurrentHashMap.put(Arrays.hashCode(compMaze) ,solFile);
                writeToHashFile=new ObjectOutputStream(new FileOutputStream(tempDirectoryPath+"hashMapOfMazes.hmom"));
                writeToHashFile.writeObject(concurrentHashMap);
                writeToHashFile.flush();
                writeToHashFile.close();

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

    /**
     * @return the wanted searching algorithm to solve the maze if it wasn't solved before
     */
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
