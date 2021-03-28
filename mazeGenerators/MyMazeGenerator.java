package mazeGenerators;

import mazeGenerators.AMazeGenerator;
import mazeGenerators.Maze;

import java.util.Random;

/**
 *
 */
public class MyMazeGenerator extends AMazeGenerator {
    @Override
    public Maze generate(int rows, int cols) {
        Maze newMaze=new Maze(rows,cols);
        newMaze.matrix=onesMatrix(rows,cols);
        DFSRndMazeGen(newMaze);
        return newMaze;
    }

    private void DFSRndMazeGen(Maze newMaze) {
    Position currPos= newMaze.getStartPosition();
    RecCreateMat(newMaze,currPos);
    }

    private void RecCreateMat(Maze newMaze, Position currPos) {
        if(currPos.equals(newMaze.getGoalPosition()))
            return;
        newMaze.matrix[currPos.getRowIndex()][currPos.getColumnIndex()]=0;
        Random rnd=new Random();
        int num= rnd.nextInt(2);
        while(!currPos.equals(newMaze.getGoalPosition())){
       if(num==0)
           op1(newMaze,currPos);
       else if (num==1)
           op2(newMaze,currPos);


        }

    }

    private void op4(Maze newMaze, Position currPos) {
           if (!isOutOfRange(newMaze.matrix,currPos.getRowIndex()+1,currPos.getColumnIndex()-1)&&
                newMaze.matrix[currPos.getRowIndex()+1][currPos.getColumnIndex()]==1)
            RecCreateMat(newMaze,new Position(currPos.getRowIndex()+1,currPos.getColumnIndex()));
          else  if (!isOutOfRange(newMaze.matrix,currPos.getRowIndex()-1,currPos.getColumnIndex())&&
                newMaze.matrix[currPos.getRowIndex()-1][currPos.getColumnIndex()]==1)
            RecCreateMat(newMaze,new Position(currPos.getRowIndex()-1,currPos.getColumnIndex()));
          else    if(!isOutOfRange(newMaze.matrix,currPos.getRowIndex(),currPos.getColumnIndex()+1)&&
                newMaze.matrix[currPos.getRowIndex()][currPos.getColumnIndex()+1]==1)
            RecCreateMat(newMaze,new Position(currPos.getRowIndex(),currPos.getColumnIndex()+1));
        else if (!isOutOfRange(newMaze.matrix,currPos.getRowIndex(),currPos.getColumnIndex()-1)&&
                newMaze.matrix[currPos.getRowIndex()][currPos.getColumnIndex()-1]==1)
            RecCreateMat(newMaze,new Position(currPos.getRowIndex(),currPos.getColumnIndex()-1));

    }

    private void op3(Maze newMaze, Position currPos) {
        if (!isOutOfRange(newMaze.matrix,currPos.getRowIndex(),currPos.getColumnIndex()-1)&&
                newMaze.matrix[currPos.getRowIndex()][currPos.getColumnIndex()-1]==1)
            RecCreateMat(newMaze,new Position(currPos.getRowIndex(),currPos.getColumnIndex()-1));

        else if (!isOutOfRange(newMaze.matrix,currPos.getRowIndex()+1,currPos.getColumnIndex()-1)&&
                newMaze.matrix[currPos.getRowIndex()+1][currPos.getColumnIndex()]==1)
            RecCreateMat(newMaze,new Position(currPos.getRowIndex()+1,currPos.getColumnIndex()));
        else    if(!isOutOfRange(newMaze.matrix,currPos.getRowIndex(),currPos.getColumnIndex()+1)&&
                newMaze.matrix[currPos.getRowIndex()][currPos.getColumnIndex()+1]==1)
            RecCreateMat(newMaze,new Position(currPos.getRowIndex(),currPos.getColumnIndex()+1));
        else if    (!isOutOfRange(newMaze.matrix,currPos.getRowIndex()-1,currPos.getColumnIndex())&&
                newMaze.matrix[currPos.getRowIndex()-1][currPos.getColumnIndex()]==1)
        RecCreateMat(newMaze,new Position(currPos.getRowIndex()-1,currPos.getColumnIndex()));

    }

    private void op2(Maze newMaze, Position currPos) {
          if (!isOutOfRange(newMaze.matrix,currPos.getRowIndex()-1,currPos.getColumnIndex()))
            RecCreateMat(newMaze,new Position(currPos.getRowIndex()-1,currPos.getColumnIndex()));
        else if (!isOutOfRange(newMaze.matrix,currPos.getRowIndex()+1,currPos.getColumnIndex()-1))
            RecCreateMat(newMaze,new Position(currPos.getRowIndex()+1,currPos.getColumnIndex()));
         else    if(!isOutOfRange(newMaze.matrix,currPos.getRowIndex(),currPos.getColumnIndex()+1))
            RecCreateMat(newMaze,new Position(currPos.getRowIndex(),currPos.getColumnIndex()+1));
        else if (!isOutOfRange(newMaze.matrix,currPos.getRowIndex(),currPos.getColumnIndex()-1))
            RecCreateMat(newMaze,new Position(currPos.getRowIndex(),currPos.getColumnIndex()-1));

    }

    private void op1(Maze newMaze,Position currPos) {
        if(!isOutOfRange(newMaze.matrix,currPos.getRowIndex(),currPos.getColumnIndex()+1))
            RecCreateMat(newMaze,new Position(currPos.getRowIndex(),currPos.getColumnIndex()+1));
        else if (!isOutOfRange(newMaze.matrix,currPos.getRowIndex(),currPos.getColumnIndex()-1))
            RecCreateMat(newMaze,new Position(currPos.getRowIndex(),currPos.getColumnIndex()-1));
        else if (!isOutOfRange(newMaze.matrix,currPos.getRowIndex()-1,currPos.getColumnIndex()))
            RecCreateMat(newMaze,new Position(currPos.getRowIndex()-1,currPos.getColumnIndex()));
        else if (!isOutOfRange(newMaze.matrix,currPos.getRowIndex()+1,currPos.getColumnIndex()-1))
            RecCreateMat(newMaze,new Position(currPos.getRowIndex()+1,currPos.getColumnIndex()));

    }


    private boolean isCellHasUnvisitedNeighbs(Position currPos,Maze maze) {
       //boolean flag=false;
        if(!isOutOfRange(maze.matrix,currPos.getRowIndex(),currPos.getColumnIndex()+1)&&
                maze.matrix[currPos.getRowIndex()][currPos.getColumnIndex()+1]==1)
            return true;
        else if (!isOutOfRange(maze.matrix,currPos.getRowIndex(),currPos.getColumnIndex()-1)&&
                maze.matrix[currPos.getRowIndex()][currPos.getColumnIndex()-1]==1)
            return true;
        else if (!isOutOfRange(maze.matrix,currPos.getRowIndex()-1,currPos.getColumnIndex())&&
                maze.matrix[currPos.getRowIndex()-1][currPos.getColumnIndex()]==1)
            return true;
        else if (!isOutOfRange(maze.matrix,currPos.getRowIndex()+1,currPos.getColumnIndex()-1)&&
                maze.matrix[currPos.getRowIndex()+1][currPos.getColumnIndex()]==1)
            return true;

        return false;


    }

    private boolean isOutOfRange(int[][] mat ,int i, int j){
    if(i<0||j<0||i==mat.length||j==mat[0].length)
        return true;
    return false;

    }

    private int[][] onesMatrix(int rows, int cols) {
        Maze newEmptyMaze = new Maze(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                newEmptyMaze.matrix[i][j] = 1;
            }
        }
     return newEmptyMaze.matrix;
    }


}



