package mazeGenerators;

import mazeGenerators.AMazeGenerator;
import mazeGenerators.Maze;

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
        while(isCellHasUnvisitedNeighbs(currPos,newMaze)){}

    }

    private boolean isCellHasUnvisitedNeighbs(Position currPos,Maze maze) {
       boolean[] neighbs= new boolean[4];
       //for(int i=0;i<4;i++)neighbs[i]=
    return  true;
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



