package algorithms.mazeGenerators;

/**
 * this class generates an empty maze(full of 0's)
 */
public class EmptyMazeGenerator extends AMazeGenerator {
    /**
     * @param rows - number of rows in the maze
     * @param cols - numbr of clumns in the maze
     * @return empty maze - without walls.
     */
    @Override
    public Maze generate (int rows, int cols) {
        Maze newEmptyMaze = new Maze(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                newEmptyMaze.matrix[i][j] = 0;
            }
        }
        return newEmptyMaze;
    }
}
