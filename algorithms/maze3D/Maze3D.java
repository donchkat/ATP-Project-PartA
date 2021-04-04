package algorithms.maze3D;

public class Maze3D {
    private int[][][] matrix3D;
    private Position3D startPosition3D;
    private Position3D goalPosition3D;
    private int depth;
    private int rows;
    private int cols;

    public Maze3D ( int depth, int rows, int cols) {
        this.matrix3D = new int[depth][rows][cols];
        this.startPosition3D = new Position3D(0,0,0);
        this.matrix3D[0][0][0] = 0;
        this.goalPosition3D = new Position3D(depth-1, rows-1,cols-1);
        this.matrix3D[depth-1][rows-1][cols-1] = 0;
        this.depth = depth;
        this.rows = rows;
        this.cols = cols;
    }

    public int[][][] getMap(){
        return null;
    }
    public Position3D getStartPosition(){
        return null;
    }
    public Position3D getGoalPosition(){
        return null;
    }

}
