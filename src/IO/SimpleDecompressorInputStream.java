package IO;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class SimpleDecompressorInputStream extends InputStream {
    InputStream in;
    OutputStream outDecomp;


    public SimpleDecompressorInputStream (InputStream inputStream) {
        this.in = inputStream;
    }

    public int read(byte[] buffer) throws IOException {
        int number;
        int i;
        int indexOfWhiles=0;
        int indexOfFors=0;
        int numOfTimes = in.read();
        while ( numOfTimes != -1) {
            if(indexOfWhiles%2==0)
                number=0;
            else
                number=1;
            for (i = 0; i < numOfTimes; i++) {
                buffer[i+indexOfFors]=(byte)number;
                outDecomp.write(number);
            }
            indexOfFors+=i;
            numOfTimes=in.read();
            indexOfWhiles++;
        }
        return 0;
    }

    @Override
    public int read () throws IOException {
       return 0;
    }


}