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
        newSimpleMaze.matrix = CreateBounds(newSimpleMaze);//take care of maze bounds
        newSimpleMaze.matrix = CreateWays(newSimpleMaze); // fill randomly the inner part
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
        int i = rndIndex.nextInt(newSimpleMaze.rows);
        int j = rndIndex.nextInt(newSimpleMaze.cols);
        while (newSimpleMaze.matrix[i][j] != 0) {
            for (int k = 0; k < newSimpleMaze.rows; k++)
                for (int n = 0; n < newSimpleMaze.cols; n++) {
                    newSimpleMaze.matrix[i][n] = 0;
                    newSimpleMaze.matrix[k][j] = 0;
                }
            i = rndIndex.nextInt(newSimpleMaze.rows);
            j = rndIndex.nextInt(newSimpleMaze.cols);
            if (i == j)
                break;
        }
        return newSimpleMaze.matrix;
    }


    /**
     *
     * @param newSimpleMaze - the maze we are filling
     * @return the same maze with 0's on the frame and 1's inside
     */
    private int[][] CreateBounds (Maze newSimpleMaze) {
        for (int i = 0; i < newSimpleMaze.rows; i++) {
            for (int j = 0; j < newSimpleMaze.cols; j++) {
                if (i == 0 || j == 0 || i == (newSimpleMaze.rows - 1) || j == (newSimpleMaze.cols - 1))
                    newSimpleMaze.matrix[i][j] = 0;
                else
                    newSimpleMaze.matrix[i][j] = 1;
            }
        }
        return newSimpleMaze.matrix;
    }
}
