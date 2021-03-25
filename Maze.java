public class Maze {
    private int[][] matrix;
    private Position startPosition;
    private Position goalPosition;
    private int columns;
    private int  rows;


    public Maze(int numofrows,int numofcols) {
        matrix=new int[numofrows][numofcols];
        //how to find where is the start and end point??
        rows=numofrows;
        columns=numofrows;
    }

    public void Print(){
        //?
    }
    public Position getStartPosition() {
        return startPosition;
    }

    public Position getGoalPosition() {
        return goalPosition;
    }
}
