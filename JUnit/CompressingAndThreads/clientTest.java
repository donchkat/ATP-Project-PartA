package CompressingAndThreads;

import Client.*;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class clientTest {
    public static void main (String[] args) {
        try{
            Client client = new Client(InetAddress.getLocalHost(), 5400, new ClientStrategyGenerateMaze());
            client.communicateWithServer();
        } catch (UnknownHostException e){
            e.printStackTrace();
        }
    }
}
