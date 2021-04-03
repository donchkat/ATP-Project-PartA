package algorithms.mazeGenerators;

import java.util.Objects;

/**
 * this class represents a position in a 2D area.
 * rowIndex - the index of current row
 * columnIndex - the index of current column
 */
public class Position {
    private int rowIndex;
    private int columnIndex;

    public void setRowIndex (int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public void setColumnIndex (int columnIndex) {
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

    @Override
    public int hashCode () {
        return Objects.hash(rowIndex, columnIndex);
    }

    /**
     * constructor
     *
     * @param rowIndex
     * @param columnIndex
     */
    public Position (int rowIndex, int columnIndex) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
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


    public Position copy () {
        Position copyPos = new Position(this.rowIndex, this.columnIndex);
        return copyPos;
    }
}