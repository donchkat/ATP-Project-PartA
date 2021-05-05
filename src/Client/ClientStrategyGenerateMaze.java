package Client;

import IO.MyDecompressorInputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.AState;
import algorithms.search.Solution;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class ClientStrategyGenerateMaze implements IClientStrategy {
    @Override
    public void clientStrategy (InputStream inFromServer, OutputStream outToServer) {
        try {
            ObjectOutputStream toServer = new ObjectOutputStream(outToServer);
            ObjectInputStream fromServer = new ObjectInputStream(inFromServer);
            toServer.flush();
            int[] mazeDimensions = new int[]{50, 50};
            toServer.writeObject(mazeDimensions); //send maze dimensions to server
            toServer.flush();
            byte[] compressedMaze = (byte[])fromServer.readObject(); //read generated maze (compressed withMyCompressor)from server
            InputStream is = new MyDecompressorInputStream(new ByteArrayInputStream(compressedMaze));
            byte[] decompressedMaze = new byte[compressedMaze[0]+1+(50*50)]; //allocating byte[] for the decompressedmaze -
            is.read(decompressedMaze); //Fill decompressedMaze with bytes
            Maze maze = new Maze(decompressedMaze);
            System.out.println("after decompression:");
            maze.print();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
            }


