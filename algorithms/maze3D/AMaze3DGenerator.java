package algorithms.maze3D;

/**
 * Abstract class for 3D maze generators.
 * All the generators will have the same "MeasureAlgorithmTimeMillis" function.
 * Each one will implement the "Generate" function in it's own way.
 */
public abstract class AMaze3DGenerator implements IMazeGenerator3D{
    /**
     * checks the time it takes to build a 3D maze.
     * @param depth - the depth of the 3D maze
     * @param rows - the number of rows in the maze
     * @param cols - the number of columns in the maze
     * @return - the time it took to generate the maze(in millis)
     */
    @Override
    public long measureAlgorithmTimeMillis (int depth, int rows, int cols) {
        long startTime = System.currentTimeMillis();
        this.generate(depth, rows, cols);
        long finishTime = System.currentTimeMillis();
        return finishTime - startTime;
    }

    /**
     * an abstract function - builds and returns a 3D maze.
     */
    @Override
    public abstract Maze3D generate(int depth, int row, int column);
}
