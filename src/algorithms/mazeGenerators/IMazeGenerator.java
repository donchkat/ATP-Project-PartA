package algorithms.mazeGenerators;

import Errors.LowBoundInput;
import Errors.NullError;

public interface IMazeGenerator {
     Maze generate (int rows, int cols) throws LowBoundInput, NullError;

     long measureAlgorithmTimeMillis (int rows, int cols) throws LowBoundInput, NullError;

}
