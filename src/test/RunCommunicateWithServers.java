package test;

import Client.*;
import IO.*;
import Server.*;
import algorithms.mazeGenerators.*;
import algorithms.search.AState;
import algorithms.search.Solution;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;


public class RunCommunicateWithServers {
    public static void main (String[] args) {
        //Initializing servers
        Server mazeGeneratingServer = new Server(5400, 1000, new ServerStrategyGenerateMaze());
        Server solveSearchProblemServer = new Server(5401, 1000, new ServerStrategySolveSearchProblem());
        //Starting servers
        solveSearchProblemServer.start();
        mazeGeneratingServer.start();

        Thread[] threads = new Thread[6];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(()->{
                System.out.println("thread number "+Thread.currentThread().getId() + " has started");
                //CommunicateWithServer_MazeGenerating();
                CommunicateWithServer_SolveSearchProblem();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
            threads[i].start();
        }

        for (int i = 0; i < threads.length; i++) {
            try{
                threads[i].join();
                System.out.println("thread number "+Thread.currentThread().getId() + " has finished");

            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        //Communicating with servers
        //CommunicateWithServer_MazeGenerating();
        //CommunicateWithServer_SolveSearchProblem();

        //Stopping all servers
        mazeGeneratingServer.stop();
        solveSearchProblemServer.stop();



    }

    private static void CommunicateWithServer_MazeGenerating () {
        try {
              Client client = new Client(InetAddress.getLocalHost(), 5400, new IClientStrategy() {
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
            });
            client.communicateWithServer();
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }


    private static void CommunicateWithServer_SolveSearchProblem () {

        try {
            Client client = new Client(InetAddress.getLocalHost(), 5401, new IClientStrategy() {
                @Override
                public void clientStrategy(InputStream inFromServer, OutputStream outToServer) {
                    try {
                        ObjectOutputStream toServer = new ObjectOutputStream(outToServer);
                        ObjectInputStream fromServer = new ObjectInputStream(inFromServer);
                        toServer.flush();
                        IMazeGenerator mg=new MyMazeGenerator();
                        Configurations configurations= Configurations.getInstance();
                        try (InputStream input = new FileInputStream("src/resources/config.properties")) {

                            // load a properties file
                            try {
                                configurations.getProperties().load(input);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            System.out.println(configurations.getProperties().getProperty("db.mazeGeneratingAlg"));
                            System.out.println(configurations.getProperties().getProperty("db.mazeGeneratingAlg"));
                            if(configurations.getProperties().getProperty("db.mazeGeneratingAlg").equals("Simple"))
                                mg=new SimpleMazeGenerator();
                           else if(configurations.getProperties().getProperty("db.mazeGeneratingAlg").equals("Empty"))
                                mg= new EmptyMazeGenerator();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        //Maze maze = mg.generate(50, 50);
                        int[][] mat = new int[][]{
                                {0,0,0},
                                {0,1,1},
                                {0,0,0},};

                        Maze maze = new Maze(3,3);
                        maze.setMatrix(mat);
                        maze.setGoalPosition(new Position(0,0));
                        maze.setStartPosition(new Position(2,2));
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
            });
            client.communicateWithServer();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }



}