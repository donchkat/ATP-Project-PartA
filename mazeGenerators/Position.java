package mazeGenerators;

/**
 * this class represents a position in a 2D area.
 * rowIndex - the index of current row
 * columnIndex - the index of current column
 */
public class Position {
    private int rowIndex;
    private int columnIndex;

    /**constructor
     * @param rowIndex
     * @param columnIndex
     */
    public Position(int rowIndex, int columnIndex) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    @Override
    public String toString() {
        return "{" + rowIndex + "," + columnIndex + '}';
    }


}
