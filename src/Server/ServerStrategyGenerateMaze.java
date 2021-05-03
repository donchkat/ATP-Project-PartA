package Server;

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
            //System.out.println("inside the maze generation strategy");
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            //System.out.println("got the IO data succesfully");

            int[] SizeOfMatrix = (int[]) fromClient.readObject();
            //System.out.println("the input is : " + SizeOfMatrix.toString());

            MyMazeGenerator mazeGenerator = new MyMazeGenerator();
            Maze toClientMaze = mazeGenerator.generate(SizeOfMatrix[0]/*rows*/, SizeOfMatrix[1]/*columns*/);
            toClientMaze.print();
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

            //NEED TO BE CHANGED TO MYCOMPRESSOR
            //OutputStream out = new SimpleCompressorOutputStream(toClient);
            ByteArrayOutputStream bOut = new ByteArrayOutputStream();
            OutputStream out = new SimpleCompressorOutputStream(bOut);
            //System.out.println("created new simple compressor");
            out.write(toClientMaze.toByteArray());
            toClient.writeObject(bOut.toByteArray());
            //System.out.println("compressed the maze to the output stream!");
            //out.flush();
            out.close();
            //System.out.println("closed out");
            fromClient.close();
            //System.out.println("closed fromClient");
           // toClient.close();
            //System.out.println("still here");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
