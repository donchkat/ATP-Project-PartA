package algorithms.maze3D;

import Errors.LowBoundInput;

/**
 * The cube maze
 */
public class Maze3D {
    private int[][][] matrix3D;
    private Position3D startPosition3D;
    private Position3D goalPosition3D;
    private int depth;
    private int rows;
    private int cols;

    public Position3D getStartPosition() {
        return startPosition3D;
    }

    public Position3D getGoalPosition() {
        return goalPosition3D;
    }

    /**
     * setter for a specific cell in the matrix of the maze
     *
     * @param depth - the depth of the cell we set
     * @param row   - row index of the cell we set
     * @param col   - column index of the cell we set
     * @param value - the value we set into the cell
     */
    public void setCellInMatrix3D (int depth, int row, int col, int value) {
        this.matrix3D[depth][row][col] = value;
    }

    public void setMatrix3D (int[][][] matrix3D) {
        this.matrix3D = matrix3D;
    }

    public void setRows (int rows) {
        this.rows = rows;
    }


    public int[][][] getMap () {
        return matrix3D;
    }

    public int getDepth () {
        return depth;
    }

    public int getRows () {
        return rows;
    }

    public int getCols () {
        return cols;
    }

    /**
     * Constructor
     *
     * @param depth - number of layers
     * @param rows - number of rows
     * @param cols - number of columns
     * @throws LowBoundInput - exception
     */
    public Maze3D (int depth, int rows, int cols) throws LowBoundInput {
        if (depth < 2 || rows < 2 || cols < 2)
            throw new LowBoundInput();
        this.matrix3D = new int[depth][rows][cols];
        this.startPosition3D = new Position3D(0, 0, 0);
        this.matrix3D[0][0][0] = 0;
        this.goalPosition3D = new Position3D(depth - 1, rows - 1, cols - 1);
        this.matrix3D[depth - 1][rows - 1][cols - 1] = 0;
        this.depth = depth;
        this.rows = rows;
        this.cols = cols;
    }

    /**
     * checks if a given position indexes is out of maze bounds
     *
     * @param rowIndex - the row index of current position
     * @param colIndex - the column index of current position
     * @return boolean answer - T if the the position is out of the maze bounds F otherwise
     */
    public boolean checkLegalCell (int depthIndex, int rowIndex, int colIndex) {
        return rowIndex < 0 || colIndex < 0 || depthIndex < 0 || rowIndex >= rows || colIndex >= cols || depthIndex >= depth;
    }

    /**
     * checks if a cell in the maze contains 0 .
     *
     * @param depthIndex - the depth of current position
     * @param rowIndex   - the row of current position
     * @param colIndex   - the column of current position
     * @return - True if cell contains 0, else False
     */
    public boolean isContainZero (int depthIndex, int rowIndex, int colIndex) {
        return this.matrix3D[depthIndex][rowIndex][colIndex] == 0;
    }


    //Rotem's print function
    public void print () {
        System.out.println("{");
        for (int depth = 0; depth < matrix3D.length; depth++) {
            for (int row = 0; row < matrix3D[0].length; row++) {
                System.out.print("{ ");
                for (int col = 0; col < matrix3D[0][0].length; col++) {
                    if (depth == startPosition3D.getDepthIndex() && row == startPosition3D.getRowIndex() && col == startPosition3D.getColumnIndex()) // if the position is the start - mark with S
                        System.out.print("S ");
                    else {
                        if (depth == goalPosition3D.getDepthIndex() && row == goalPosition3D.getRowIndex() && col == goalPosition3D.getColumnIndex()) // if the position is the goal - mark with E
                            System.out.print("E ");
                        else
                            System.out.print(matrix3D[depth][row][col] + " ");
                    }
                }
                System.out.println("}");
            }
            if (depth < matrix3D.length - 1) {
                System.out.print("---");
                for (int i = 0; i < matrix3D[0][0].length; i++)
                    System.out.print("--");
                System.out.println();
            }
        }
        System.out.println("}");
    }


}
