package algorithms.mazeGenerators;

import Errors.LowBoundInput;

/**
 * this class represents a position in a 2D area.
 * rowIndex - the index of current row
 * columnIndex - the index of current column
 */
public class Position {
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

    public void setRowIndex (int rowIndex) throws LowBoundInput {
        if (rowIndex < 0)
            throw new LowBoundInput();
        this.rowIndex = rowIndex;
    }

    public void setColumnIndex (int columnIndex) throws LowBoundInput {
        if (columnIndex < 0)
            throw new LowBoundInput();
        this.columnIndex = columnIndex;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return rowIndex == position.rowIndex &&
                columnIndex == position.columnIndex;
    }

    public int getRowIndex () {
        return rowIndex;
    }

    public int getColumnIndex () {
        return columnIndex;
    }

    @Override
    public String toString () {
        return "{" + rowIndex + "," + columnIndex + '}';
    }

    /**
     * @return a new copy of current Position
     */
    public Position copy () throws LowBoundInput {
        Position copyPos = new Position(this.rowIndex, this.columnIndex);
        return copyPos;
    }
}
