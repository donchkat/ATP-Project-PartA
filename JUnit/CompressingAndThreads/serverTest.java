package CompressingAndThreads;

import Server.*;

public class serverTest {
    public static void main (String[] args) {
        System.out.println("This is the mainServer class");
        Server server = new Server(5400, 1000, new ServerStrategyGenerateMaze());
        server.start();
        //new Thread(()->{
           // server.start();
       // }).start();
        server.stop();
    }
}
