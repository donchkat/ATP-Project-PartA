package Server;

import IO.MyCompressorOutputStream;
import IO.SimpleCompressorOutputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.*;

import java.io.*;

public class ServerStrategySolveSearchProblem implements IServerStrategy{

    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            Maze toSolveMaze=(Maze)fromClient.readObject();
            SearchableMaze searchableMaze = new SearchableMaze(toSolveMaze);

            ISearchingAlgorithm searcher=getSearchingAlgFromConfig();
            Solution MazeSolution=searcher.solve(searchableMaze);
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
        Configurations configurations=Configurations.getInstance();
        try (InputStream input = new FileInputStream("src/resources/config.properties")) {

            // load a properties file
            try {
                configurations.getProperties().load(input);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(configurations.getProperties().getProperty("db.mazeSearchingAlg")=="BestFirstSearch")
                return new BestFirstSearch();
            if(configurations.getProperties().getProperty("db.mazeSearchingAlg")=="BreadthFirstSearch")
                return new BestFirstSearch();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return new DepthFirstSearch();
    }
}
