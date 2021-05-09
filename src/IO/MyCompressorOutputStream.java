package IO;

import java.io.IOException;
import java.io.OutputStream;

/**
 * compressing buffer into OutputStream on our way of compressing (making from each series of 8 0s and 1s binary number and encode it to byte)
 */
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
        //writing the rows information
        for (int i = 0; i < b[0] + 1; i++) {
            out.write(b[i]);
        }
        //last 8 or less numbers in array b.
        int lastNumbers = (b.length - b[0] - 1) % 8;
        int i;
        byte[] arr = new byte[8];

        //loop the maze content only, in groups of 8 numbers each time.
        //stops before the 8 (maybe less) last numbers of the maze.
        for (i = b[0] + 1; i < b.length-lastNumbers; i += 8) {
            for (int j = 0; j < 8; j++) {
                arr[j] = b[i + j];
            }
            out.write(WriteToOutBinaryNum(arr));
        }
        if (lastNumbers != 0) {
            //the last index we stopped on in the byte array
            byte[] lastArr = new byte[lastNumbers];
            for (int j = 0; j < lastNumbers; j++) {
                lastArr[j] = b[i + j];
            }
            out.write(WriteToOutBinaryNum(lastArr));
        }
    }

    /**
     * @param arr byte arr that represent a binary number
     * @return the decimal form of the binary number
     */
    private byte WriteToOutBinaryNum (byte[] arr) {
        byte num = 0;
        int decimal =0;
        int j=0;
        for (int i = arr.length - 1; i >= 0; i--) {
            num = (byte)(Math.pow(2, j) * arr[i]);
            j++;
            decimal += num;
        }
        num = (byte)decimal;
        return (byte)unsignedToBytes(num);

    }

    /**
     * @param b bunary number that could be negative
     * @return the number but as unsigned (ignoring the sign bit and use it as msb)
     */
    public static int unsignedToBytes (byte b) {
        return  (b & 0xFF);
    }


}
