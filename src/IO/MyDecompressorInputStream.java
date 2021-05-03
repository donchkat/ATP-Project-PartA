package IO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream {
    InputStream in;
    public MyDecompressorInputStream (InputStream input) {
        this.in = input;
    }

    @Override
    public int read (byte[] buffer) throws IOException {
        buffer[0] = (byte) in.read();
        int j;
        for (j= 1; j < buffer[0] + 1; j++) {
            buffer[j] = (byte) in.read();
        }

        int decNumber = in.read();
        while (decNumber != -1) {
        ToByteNumber(decNumber,buffer,j);
        decNumber=in.read();
        j+=8;
        }

            return  0;
    }

    private void ToByteNumber(int decNumber, byte[] buffer, int j) {

        for (int i = j-1; i >= 0; i--) {
            if(decNumber>0){
                buffer[j+i]= (byte) (decNumber%2);
                decNumber/=2;
            }
            else
                buffer[j+i]=0;
        }

    }

    @Override
    public int read () throws IOException {

        return 0;
    }
}
