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
            //DO WE NEED TO USE HERE DECORATOR PATTERN?
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);

            int[] SizeOfMatrix = (int[]) fromClient.readObject();
            MyMazeGenerator mazeGenerator = new MyMazeGenerator();
            Maze toClientMaze = mazeGenerator.generate(SizeOfMatrix[0]/*rows*/, SizeOfMatrix[1]/*columns*/);
            //NEED TO BE CHANGED TO MYCOMPRESSOR
            OutputStream out = new SimpleCompressorOutputStream(toClient);
            out.write(toClientMaze.toByteArray());
            out.flush();
            out.close();
            fromClient.close();
            toClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
