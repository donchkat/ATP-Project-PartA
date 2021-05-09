package Server;

import IO.MyCompressorOutputStream;

import algorithms.mazeGenerators.*;

import java.io.*;


/**
 * Strategy design pattern - one of the strategies of our server.
 * This strategy is to get a task from client and generate a maze with the given sizes
 * and then to return the generated maze to client.(in compressed form)
 */
public class ServerStrategyGenerateMaze implements IServerStrategy {
    @Override
    public void applyStrategy (InputStream inFromClient, OutputStream outToClient) {
        System.out.println("in strategy:"+Thread.currentThread().getId());
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            int[] SizeOfMatrix = (int[]) fromClient.readObject();
            IMazeGenerator mazeGenerator = GetKindFromConfig();
            Maze toClientMaze = mazeGenerator.generate(SizeOfMatrix[0]/*rows*/, SizeOfMatrix[1]/*columns*/);
            System.out.println("Before compression: "+Thread.currentThread().getId());
            toClientMaze.print();
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            ByteArrayOutputStream bOut = new ByteArrayOutputStream();
            OutputStream out = new MyCompressorOutputStream(bOut);
            out.write(toClientMaze.toByteArray());
            toClient.writeObject(bOut.toByteArray());
            out.flush();
            out.close();
            fromClient.close();
            //MAYBE PROBLEMATIC!
            toClient.close();
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
            if(configurations.getProperties().getProperty("db.mazeGeneratingAlg")=="Simple")
                return new SimpleMazeGenerator();
            if(configurations.getProperties().getProperty("db.mazeGeneratingAlg")=="Empty")
                return new EmptyMazeGenerator();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return new MyMazeGenerator();
    }
}
