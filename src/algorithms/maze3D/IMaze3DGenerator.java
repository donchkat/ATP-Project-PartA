package algorithms.maze3D;

/**
 * interface for all the 3D maze generators.
 */
public interface IMaze3DGenerator {

    Maze3D generate (int depth, int rows, int cols);
    long measureAlgorithmTimeMillis (int depth, int row, int column);
}
