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


    }
}
