package mazeGenerators;

import mazeGenerators.AMazeGenerator;
import mazeGenerators.Maze;

import java.lang.reflect.Array;

/**
 * this class generates an empty maze(full of 0's)
 */
public class EmptyMazeGenerator extends AMazeGenerator {
    @Override
    public Maze generate(int rows, int cols) {
        Maze newEmptyMaze = new Maze(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                newEmptyMaze.matrix[i][j] = 0;
            }
        }

        return newEmptyMaze;
    }
}
