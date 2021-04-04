package algorithms.mazeGenerators;

public abstract class AMazeGenerator implements IMazeGenerator {
    //protected Maze myMaze; -NOT SURE WE NEED THIS
    //do we need a constructor????

    @Override
    public abstract Maze generate (int rows, int cols);

    /**
     * @param rows - num of rows in the maze
     * @param cols - num of columns in the maze
     * @return the time it took to build the maze
     */
    @Override
    public long measureAlgorithmTimeMillis (int rows, int cols) {
        long startTime = System.currentTimeMillis();
        this.generate(rows, cols);
        long finishTime = System.currentTimeMillis();
        return finishTime - startTime;
    }
}
