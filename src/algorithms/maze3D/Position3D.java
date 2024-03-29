package algorithms.maze3D;

import Errors.LowBoundInput;

/**
 * A class for 3D maze Positions.
 * Like regular Position + depth field is added.
 */
public class Position3D {
    private int depth;
    private int row;
    private int col;

    /**
     * constructor
     * @param depth - depth of current position
     * @param row - row of current position
     * @param col - column of current position
     */
    public Position3D (int depth, int row, int col)  {
        this.depth = depth;
        this.row = row;
        this.col = col;
    }

    /**
     * compares two positions
     * @param o - the object itself
     * @return true if they have same coordinates else false
     */
    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position3D that = (Position3D) o;
        return depth == that.depth && row == that.row && col == that.col;
    }

    /**
     * @return string Position3D {depth,row,column} - DO WE NEED SPACES BETWEEN NUMBERS??
     */
    @Override
    public String toString () {
        return "{" + depth + "," + row + "," + col + '}';
    }

    public Position3D copy () {
        return new Position3D(this.depth, this.row, this.col);
    }

    /**
     * @return the depth of current position
     */
    public int getDepthIndex(){
        return depth;
    }

    /**
     * @return the row of current position
     */
    public int getRowIndex(){
        return row;
    }

    /**
     * @return the column of current position
     */
    public int getColumnIndex(){
        return col;
    }

    /**
     * sets the value of current position depth
     */
    public void setDepth (int depth) throws LowBoundInput {
        if(depth < 0)
            throw new LowBoundInput();
        this.depth = depth;
    }

    /**
     * sets the value of current position row
     */
    public void setRowIndex (int row) throws LowBoundInput {
        if(row < 0)
            throw new LowBoundInput();
        this.row = row;
    }

    /**
     * sets the value of current position column
     */
    public void setColumnIndex (int col) throws LowBoundInput {
        if(col < 0)
            throw new LowBoundInput();
        this.col = col;
    }
}
