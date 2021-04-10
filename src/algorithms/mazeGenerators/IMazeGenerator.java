package algorithms.mazeGenerators;

import Errors.LowBoundInput;
import Errors.NullError;

/**
 * interface that defines maze generators.
 */
public interface IMazeGenerator {
     Maze generate (int rows, int cols) throws LowBoundInput, NullError;

     long measureAlgorithmTimeMillis (int rows, int cols) throws LowBoundInput, NullError;

}
