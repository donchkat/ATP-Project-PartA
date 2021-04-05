package algorithms.mazeGenerators;

import java.util.Objects;

/**
 * this class represents a 2D Maze object
 * startPosition - the position we start the maze at
 * goalPosition - the goal position we wanna get to
 * matrix - the 2D field of the maze (array of int [][])
 */
public class Maze {
    private Position startPosition;
    private Position goalPosition;
    protected int rows;
    protected int cols;
    protected int[][] matrix;


    /**
     * @return the number of rows in the maze
     */
    public int getRows () {
        return rows;
    }

    /**
     * @return the number of columns in the maze
     */
    public int getCols () {
        return cols;
    }

    //not using it - why?
    public void setStartPosition (Position stPos) {
        this.startPosition = stPos;
    }

    //not using it - why?
    public void setGoalPosition (Position goalPosition) {
        this.goalPosition = goalPosition;
    }

    /**
     * constructor
     *
     * @param numOfRows - the number of rows in the maze
     * @param numOfCols - the number of columns in the maze
     */
    public Maze (int numOfRows, int numOfCols) {
        matrix = new int[numOfRows][numOfCols];
        this.rows = numOfRows;
        this.cols = numOfCols;
        this.startPosition = new Position(0, 0);
        matrix[startPosition.getRowIndex()][startPosition.getColumnIndex()] = 0;
        this.goalPosition = new Position(rows - 1, cols - 1);
        matrix[goalPosition.getRowIndex()][goalPosition.getColumnIndex()] = 0;

    }


    /**
     * prints a 2D maze
     */
    public void Print () {
        for (int i = 0; i < this.matrix.length; i++) {
            System.out.print("{ ");
            for (int j = 0; j < matrix[i].length; j++) {
                if (i == 0 && j == 0) {
                    System.out.print('S' + " ");
                    continue;
                }
                if (i == rows - 1 && j == cols - 1) {
                    System.out.print('E' + " ");
                    continue;
                }
                System.out.print(this.matrix[i][j] + " ");
            }
            System.out.println("} \n");
        }
    }

    /**
     * ???????????????????
     *
     * @param o
     * @return
     */
    //what this function do?? where do we use it?
    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Maze maze = (Maze) o;
        boolean flag = true;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] != ((Maze) o).matrix[i][j])
                    flag = false;
            }
        }
        return rows == maze.rows &&
                cols == maze.cols &&
                Objects.equals(startPosition, maze.startPosition) &&
                Objects.equals(goalPosition, maze.goalPosition) &&
                flag;
    }


    /**
     * @return the start position of the maze
     */
    public Position getStartPosition () {
        return startPosition;
    }

    /**
     * @return the end position of the maze
     */
    public Position getGoalPosition () {
        return goalPosition;
    }

    /**
     * checks if a given position indexes is out of maze bounds
     *
     * @param rowIndex - the row index of current position
     * @param colIndex - the column index of current position
     * @return boolean answer - T if the the position is out of the maze bounds F otherwise
     */
    public boolean checkLegalCell (int rowIndex, int colIndex) {
        return rowIndex < 0 || colIndex < 0 || rowIndex >= this.matrix.length || colIndex >= this.matrix[0].length;
    }

    public boolean isContainZero (int rowIndex, int colIndex) {
        return this.matrix[rowIndex][colIndex] == 0;
    }


    /**
     * we are not using it- why?
     *
     * @param myMaze
     * @param optionalPos
     * @return
     */
    private boolean isOutOfRange (Maze myMaze, Position optionalPos) {
        int r = optionalPos.getRowIndex();
        int c = optionalPos.getColumnIndex();
        int mazeHeight = myMaze.rows - 1;
        int mazeWidth = myMaze.cols - 1;
        return r < 0 || r > mazeHeight || c < 0 || c > mazeWidth;

    }
}