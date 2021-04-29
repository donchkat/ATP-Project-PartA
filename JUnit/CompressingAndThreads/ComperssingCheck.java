package CompressingAndThreads;

import Errors.LowBoundInput;
import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

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

    public static void main(String[] args) throws Exception {
       CheckMazeCompressMethods();



          byte FORWARD = 0x1; // 00000001
          byte LEFT     =0x2; // 00000010
          byte RIGHT    =0x4; // 00000100
        System.out.println(FORWARD);
        System.out.println(LEFT);
        LEFT= (byte) (LEFT|FORWARD|RIGHT);
        System.out.println(LEFT);


    }

    private static void CheckMazeCompressMethods() throws Exception {
        for (int i = 3; i < 5; i++) {
            for (int j = 3; j < 5; j++) {
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
                IMazeGenerator mg1 = new MyMazeGenerator();
                Maze maze1 = mg1.generate(i, j);
                maze1.print();
                System.out.println("--------------------------------------------------");
                Maze newMaze= new Maze(maze1.toByteArray());
                newMaze.print();
            }

        }


    }
}
