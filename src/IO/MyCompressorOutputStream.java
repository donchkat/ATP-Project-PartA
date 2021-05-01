package IO;

import java.io.IOException;
import java.io.OutputStream;

public class MyCompressorOutputStream extends OutputStream {
    private OutputStream out;

    public MyCompressorOutputStream (OutputStream out) {
        this.out = out;
    }

    @Override
    public void write (int b) throws IOException {

    }

    /**
     * the function counts sequels of ones and zeros in the maze
     *
     * @param b -buffer of sequences of 0,1 that represent a sequel of 1s and 0s in the maze
     */
    @Override
    public void write (byte[] b) throws IOException {

    }


}
