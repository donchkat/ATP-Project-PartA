package Client;

import Server.Configurations;
import algorithms.mazeGenerators.*;
import algorithms.search.AState;
import algorithms.search.BestFirstSearch;
import algorithms.search.DepthFirstSearch;
import algorithms.search.Solution;

import java.io.*;
import java.util.ArrayList;

public class ClientStrategySolveMaze implements IClientStrategy {
    @Override
    public void clientStrategy (InputStream inFromServer, OutputStream outToServer) {
        try {
            ObjectOutputStream toServer = new ObjectOutputStream(outToServer);
            ObjectInputStream fromServer = new ObjectInputStream(inFromServer);
            toServer.flush();
            IMazeGenerator mg = GetKindFromConfig();
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

    private IMazeGenerator GetKindFromConfig() {
        Configurations configurations=Configurations.getInstance();
        try (InputStream input = new FileInputStream("src/resources/config.properties")) {

            // load a properties file
            try {
                configurations.getProperties().load(input);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(configurations.getProperties().getProperty("db.mazeGeneratingAlgorithm").equals("Simple"))
                return new SimpleMazeGenerator();
            if(configurations.getProperties().getProperty("db.mazeGeneratingAlgorithm").equals("Empty"))
                return new EmptyMazeGenerator();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return new MyMazeGenerator();
    }
}
