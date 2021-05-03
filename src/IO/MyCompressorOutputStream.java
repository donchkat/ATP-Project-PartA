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
        byte currByte = b[b[0] + 1];

        for (int i = 0; i < b[0] + 1; i++) {
            out.write(b[i]);
        }
        int i;
        byte[] arr=new byte[8];
        for (i = b[0] + 1; i < b.length; i+=8) {
            for (int j = 0; j < 8; j++) {
                arr[j]=b[i+j];
            }
           byte tmp= WriteToOutBinaryNum(arr);
           out.write(tmp);
        }
        out.write((b.length-b[0]-1)%8);
        for (int j = (b.length-b[0]-1)%8-1; j >=0; j++) {
            out.write((byte) Math.pow(2,j)*b[i+j]);
        }
    }

    private byte WriteToOutBinaryNum(byte[] arr) {
     byte num=0;
        for (int i = arr.length-1; i >= 0; i--) {
            num+=(byte) Math.pow(2,i)*arr[i];
        }
     return num;

    }


}
