package algorithms.mazeGenerators;

import Errors.LowBoundInput;
import Errors.NullError;

/**
 * abstract class for a maze generators
 */
public abstract class AMazeGenerator implements IMazeGenerator {


    @Override
    public abstract Maze generate (int rows, int cols) throws LowBoundInput, NullError;

    /**
     * @param rows - num of rows in the maze
     * @param cols - num of columns in the maze
     * @return the time it took to build the maze
     */
    @Override
    public long measureAlgorithmTimeMillis (int rows, int cols) throws LowBoundInput, NullError {
        long startTime = System.currentTimeMillis();
        this.generate(rows, cols);
        long finishTime = System.currentTimeMillis();
        return finishTime - startTime;
    }
}
