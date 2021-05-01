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
        // initialize a new fixed thread pool with 2 threads: //WHY ONLY 2?
        this.threadPool = Executors.newFixedThreadPool(2);
    }

    public void start () {
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
                        handleClient(clientSocket);
                    });


                } catch (SocketTimeoutException e) {
                    System.out.println("Socket out of time..");
                }
            }
            serverSocket.close();
            //threadPool.shutdown(); // do not allow any new tasks into the thread pool (not doing anything to the current tasks and running threads)
            threadPool.shutdownNow(); // do not allow any new tasks into the thread pool, and also interrupts all running threads (do not terminate the threads, so if they do not handle interrupts properly, they could never stop...)
        } catch (IOException e) {
            System.out.println("Something happened while connecting to server socket.");
        }
    }

    private void handleClient (Socket clientSocket) {
        try {
            strategy.applyStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
            clientSocket.close();
        } catch (IOException e) {
            System.out.println("Something happened while handling client..");
        }
    }

    public void stop () {
        stop = true;
    }
}
