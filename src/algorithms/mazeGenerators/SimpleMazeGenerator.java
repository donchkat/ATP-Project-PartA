package algorithms.mazeGenerators;

import Errors.LowBoundInput;

import java.util.Random;

/**
 * this class generates a simple maze.
 */
public class SimpleMazeGenerator extends AMazeGenerator {

    /**
     * generates a simple random maze. The frame of the maze is always 0's
     * so it always will have a solution - the inside square is filled randomly with 1's.
     *
     * @param rows - the number of rows in the maze
     * @param cols - the number of cols in the maze
     * @return Maze - the simple maze it created.
     */
    @Override
    public Maze generate (int rows, int cols) throws LowBoundInput {
        Maze newSimpleMaze = new Maze(rows, cols);
        newSimpleMaze.setMatrix(CreateBounds(newSimpleMaze)); //take care of maze bounds
        newSimpleMaze.setMatrix(CreateWays(newSimpleMaze)); // fill randomly the inner part
        return newSimpleMaze;
    }

    /**
     * chooses randomly a position and until it's not contains 0,
     * we fill the inner part with columns and rows of 0's.
     *
     * @param newSimpleMaze - the maze we are filling
     * @return the same maze but randomly filled with 0's in the inner part.
     */
    private int[][] CreateWays (Maze newSimpleMaze) {
        Random rndIndex = new Random();
        int i = rndIndex.nextInt(newSimpleMaze.getRows());
        int j = rndIndex.nextInt(newSimpleMaze.getCols());
        while (newSimpleMaze.getMatrix()[i][j] != 0) {
            for (int k = 0; k < newSimpleMaze.getRows(); k++)
                for (int n = 0; n < newSimpleMaze.getCols(); n++) {
                    newSimpleMaze.setCellInMatrix(i,n,0);
                    newSimpleMaze.setCellInMatrix(k,j,0);
                }
            i = rndIndex.nextInt(newSimpleMaze.getRows());
            j = rndIndex.nextInt(newSimpleMaze.getCols());
            if (i == j)
                break;
        }
        return newSimpleMaze.getMatrix();
    }


    /**
     *
     * @param newSimpleMaze - the maze we are filling
     * @return the same maze with 0's on the frame and 1's inside
     */
    private int[][] CreateBounds (Maze newSimpleMaze) {
        for (int i = 0; i < newSimpleMaze.getRows(); i++) {
            for (int j = 0; j < newSimpleMaze.getCols(); j++) {
                if (i == 0 || j == 0 || i == (newSimpleMaze.getRows() - 1) || j == (newSimpleMaze.getCols() - 1))
                    newSimpleMaze.setCellInMatrix(i,j,0);
                else
                    newSimpleMaze.setCellInMatrix(i,j,1);
            }
        }
        return newSimpleMaze.getMatrix();
    }
}
