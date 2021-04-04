package algorithms.mazeGenerators;

import java.util.Random;

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
    public Maze generate (int rows, int cols) {
        Maze newSimpleMaze = new Maze(rows, cols);
        newSimpleMaze.matrix = CreateBounds(newSimpleMaze);//take care of bounderies
        newSimpleMaze.matrix = CreateWays(newSimpleMaze);
        return newSimpleMaze;
    }

    private int[][] CreateWays (Maze newSimpleMaze) {
        Random rndIndex = new Random();
        //newSimpleMaze.Print();
        int i = rndIndex.nextInt(newSimpleMaze.rows);
        int j = rndIndex.nextInt(newSimpleMaze.cols);
        while (newSimpleMaze.matrix[i][j] != 0) {
            for (int k = 0; k < newSimpleMaze.rows; k++)
                for (int n = 0; n < newSimpleMaze.cols; n++) {
                    newSimpleMaze.matrix[i][n] = 0;
                    newSimpleMaze.matrix[k][j] = 0;
                }
            i = rndIndex.nextInt(newSimpleMaze.rows);
            j = rndIndex.nextInt(newSimpleMaze.cols);
            if (i == j)
                break;

        }
        return newSimpleMaze.matrix;
    }


    private int[][] CreateBounds (Maze newSimpleMaze) {
        /**
         * @param rows - the number of rows in the maze
         * @param cols - the number of cols in the maze
         * @param matrix- empty matrix of ints
         * @return Maze - matrix with ones at the limits and full of zeros otherwise
         * */
        for (int i = 0; i < newSimpleMaze.rows; i++) {
            for (int j = 0; j < newSimpleMaze.cols; j++) {
                if (i == 0 || j == 0 || i == (newSimpleMaze.rows - 1) || j == (newSimpleMaze.cols - 1))
                    newSimpleMaze.matrix[i][j] = 0;
                else
                    newSimpleMaze.matrix[i][j] = 1;
            }
        }
        newSimpleMaze.matrix[0][0] = 0;
        newSimpleMaze.matrix[newSimpleMaze.rows - 1][newSimpleMaze.cols - 1] = 0;
        return newSimpleMaze.matrix;
    }


/**
 private boolean indexInGoalOrStart(int i, int j,Maze maze) {
 maze.Print();
 if(maze.getGoalPosition().getColumnIndex()==j||
 maze.getStartPosition().getColumnIndex()==j||
 maze.getGoalPosition().getColumnIndex()==i||
 maze.getStartPosition().getColumnIndex()==i)
 return true;
 return false;
 }
 **/
}
