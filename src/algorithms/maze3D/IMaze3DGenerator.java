package algorithms.maze3D;

import Errors.LowBoundInput;

/**
 * interface for all the 3D maze generators.
 */
public interface IMaze3DGenerator {

    Maze3D generate (int depth, int rows, int cols) throws LowBoundInput;
    long measureAlgorithmTimeMillis (int depth, int row, int column) throws LowBoundInput;
}
