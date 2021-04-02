package mazeGenerators;

import search.ISearchable;
import search.MazeState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * this class represents a 2D Maze object
 * startPosition - the position we start the maze
 * goalPosition - the goal position
 * matrix - the 2D field of the maze
 */
public class Maze implements ISearchable {
    private Position startPosition;
    private Position goalPosition;
    protected int rows;
    protected int cols;
    protected int[][] matrix;

    public void setStartPosition (Position startPosition) {
        this.startPosition = startPosition;
    }

    public void setGoalPosition (Position goalPosition) {
        this.goalPosition = goalPosition;
    }

    //constructor
    public Maze (int numOfRows, int numOfCols) {
        matrix = new int[numOfRows][numOfCols];
        this.rows = numOfRows;
        this.cols = numOfCols;
        this.startPosition = new Position(0, 0);
        matrix[startPosition.getRowIndex()][startPosition.getColumnIndex()] = 0;
        this.goalPosition = new Position(rows - 1, cols - 1);
        matrix[goalPosition.getRowIndex()][goalPosition.getColumnIndex()] = 0;

    }

    public void Print () {
        for (int i = 0; i < this.matrix.length; i++) {
            //System.out.println("{ ");//WHY IS THIS HERE???? -  BECAUSE that's how they want it to be printed
            for (int j = 0; j < matrix[i].length; j++) {
                if(i==0&&j==0) {
                    System.out.print('S' + " ");
                continue;
                }
                if(i==rows-1&&j==cols-1) {
                    System.out.print('E' + " ");
                    continue;
                }
                System.out.print(this.matrix[i][j] + " ");
            }
            System.out.println("} \n");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Maze maze = (Maze) o;
        boolean flag=true;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(matrix[i][j]!=((Maze) o).matrix[i][j])
                    flag=false;
            }
        }
        return rows == maze.rows &&
                cols == maze.cols &&
                Objects.equals(startPosition, maze.startPosition) &&
                Objects.equals(goalPosition, maze.goalPosition) &&
                flag;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(startPosition, goalPosition, rows, cols);
        result = 31 * result + Arrays.hashCode(matrix);
        return result;
    }

    public Position getStartPosition () {
        return startPosition;
    }

    public Position getGoalPosition () {
        return goalPosition;
    }


    @Override
    public MazeState getStartState() {
        return null;
    }

    @Override
    public MazeState getGoalState() {
        return null;
    }

    @Override
    public ArrayList<MazeState> getAllSuccessors() {
        return null;
    }
}
