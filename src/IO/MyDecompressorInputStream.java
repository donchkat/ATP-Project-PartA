package IO;

import java.io.*;
import java.util.ArrayList;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Decompressing buffer into input stream
 */
public class MyDecompressorInputStream extends InputStream {
    InputStream in;

    public MyDecompressorInputStream (InputStream input) {
        this.in =  input;
    }

    /**
     * @param buffer  write into the buffer from the input
     * @return 0 when we finished the decompressing
     * @throws IOException
     */
    @Override
    public int read (byte[] buffer) throws IOException {
        buffer[0] = (byte) in.read();
        int j;
        int index=0;
        //inserting first the raws information into the buffer
        for (j = 1; j < buffer[0] + 1; j++) {
            buffer[j] = (byte) in.read();
        }
        index=j;
        byte [] decimals = readFromStreamToArrayList(); //moving all the decimals to array list
        int i;
        int bitsNumber=8; //the number of the bits in the binary number
        //for each decimal in the array list we translate it to binary 8 bit number
        for (i = 0; i < decimals.length-1; i++) {
            ToByteNumber(decimals[i], buffer, index, bitsNumber);
            index+=8;
        }

        //in case we have less then 8 numbers at the last loop
        int loopLength = calcLastLoopLength(buffer); //calculate the quantity of the last numbers(quantity<8)
        if(loopLength !=0){
            bitsNumber = loopLength;
            ToByteNumber(decimals[i], buffer, index, bitsNumber);
        }
        else //if there is no such group with less then 8 numbers, we just add the last number normally
        {
            ToByteNumber(decimals[i], buffer, index, bitsNumber);
        }
        return 0;
    }

    public static int unsignedToBytes (byte b) {
        return (b & 0xFF);
    }


    /**
     * inserts into the buffer the decimal number in binary shape
     * @param decimal - the number we translate to binary
     * @param buffer - the buffer we insert to
     * @param index - the current index we at in the buffer so far
     * @param bitsNumber - number of bits of the binary representation we want
     *                   (usually its 8, only in the last loop it can change to less then 8)
     */
    private void ToByteNumber (byte decimal, byte[] buffer, int index, int bitsNumber) {
        int num = unsignedToBytes(decimal);
        for (int i = bitsNumber - 1; i >= 0; i--) {
            if (num > 0) {
                buffer[index + i] = (byte) (num % 2);
                num /= 2;
                //index++;
            } else {
                buffer[index + i] = 0;
                //index++;
            }
        }

    }


    /**
     * when we divide the numbers of the maze into groups of 8, we may have a rest.
     * here we calculate the rest,
     * so we can know how to compress and decompress the rest correctly.
     * @param buffer - the buffer we insert to
     * @return the quantity of the numbers we have at the end of the maze and they are less then 8
     */
    private int calcLastLoopLength (byte[] buffer) {
        int numOfCells = buffer.length - buffer[0] - 1;
        int lastLoopLength = numOfCells % 8;
        return lastLoopLength;
    }


    /**
     * reads all the datat from input stream into byte array
     * @throws IOException - exception
     */
    private byte[] readFromStreamToArrayList() throws IOException {
        byte [] bytes = in.readAllBytes();
        return bytes;
    }

    @Override
    public int read () throws IOException {

        return 0;
    }
}
