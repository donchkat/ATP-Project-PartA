package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class Server {
    private int port;
    private int listeningIntervalMS;
    private IServerStrategy strategy;
    private volatile boolean stop;
    private ExecutorService threadPool; // Thread pool


    public Server (int port, int listeningIntervalMS, IServerStrategy strategy) {
        this.port = port;
        this.listeningIntervalMS = listeningIntervalMS;
        this.strategy = strategy;
        this.threadPool = Executors.newFixedThreadPool(Configurations.getInstance().getThreadPoolSize());
    }

    public void start () {
        new Thread(()->{
            runServer();
        }).start();
    }

    /**
     * creating new socket for each client and handling it with the strategies
     */
    private void runServer(){
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listeningIntervalMS);
            System.out.println("Server socket was created.");

            while (!stop) {
                try {
                    //waiting to get a task from client
                    Socket clientSocket = serverSocket.accept();
                    // Insert the new task into the thread pool:
                    System.out.println("Server got a task from a client!");
                    threadPool.submit(() -> {
                        //System.out.println("thread was created");
                        handleClient(clientSocket);
                    });
                    //handleClient(clientSocket);


                } catch (SocketTimeoutException e) {
                    System.out.println("Socket out of time..");
                }
            }
            serverSocket.close();
            threadPool.shutdownNow(); // do not allow any new tasks into the thread pool, and also interrupts all running threads (do not terminate the threads, so if they do not handle interrupts properly, they could never stop...)
        } catch (IOException e) {
            System.out.println("Something happened while connecting to server socket.");
        }
    }

    /**
     * @param clientSocket handeling the client call for the server with the use of strategy
     */
    private void handleClient (Socket clientSocket) {
        try {
            //System.out.println("starting strategy");
            strategy.ServerStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
            clientSocket.close();
        } catch (IOException e) {
            System.out.println("Something happened while handling client..");
        }
    }

    /**
     * we use this method when we finished to take care of all the clients
     */
    public void stop () {
        stop = true;
    }
}
