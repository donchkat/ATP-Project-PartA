package algorithms.maze3D;

public class Position3D {
    private int depth;
    private int row;
    private int col;

    public Position3D (int depth, int row, int col) {
        this.depth = depth;
        this.row = row;
        this.col = col;
    }

    public int getDepthIndex(){
        return 0;
    }

    public int getRowIndex(){
        return 0;
    }

    public int getColumnIndex(){
        return 0;
    }
}
