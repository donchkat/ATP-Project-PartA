package Server;

import IO.MyCompressorOutputStream;
import IO.SimpleCompressorOutputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.BestFirstSearch;
import algorithms.search.ISearchingAlgorithm;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class ServerStrategySolveSearchProblem implements IServerStrategy{

    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            Maze toSolveMaze=(Maze)fromClient.readObject();
            SearchableMaze searchableMaze = new SearchableMaze(toSolveMaze);
            ISearchingAlgorithm searcher=new BestFirstSearch();
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
}
