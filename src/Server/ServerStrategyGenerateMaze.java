package Server;

import IO.MyCompressorOutputStream;
import IO.SimpleCompressorOutputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.*;
import java.util.ArrayList;

/**
 * Strategy design pattern - one of the strategies of our server.
 * This strategy is to get a task from client and generate a maze with the given sizes
 * and then to return the generated maze to client.(in compressed form)
 */
public class ServerStrategyGenerateMaze implements IServerStrategy {
    @Override
    public void applyStrategy (InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            int[] SizeOfMatrix = (int[]) fromClient.readObject();
            MyMazeGenerator mazeGenerator = new MyMazeGenerator();
            Maze toClientMaze = mazeGenerator.generate(SizeOfMatrix[0]/*rows*/, SizeOfMatrix[1]/*columns*/);
            System.out.println("Before compression:");
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
}
