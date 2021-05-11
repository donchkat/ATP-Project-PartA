package Server;

import Errors.LowBoundInput;
import Errors.NullError;
import Errors.OutOfBoundMatrixInput;
import algorithms.mazeGenerators.Maze;
import algorithms.search.*;

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
    ConcurrentHashMap<Integer, File> concurrentHashMap;
    String tempDirectoryPath = System.getProperty("java.io.tmpdir");

    /**
     * we solve the maze or get the saved solution we already had, and return it to the client
     *
     * @param inFromClient -maze that the client want to solve
     * @param outToClient  the solution of the maze
     */
    @Override
    public void ServerStrategy (InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            Maze toSolveMaze = (Maze) fromClient.readObject();
            toSolveMaze.print(); //delete
            byte[] byteArrayMaze = toSolveMaze.toByteArray();
            Solution MazeSolution;
            File HashMapOfMazesFile = new File(tempDirectoryPath + "hashMapOfMazes.hmom");
            ObjectOutputStream writeToHashFile;
            hashMapGenerator(HashMapOfMazesFile);
            if (concurrentHashMap.containsKey(Arrays.hashCode(byteArrayMaze))) {
                MazeSolution = getSolutionFromHash(byteArrayMaze);
            } else {
                MazeSolution = insertSolutionToHash(toSolveMaze, byteArrayMaze);
                writeToHashFile = new ObjectOutputStream(new FileOutputStream(tempDirectoryPath + "hashMapOfMazes.hmom"));
                writeToHashFile.writeObject(concurrentHashMap);
                writeToHashFile.flush();
                writeToHashFile.close();
            }
            toClient.writeObject(MazeSolution);
            fromClient.close();
            toClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * we create the hash map if it wasn't created yet, otherwise it gets the hash map from the file.
     *
     * @param hashFile
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void hashMapGenerator (File hashFile) throws IOException, ClassNotFoundException {
        if (!hashFile.isFile())
            concurrentHashMap = new ConcurrentHashMap<>();
        else {
            ObjectInputStream fromHashMapFile = new ObjectInputStream(new FileInputStream(hashFile));
            concurrentHashMap = (ConcurrentHashMap<Integer, File>) fromHashMapFile.readObject();
        }
    }

    /**
     * in case we already solved the given maze - we just find it in the hash map and return it.
     *
     * @param byteArrayMaze - the given maze in byte array
     * @return the solution we got from the hash map
     * @throws IOException            - exception
     * @throws ClassNotFoundException - exception
     */
    private Solution getSolutionFromHash (byte[] byteArrayMaze) throws IOException, ClassNotFoundException {
        File myFile = concurrentHashMap.get(Arrays.hashCode(byteArrayMaze)).getAbsoluteFile();
        ObjectInputStream fromFile = new ObjectInputStream(new FileInputStream(myFile));
        Solution MazeSolution = (Solution) fromFile.readObject();
        return MazeSolution;
    }

    /**
     * in case we didn't solve the given maze yet,
     * we solve it and save the solution in the file and in the hash table.
     *
     * @param toSolveMaze   - the maze we are solving
     * @param byteArrayMaze - the maze in bytes array
     * @return the solution of the maze
     * @throws NullError             - exception
     * @throws LowBoundInput         - exception
     * @throws OutOfBoundMatrixInput - exception
     * @throws IOException           - exception
     */
    private Solution insertSolutionToHash (Maze toSolveMaze, byte[] byteArrayMaze) throws NullError, LowBoundInput, OutOfBoundMatrixInput, IOException {
        SearchableMaze searchableMaze = new SearchableMaze(toSolveMaze);
        ISearchingAlgorithm searcher = getSearchingAlgFromConfig();
        Solution MazeSolution = searcher.solve(searchableMaze);
        ObjectOutputStream toFile = new ObjectOutputStream(new FileOutputStream(tempDirectoryPath + Arrays.hashCode(byteArrayMaze)));
        toFile.writeObject(MazeSolution);
        File solFile = new File(tempDirectoryPath + Arrays.hashCode(byteArrayMaze));
        concurrentHashMap.put(Arrays.hashCode(byteArrayMaze), solFile);
        return MazeSolution;
    }

    /**
     * @return the wanted searching algorithm to solve the maze if it wasn't solved before
     */
    private ISearchingAlgorithm getSearchingAlgFromConfig () {
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
