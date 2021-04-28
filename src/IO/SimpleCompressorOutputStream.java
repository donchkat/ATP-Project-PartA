package IO;

import jdk.jfr.Unsigned;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class SimpleCompressorOutputStream extends OutputStream {
    private OutputStream out;


    public SimpleCompressorOutputStream(OutputStream out) {
        this.out = out;
    }
    /**
     * the function counts sequels of ones and zeros in the maze
     * @param b -buffer of sequences of 0,1 that represent a sequel of 1s and 0s in the maze
     *
     */
    @Override
    public void write(byte[] b) throws IOException {
        byte currByte=b[0];
        byte counter=0;


        for (int i = 0; i <b.length ; i++) {
            if(currByte==0&&b[i]==1||currByte==1&&b[i]==0){
                out.write(counter);
                counter=1;
                currByte=b[i];
            }
            else{
                counter++;
            }
        }


    }
    @Override
    public void write(int b) throws IOException {

    }
}
