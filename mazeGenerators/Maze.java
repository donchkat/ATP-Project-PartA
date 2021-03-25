package mazeGenerators;

import java.util.Arrays;

/**
 * this class represents a 2D Maze object
 * startPosition - the position we start the maze
 * goalPosition - the goal position
 * matrix - the 2D field of the maze
 */
public class Maze {
    private Position startPosition;
    private Position goalPosition;
    protected int[][] matrix;

    //constructor
    public Maze(int numOfRows, int numOfCols) {
        matrix = new int[numOfRows][numOfCols];

    }

    public void Print() {
        for (int i = 0; i < this.matrix.length; i++) {
            System.out.println("{ ");
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.println(this.matrix[i][j]+" ");
            }
            System.out.println("} \n");
        }
    }

    public Position getStartPosition() {
        return startPosition;
    }

    public Position getGoalPosition() {
        return goalPosition;
    }


}
