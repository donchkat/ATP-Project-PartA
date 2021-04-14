package algorithms.mazeGenerators;

import Errors.LowBoundInput;
import Errors.OutOfBoundMatrixInput;

/**
 * this class represents a 2D Maze object
 * startPosition - the position we start the maze at
 * goalPosition - the goal position we wanna get to
 * matrix - the 2D field of the maze (array of int [][])
 */
public class Maze {
    private Position startPosition;
    private Position goalPosition;
    private int rows;
    private int cols;
    private int[][] matrix;


    public void setRows (int rows) {
        this.rows = rows;
    }


    public int[][] getMatrix () {

        return matrix;
    }

    public void setStartPosition (Position startPosition) {
        this.startPosition = startPosition;
    }

    public void setGoalPosition (Position goalPosition) {
        this.goalPosition = goalPosition;
    }

    public void setMatrix (int[][] matrix) {
        this.matrix = matrix;
    }

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


    /**
     * constructor
     *
     * @param numOfRows - the number of rows in the maze
     * @param numOfCols - the number of columns in the maze
     */
    public Maze (int numOfRows, int numOfCols) throws LowBoundInput {
       if(numOfCols<2||numOfRows<2)throw new LowBoundInput();
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
    public void print () {
        for (int i = 0; i < this.matrix.length; i++) {
            System.out.print("{ ");
            for (int j = 0; j < matrix[i].length; j++) {
                if (i == startPosition.getRowIndex() && j == startPosition.getColumnIndex()) {
                    System.out.print('S' + " ");
                    continue;
                }
                if (i == goalPosition.getRowIndex() && j == goalPosition.getColumnIndex()) {
                    System.out.print('E' + " ");
                    continue;
                }
                System.out.print(this.matrix[i][j] + " ");
            }
            System.out.println("} \n");
        }
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

    /**
     * checks if a cell in the maze contains 0 .
     *
     * @param rowIndex - the row of current position
     * @param colIndex - the column of current position
     * @return - True if cell contains 0, else False
     */
    public boolean isContainZero (int rowIndex, int colIndex) throws LowBoundInput, OutOfBoundMatrixInput {
        if(rowIndex < 0 || colIndex < 0)throw new LowBoundInput();
        if(rowIndex >= this.matrix.length || colIndex >= this.matrix[0].length)throw new OutOfBoundMatrixInput();
        return this.matrix[rowIndex][colIndex] == 0;
    }

    /**
     * setter for a specific cell in the matrix of the maze
     * @param row - row index of the cell we set
     * @param col - column index of the cell we set
     * @param value - the value we set into the cell
     */
    public void setCellInMatrix(int row, int col, int value){
        this.matrix[row][col] = value;
    }

}