package IO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MyDecompressorInputStream extends InputStream {
    InputStream in;

    public MyDecompressorInputStream (InputStream input) {
        this.in = input;
    }

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
        ArrayList<Integer> decimalArr = new ArrayList<>();
        readFromStreamToArrayList(decimalArr); //moving all the decimals to array list
        int i;
        int bitsNumber=8; //the number of the bits in the binary number

        //for each decimal in the array list we translate it to binary 8 bit number
        for (i = 0; i < decimalArr.size()-1; i++) {
            ToByteNumber(decimalArr.get(i), buffer, index, bitsNumber);
        }

        //in case we have less then 8 numbers at the last loop
        int loopLength = calcLastLoopLength(buffer); //calculate the quantity of the last numbers(quantity<8)
        if(loopLength !=0){
            bitsNumber = loopLength;
            ToByteNumber(decimalArr.get(i), buffer, index, bitsNumber);
        }
        else //if there is no such group with less then 8 numbers, we just add the last number normally
        {
            ToByteNumber(decimalArr.get(i), buffer, index, bitsNumber);
        }
        return 0;
    }


    /**
     * inserts into the buffer the decimal number in binary shape
     * @param decimal - the number we translate to binary
     * @param buffer - the buffer we insert to
     * @param index - the current index we at in the buffer so far
     * @param bitsNumber - number of bits of the binary representation we want
     *                   (usually its 8, only in the last loop it can change to less then 8)
     */
    private void ToByteNumber (int decimal, byte[] buffer, int index, int bitsNumber) {

        for (int i = bitsNumber - 1; i >= 0; i--) {
            if (decimal > 0) {
                buffer[index + i] = (byte) (decimal % 2);
                decimal /= 2;
                index++;
            } else
                buffer[index + i] = 0;
            index++;
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
     * we read the input and add it to the array list.
     * it helps us to know when we get the last number,
     * which is probably the shortest binary number(in bits)
     * @param decimalArr - the array list we fill with the decimals we got from the input stream
     * @throws IOException - exception
     */
    private void readFromStreamToArrayList(ArrayList<Integer> decimalArr) throws IOException {
        int decimal = in.read();
        while (decimal != -1) {
            decimalArr.add(decimal);
            decimal = in.read();
        }
    }

    @Override
    public int read () throws IOException {

        return 0;
    }
}
