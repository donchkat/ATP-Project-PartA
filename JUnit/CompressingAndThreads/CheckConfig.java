package CompressingAndThreads;

import Server.Configurations;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class CheckConfig {
public static void main(String[] args) {
        Configurations configurations=Configurations.getInstance();
        try (InputStream input = new FileInputStream("src/resources/config.properties")) {

            // load a properties file
            try {
                configurations.getProperties().load(input);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // get the property value and print it out
            System.out.println(configurations.getProperties().getProperty("db.numOfThreads"));
            System.out.println(configurations.getProperties().getProperty("db.mazeGeneratingAlg"));
            System.out.println(configurations.getProperties().getProperty("db.mazeSearchingAlg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}

