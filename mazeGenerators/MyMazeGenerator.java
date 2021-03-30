package mazeGenerators;

import mazeGenerators.AMazeGenerator;
import mazeGenerators.Maze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 */
public class MyMazeGenerator extends AMazeGenerator {
    @Override
    public Maze generate (int rows, int cols) {
        Maze newMaze = new Maze(rows, cols);
        newMaze.matrix = onesMatrix(rows, cols);
        //get random row and column numbers
        //Random rand = new Random();
       // int r = randRowIndex(rand, rows);
        //int c = randColIndex(rand, cols);
        //start generate from this cell
        //Position currPos = new Position(r, c);
        //newMaze.matrix[r][c] = 0;
        newMaze.matrix[0][0] = 0;
        newMaze.matrix[rows-1][cols-1] = 0;
        Position currPos = new Position(0, 0);
        recGenerate(newMaze.matrix, currPos);
        return newMaze;
    }

    /**NOT SURE WE NEED THIS - DONT DELETE YET
     * @param rand - Random type - that helps us to get random column number
     * @param cols - number of columns in our maze
     * @return random column number [int]
     */
    private int randColIndex (Random rand, int cols) {
        int c = rand.nextInt(cols);
        while (c % 2 == 0) {
            c = rand.nextInt(cols);
        }
        return c;
    }

    /**NOT SURE WE NEED THIS - DONT DELETE YET
     * @param rand - Random type - that helps us to get random row number
     * @param rows - number of rows in our maze
     * @return random row number [int]
     */
    private int randRowIndex (Random rand, int rows) {
        int r = rand.nextInt(rows);
        while (r % 2 == 0) {
            r = rand.nextInt(rows);
        }
        return r;
    }

    /**
     * @param matrix  - the 2D array we generate our maze on
     * @param currPos - the current position in the maze.
     */
    private void recGenerate (int[][] matrix, Position currPos) {

        //random directions
        Integer[] ranDirs = randomDirections(4);
        int r = currPos.getRowIndex();
        int c = currPos.getColumnIndex();
        //check each direction
        for (Integer ranDir : ranDirs) {
            switch (ranDir) {

                case 1: //UP
                    //index out of range
                    if (r - 2 <= 0)
                        continue;
                    if (matrix[r - 2][c] != 0) {
                        matrix[r - 2][c] = 0;
                        matrix[r - 1][c] = 0;
                        currPos.setRowIndex(r - 2);
                        currPos.setColumnIndex(c);
                        recGenerate(matrix, currPos);
                    }
                    break;
                case 2: //RIGHT
                    if (c + 2 >= matrix[0].length - 1)
                        continue;
                    if (matrix[r][c + 2] != 0) {
                        matrix[r][c + 2] = 0;
                        matrix[r][c + 1] = 0;
                        currPos.setRowIndex(r);
                        currPos.setColumnIndex(c + 2);
                        recGenerate(matrix, currPos);
                    }
                    break;
                case 3: //LEFT
                    if (c - 2 <= 0)
                        continue;
                    if (matrix[r][c - 2] != 0) {
                        matrix[r][c - 2] = 0;
                        matrix[r][c - 1] = 0;
                        currPos.setRowIndex(r);
                        currPos.setColumnIndex(c - 2);
                        recGenerate(matrix, currPos);
                    }
                    break;
                case 4: //DOWN
                    if (r + 2 >= matrix.length - 1)
                        continue;
                    if (matrix[r + 2][c] != 0) {
                        matrix[r + 2][c] = 0;
                        matrix[r + 1][c] = 0;
                        currPos.setRowIndex(r + 2);
                        currPos.setColumnIndex(c);
                        recGenerate(matrix, currPos);
                    }
                    break;
                default:
                    //do we want to throw here an exception?
                    break;
            }
        }
    }


    /**
     * @param numOfDirs - quantity of the directions
     * @return array of numbers 0-X representing X different directions in random order
     */
    private Integer[] randomDirections (int numOfDirs) {
        ArrayList<Integer> directions = new ArrayList<>();
        for (int i = 0; i < numOfDirs; i++) {
            directions.add(i + 1);
        }
        Collections.shuffle(directions);

        return directions.toArray(new Integer[numOfDirs]);
    }

    //didnt use this yet
    private boolean isOutOfRange (int[][] mat, int i, int j) {
        return i < 0 || j < 0 || i == mat.length || j == mat[0].length;

    }

    /**
     * @param rows - number of rows
     * @param cols - number of columns
     * @return matrix full of 1's
     */
    private int[][] onesMatrix (int rows, int cols) {
        Maze newEmptyMaze = new Maze(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                newEmptyMaze.matrix[i][j] = 1;
            }
        }
        return newEmptyMaze.matrix;
    }

}



