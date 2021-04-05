package algorithms.maze3D;

public class Maze3D {
    protected int[][][] matrix3D;
    private Position3D startPosition3D;
    private Position3D goalPosition3D;
    protected int depth;
    protected int rows;
    protected int cols;

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

    public void Print () {
        System.out.println("{ ");
        for (int i = 0; i < this.matrix3D.length; i++) {
            for (int j = 0; j < matrix3D[i].length; j++) {
                System.out.print("{ ");

                for (int k = 0; k < matrix3D[i][j].length; k++) {
                    if (i == 0 && j == 0 && k == 0) {
                        System.out.print('S' + " ");
                        continue;
                    }
                    if (i == depth - 1 && j == rows - 1 && k == cols-1) {
                        System.out.print('E' + " ");
                        continue;
                    }
                    System.out.print(this.matrix3D[i][j][k] + " ");
                }
                System.out.print("}\n");
            }
            System.out.println("------------"); //how much of these we need to print?

        }
        System.out.println("}\n");

    }


    public int[][][] getMap3D(){
        return matrix3D;
    }

    public int getRows3D () {
        return rows;
    }

    public int getCols3D () {
        return cols;
    }

    public Position3D getStartPosition3D(){
        return startPosition3D;
    }
    public Position3D getGoalPosition3D(){
        return goalPosition3D;
    }



}
