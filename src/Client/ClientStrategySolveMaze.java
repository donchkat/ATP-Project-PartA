package Client;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.AState;
import algorithms.search.Solution;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class ClientStrategySolveMaze implements IClientStrategy {
    @Override
    public void clientStrategy (InputStream inFromServer, OutputStream outToServer) {
        try {
            ObjectOutputStream toServer = new ObjectOutputStream(outToServer);
            ObjectInputStream fromServer = new ObjectInputStream(inFromServer);
            toServer.flush();
            MyMazeGenerator mg = new MyMazeGenerator();
            Maze maze = mg.generate(50, 50);
            toServer.writeObject(maze); //send maze to server
            toServer.flush();
            Solution mazeSolution = (Solution) fromServer.readObject(); //read generated maze (compressed with MyCompressor)from server
            System.out.println(String.format("Solution steps:%s", mazeSolution));
            ArrayList<AState> mazeSolutionSteps = mazeSolution.getSolutionPath();
            for (int i = 0; i < mazeSolutionSteps.size(); i++) {
                System.out.println(String.format("%s. %s", i, mazeSolutionSteps.get(i).toString()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
