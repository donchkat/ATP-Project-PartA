package CompressingAndThreads;

import IO.MyCompressorOutputStream;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;

public class ComperssingCheck {

    public static void PrintOutputStream(){
        byte[] arr=new byte[10];

       // ObjectInputStream objectInputStream=new ObjectInputStream();
        for (int i = 0; i < arr.length; i++) {
            if(i%2==0)
                arr[i]=(byte) 0;
            else
                arr[i]=(byte)1;
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(arr[i]);
        }

        //OutputStream out= new  ;
        //MyCompressorOutputStream myCompressorOutputStream=new MyCompressorOutputStream(arr);

    }

    public static void main(String[] args) throws IOException {
        /*
        byte[] bits= new byte[9];
         int x;
         for (int i = 0; i < 9; i++) {
            x=(int)Math.pow(2,i);
            bits[i]=(byte) x;
         }
        for (int i = 1; i < 9; i++) {
            System.out.println(bits[i-1]|bits[i]);
        }*/
          byte FORWARD = 0x1; // 00000001
          byte LEFT     =0x2; // 00000010
          byte RIGHT    =0x4; // 00000100
        System.out.println(FORWARD);
        System.out.println(LEFT);
        LEFT= (byte) (LEFT|FORWARD|RIGHT);
        System.out.println(LEFT);


    }
}
