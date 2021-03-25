public class Position {
    private int RowIndex;
    private int ColumnIndex;




    public int getRowIndex() {
        return RowIndex;
    }

    @Override
    public String toString() {
        return "{" + RowIndex + "," + ColumnIndex + '}';
    }

    public int getColumnIndex() {
        return ColumnIndex;
    }
}
