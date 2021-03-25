package mazeGenerators;

import mazeGenerators.AMazeGenerator;

/**
 * this class generates a simple maze.
 */
public class SimpleMazeGenerator extends AMazeGenerator {

    /**
     * @param rows - the number of rows in the maze
     * @param cols - the number of cols in the maze
     * @return Maze - the simple maze it created.(the walls are put in the even cells-[0,0], [0,2], [2,2]..)
     */
    @Override
    public Maze generate(int rows, int cols) {
        Maze newSimpleMaze = new Maze(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if ((i % 2) == 0 && (j % 2) == 0) {
                    newSimpleMaze.matrix[i][j] = 1;
                }
            }
        }
        return newSimpleMaze;
    }

}
