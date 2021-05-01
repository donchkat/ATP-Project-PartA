package IO;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class SimpleDecompressorInputStream extends InputStream {
    InputStream in;


    public SimpleDecompressorInputStream (InputStream inputStream) {
        this.in = inputStream;
    }

    public int read (byte[] buffer) throws IOException {
        int number;
        int i;
        int indexOfWhiles = 0;
        int indexOfFors = 0;
        buffer[0] = (byte) in.read();
        for (int j = 1; j < buffer[0] + 1; j++) {
            buffer[j] = (byte) in.read();
        }
        int numOfTimes = in.read();
        while (numOfTimes != -1) {
            System.out.println(numOfTimes);
            if (indexOfWhiles % 2 == 0)
                number = 0;
            else
                number = 1;
            for (i = 0; i < numOfTimes; i++) {
                buffer[i + indexOfFors + buffer[0] + 1] = (byte) number;
            }
            indexOfFors += i;
            numOfTimes = in.read();
            indexOfWhiles++;
        }
        return 0;
    }

    @Override
    public int read () throws IOException {
        return 0;
    }


}