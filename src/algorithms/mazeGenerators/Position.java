package algorithms.mazeGenerators;

import Errors.LowBoundInput;

import java.io.Serializable;

/**
 * this class represents a position in a 2D area.
 * rowIndex - the index of current row
 * columnIndex - the index of current column
 */
public class Position implements Serializable {
    private int rowIndex;
    private int columnIndex;

    /**
     * constructor
     *
     * @param rowIndex    - the row index of current position.
     * @param columnIndex - the column index of current position.
     */
    public Position (int rowIndex, int columnIndex) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;

    }

    /**
     * @param rowIndex -the new row index
     * @throws LowBoundInput - throws exception
     */
    public void setRowIndex (int rowIndex) throws LowBoundInput {
        if (rowIndex < 0)
            throw new LowBoundInput();
        this.rowIndex = rowIndex;
    }

    /**
     * @param columnIndex -the new column index
     * @throws LowBoundInput -throws exception
     */
    public void setColumnIndex (int columnIndex) throws LowBoundInput {
        if (columnIndex < 0)
            throw new LowBoundInput();
        this.columnIndex = columnIndex;
    }

    /**
     * @param o- some object
     * @return true if it is a position that has equal coordinates to this
     */
    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return rowIndex == position.rowIndex &&
                columnIndex == position.columnIndex;
    }

    /**
     * @return index of the row of this
     */
    public int getRowIndex () {
        return rowIndex;
    }

    /**
     * @return index of the column of this
     */
    public int getColumnIndex () {
        return columnIndex;
    }

    /**
     * @return a string of the object as {x,y}
     */
    @Override
    public String toString () {
        return "{" + rowIndex + "," + columnIndex + '}';
    }

    /**
     * @return a new copy of current Position
     */
    public Position copy () {
        return new Position(this.rowIndex, this.columnIndex);
    }
}
