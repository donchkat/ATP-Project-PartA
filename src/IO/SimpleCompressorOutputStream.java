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
        byte currByte=b[b[0]+1];
        int counter=0;
        for (int i = 0; i < b[0]+1; i++) {
            out.write(b[i]);
        }


        for (int i = b[0]+1; i <b.length ; i++) {
            if(currByte==0&&b[i]==1||currByte==1&&b[i]==0){
                if(counter<=255) {
                    out.write((byte)counter);
                }
                else{
                 OutOfByteSize(counter);
                }
                counter=1;
                currByte=b[i];
            }
            else{
                counter++;
            }
        }


    }

    private void OutOfByteSize(int counter) throws IOException {
        while (counter > 0) {
            if (counter >= 255) {
                out.write(255);
                out.write(0);
            }
            else
                out.write((byte)counter);
            counter -= 255;
        }

    }

    @Override
    public void write(int b) throws IOException {

    }
}
